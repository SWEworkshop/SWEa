package com.pushwoosh.internal.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import com.pushwoosh.internal.fileprovider.PWFileProvider;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.notification.SoundType;
import com.pushwoosh.q;
import com.pushwoosh.repository.RepositoryModule;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class g {

    /* access modifiers changed from: package-private */
    public enum a {
        sound,
        image
    }

    private static float a(int i, int i2, int i3) {
        DisplayMetrics b;
        int max = Math.max(i2, i3);
        if (-1 == i || (b = AndroidPlatformModule.getResourceProvider().b()) == null) {
            return 1.0f;
        }
        return ((float) max) / (((float) i) * b.density);
    }

    public static int a(String str) {
        int a2;
        int a3 = AndroidPlatformModule.getResourceProvider().a("pw_notification", "drawable");
        if (a3 == 0) {
            a3 = -1;
        }
        int k = q.d().e().k();
        if (k != 0) {
            a3 = k;
        }
        return (str == null || (a2 = AndroidPlatformModule.getResourceProvider().a(str, "drawable")) == 0) ? a3 : a2;
    }

    public static Bitmap a(String str, int i) {
        return d(str) ? b(str, i) : c(c(str), i);
    }

    private static Uri a(File file) {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext == null) {
            return Uri.fromFile(file);
        }
        Uri uriForFile = PWFileProvider.getUriForFile(applicationContext, applicationContext.getPackageName() + ".provider", file);
        applicationContext.grantUriPermission("com.android.systemui", uriForFile, 1);
        return uriForFile;
    }

    private static Uri a(String str, a aVar) {
        String str2;
        String str3;
        String substring = str.substring(str.lastIndexOf(47) + 1);
        File h = AndroidPlatformModule.getAppInfoProvider().h();
        if (h == null) {
            str2 = "Asset";
            str3 = "Missing external cache dir";
        } else {
            String str4 = h.toString() + "/com.pushwoosh.noitfysnd";
            File file = new File(str4, substring);
            File file2 = new File(str4);
            if (file2.exists() || file2.mkdir()) {
                try {
                    AssetManager assets = AndroidPlatformModule.getManagerProvider().getAssets();
                    if (assets == null) {
                        return Uri.EMPTY;
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    a(assets.open(str), fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return aVar == a.sound ? a(file) : Uri.fromFile(file);
                } catch (Exception e) {
                    PWLog.error("Asset", "File not found: assets/" + str);
                    PWLog.exception(e);
                }
            } else {
                str2 = "Asset";
                str3 = "Storage file not created";
            }
        }
        PWLog.error(str2, str3);
        return Uri.EMPTY;
    }

    private static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static boolean a() {
        PackageManager packageManager = AndroidPlatformModule.getManagerProvider().getPackageManager();
        if (packageManager == null) {
            return false;
        }
        try {
            return packageManager.checkPermission("android.permission.VIBRATE", AndroidPlatformModule.getAppInfoProvider().b()) == 0;
        } catch (Exception e) {
            PWLog.error("error in checking vibrate permission", e);
        }
    }

    /* JADX INFO: finally extract failed */
    public static Bitmap b(String str, int i) {
        if (str == null) {
            return null;
        }
        try {
            URLConnection openConnection = new URL(str).openConnection();
            openConnection.connect();
            byte[] bArr = new byte[1024];
            InputStream inputStream = openConnection.getInputStream();
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int read = inputStream.read(bArr);
                        if (read != -1) {
                            byteArrayOutputStream.write(bArr, 0, read);
                        } else {
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.close();
                            inputStream.close();
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inJustDecodeBounds = true;
                            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
                            float a2 = a(i, options.outWidth, options.outHeight);
                            options.inJustDecodeBounds = false;
                            options.inSampleSize = Math.round(a2);
                            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
                        }
                    } catch (Throwable th) {
                        byteArrayOutputStream.close();
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                inputStream.close();
                throw th2;
            }
        } catch (Throwable th3) {
            PWLog.error("Can't load image: " + str, th3);
            return null;
        }
    }

    @Nullable
    public static Uri b(String str) {
        com.pushwoosh.repository.q notificationPreferences = RepositoryModule.getNotificationPreferences();
        Uri defaultUri = RingtoneManager.getDefaultUri(2);
        AudioManager audioManager = AndroidPlatformModule.getManagerProvider().getAudioManager();
        if (audioManager != null) {
            if (notificationPreferences.l().get() != SoundType.ALWAYS && (audioManager.getRingerMode() != 2 || notificationPreferences.l().get() != SoundType.DEFAULT_MODE)) {
                return null;
            }
            if (str == null) {
                return defaultUri;
            }
            if (str.length() == 0) {
                return null;
            }
        }
        int a2 = AndroidPlatformModule.getResourceProvider().a(str, "raw");
        if (a2 != 0) {
            return Uri.parse("android.resource://" + AndroidPlatformModule.getAppInfoProvider().b() + "/" + a2);
        }
        Uri a3 = a("www/res/" + str, a.sound);
        return a3 != Uri.EMPTY ? a3 : defaultUri;
    }

    @SuppressLint({"NewApi"})
    public static boolean b() {
        if (Build.VERSION.SDK_INT < 19) {
            return true;
        }
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext != null) {
            return NotificationManagerCompat.from(applicationContext).areNotificationsEnabled();
        }
        PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
        return false;
    }

    public static Bitmap c(String str, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = Math.round(a(i, options.outWidth, options.outHeight));
        return BitmapFactory.decodeFile(str, options);
    }

    public static String c(String str) {
        return a(str, a.image).getEncodedPath();
    }

    public static void c() {
        PowerManager powerManager;
        try {
            if (RepositoryModule.getNotificationPreferences().c().get() && (powerManager = AndroidPlatformModule.getManagerProvider().getPowerManager()) != null) {
                powerManager.newWakeLock(268435466, "Push").acquire(1000);
            }
        } catch (Exception e) {
            PWLog.exception(e);
        }
    }

    public static boolean d(String str) {
        try {
            new URL(str).toURI();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
