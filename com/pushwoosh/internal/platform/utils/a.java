package com.pushwoosh.internal.platform.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.WorkRequest;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pushwoosh.PushwooshSharedDataProvider;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.q;
import com.pushwoosh.repository.RegistrationPrefs;
import com.pushwoosh.repository.RepositoryModule;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class a {
    private static f a = new c();
    private static f b = new C0013a();
    private static f c = new e();
    private static f d = new b();
    private static f e = new d();

    /* access modifiers changed from: private */
    /* renamed from: com.pushwoosh.internal.platform.utils.a$a  reason: collision with other inner class name */
    public static class C0013a extends f {
        private C0013a() {
        }

        /* access modifiers changed from: protected */
        @Override // com.pushwoosh.internal.platform.utils.a.f
        public String a() {
            return AndroidPlatformModule.getAppInfoProvider().c();
        }
    }

    /* access modifiers changed from: private */
    public static class b extends f {
        private b() {
        }

        /* access modifiers changed from: protected */
        @Override // com.pushwoosh.internal.platform.utils.a.f
        public String a() {
            RegistrationPrefs registrationPreferences = RepositoryModule.getRegistrationPreferences();
            String str = registrationPreferences.deviceId().get();
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            String uuid = UUID.randomUUID().toString();
            registrationPreferences.deviceId().set(uuid);
            return uuid;
        }
    }

    /* access modifiers changed from: private */
    public static class c extends f {
        private c() {
        }

        /* access modifiers changed from: protected */
        @Override // com.pushwoosh.internal.platform.utils.a.f
        public String a() {
            if (Build.VERSION.SDK_INT >= 28) {
                return "";
            }
            String str = Build.SERIAL;
            return TextUtils.equals("unknown", str) ? "" : str;
        }
    }

    /* access modifiers changed from: private */
    public static class d extends f {
        private CountDownLatch c;
        private b d;

        /* access modifiers changed from: private */
        /* renamed from: com.pushwoosh.internal.platform.utils.a$d$a  reason: collision with other inner class name */
        public interface AbstractC0014a {
            void a(String str);
        }

        /* access modifiers changed from: private */
        public static class b extends AsyncTask<Void, Void, String> {
            private final List<AbstractC0014a> a;

            private b() {
                this.a = new ArrayList();
            }

            private void b(String str) {
                ArrayList<AbstractC0014a> arrayList;
                if (TextUtils.isEmpty(str)) {
                    str = UUID.randomUUID().toString();
                }
                RepositoryModule.getRegistrationPreferences().deviceId().set(str);
                synchronized (this.a) {
                    arrayList = new ArrayList(this.a);
                }
                for (AbstractC0014a aVar : arrayList) {
                    aVar.a(str);
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public String doInBackground(Void... voidArr) {
                return d.e();
            }

            /* access modifiers changed from: package-private */
            public void a(AbstractC0014a aVar) {
                synchronized (this.a) {
                    this.a.add(aVar);
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(String str) {
                super.onPostExecute(str);
                b(str);
            }

            /* access modifiers changed from: protected */
            public void onCancelled() {
                super.onCancelled();
                b(null);
            }
        }

        private d() {
            this.c = new CountDownLatch(1);
        }

        private static List<ProviderInfo> a(List<ProviderInfo> list) {
            String c2 = q.d().e().c();
            if (TextUtils.isEmpty(c2)) {
                return list;
            }
            ArrayList arrayList = new ArrayList();
            for (ProviderInfo providerInfo : list) {
                if (!TextUtils.isEmpty(providerInfo.packageName) && providerInfo.packageName.contains(c2)) {
                    arrayList.add(providerInfo);
                }
            }
            return arrayList;
        }

        static /* synthetic */ void a(d dVar) {
            b bVar = dVar.d;
            if (bVar != null && bVar.getStatus() != AsyncTask.Status.FINISHED) {
                dVar.d.cancel(true);
            }
        }

        static /* synthetic */ void a(d dVar, Handler handler, f.AbstractC0015a aVar, String str) {
            handler.removeCallbacksAndMessages(null);
            if (aVar != null) {
                aVar.a(str);
            }
            dVar.c.countDown();
            dVar.d = null;
        }

        private boolean d() {
            b bVar = this.d;
            return (bVar == null || bVar.getStatus() == AsyncTask.Status.FINISHED) ? false : true;
        }

        /* access modifiers changed from: private */
        public static String e() {
            Exception e;
            List<ProviderInfo> a;
            String str = null;
            try {
                Context applicationContext = AndroidPlatformModule.getApplicationContext();
                if (!(applicationContext == null || (a = a(applicationContext.getPackageManager().queryContentProviders(null, 0, 0))) == null)) {
                    if (a.size() != 0) {
                        String str2 = applicationContext.getPackageName() + "." + PushwooshSharedDataProvider.class.getSimpleName();
                        for (ProviderInfo providerInfo : a) {
                            if (providerInfo.authority.endsWith("." + PushwooshSharedDataProvider.class.getSimpleName()) && !providerInfo.authority.equals(str2)) {
                                Cursor query = applicationContext.getContentResolver().query(Uri.parse("content://" + providerInfo.authority + "/" + "hwid"), null, null, null, GeneralUtils.md5(applicationContext.getPackageName()));
                                if (query == null) {
                                    continue;
                                } else {
                                    if (query.getColumnCount() > 0 && query.getColumnName(0).equals("hwid") && query.moveToFirst()) {
                                        String string = query.getString(0);
                                        if (!TextUtils.isEmpty(string)) {
                                            try {
                                                query.close();
                                                return string;
                                            } catch (Exception e2) {
                                                e = e2;
                                                str = string;
                                                e.printStackTrace();
                                                return str;
                                            }
                                        }
                                    }
                                    query.close();
                                }
                            }
                        }
                        return null;
                    }
                }
                return null;
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
                return str;
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.pushwoosh.internal.platform.utils.a.f
        public String a() {
            RegistrationPrefs registrationPreferences = RepositoryModule.getRegistrationPreferences();
            if (!d()) {
                a((f.AbstractC0015a) null);
            }
            try {
                this.c.await();
                return registrationPreferences.deviceId().get();
            } catch (InterruptedException unused) {
                return null;
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.pushwoosh.internal.platform.utils.a.f
        public void a(f.AbstractC0015a aVar) {
            if (!d()) {
                Handler handler = new Handler();
                AbstractC0014a a = c.a(this, handler, aVar);
                try {
                    String str = RepositoryModule.getRegistrationPreferences().deviceId().get();
                    if (!TextUtils.isEmpty(str)) {
                        a.a(str);
                        return;
                    }
                    this.d = new b();
                    this.d.a(a);
                    handler.postDelayed(d.a(this), WorkRequest.MIN_BACKOFF_MILLIS);
                    this.d.execute(new Void[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                    a.a(null);
                }
            } else if (aVar != null) {
                b bVar = this.d;
                aVar.getClass();
                bVar.a(b.a(aVar));
            }
        }
    }

    /* access modifiers changed from: private */
    public static class e extends f {
        private e() {
        }

        /* access modifiers changed from: protected */
        @Override // com.pushwoosh.internal.platform.utils.a.f
        @SuppressLint({"MissingPermission", "HardwareIds"})
        public String a() {
            try {
                TelephonyManager telephonyManager = AndroidPlatformModule.getManagerProvider().getTelephonyManager();
                return telephonyManager != null ? telephonyManager.getDeviceId() : "";
            } catch (RuntimeException e) {
                PWLog.error("DeviceTelephonyUUID", e);
                return "";
            }
        }
    }

    public static abstract class f {
        private static List<String> c = new ArrayList();
        String a = null;
        f b;

        /* access modifiers changed from: protected */
        /* renamed from: com.pushwoosh.internal.platform.utils.a$f$a  reason: collision with other inner class name */
        public interface AbstractC0015a {
            void a(String str);
        }

        f() {
            c.add("9774d56d682e549c");
            c.add("1234567");
            c.add("abcdef");
            c.add("dead00beef");
            c.add("unknown");
        }

        static /* synthetic */ void a(f fVar, f fVar2, String str) {
            String str2;
            f fVar3;
            if (!fVar.a(str) || (fVar3 = fVar.b) == null) {
                fVar.a = str;
                str2 = fVar.a;
            } else {
                str2 = fVar3.c();
            }
            fVar2.a(str2);
        }

        private boolean a(String str) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str.replace('0', ' ').replace('-', ' ').trim())) {
                return true;
            }
            return c.contains(str.toLowerCase());
        }

        /* access modifiers changed from: protected */
        public abstract String a();

        /* access modifiers changed from: protected */
        public void a(AbstractC0015a aVar) {
            aVar.a(a());
        }

        /* access modifiers changed from: package-private */
        public void a(f fVar) {
            this.b = fVar;
        }

        public void a(f fVar) {
            synchronized (this) {
                if (this.a != null) {
                    fVar.a(this.a);
                } else {
                    a(e.a(this, fVar));
                }
            }
        }

        public String c() {
            synchronized (this) {
                if (this.a != null) {
                    return this.a;
                }
                String a2 = a();
                if (!a(a2) || this.b == null) {
                    this.a = a2;
                    return a2;
                }
                return this.b.c();
            }
        }
    }

    static {
        a();
    }

    private static String a(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char charAt = str.charAt(0);
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        return Character.toUpperCase(charAt) + str.substring(1);
    }

    static void a() {
        a = new c();
        b = new C0013a();
        c = new e();
        d = new b();
        e = new d();
        b.a(c);
        c.a(a);
        a.a(d);
        e.a(d);
    }

    public static void a(@NonNull f fVar) {
        e.a(fVar);
    }

    public static String b() {
        return e.c();
    }

    @Nullable
    public static String c() {
        return RepositoryModule.getRegistrationPreferences().deviceId().get();
    }

    public static String d() {
        return b.c();
    }

    @SuppressLint({"InlinedApi"})
    public static boolean e() {
        Configuration a2 = AndroidPlatformModule.getResourceProvider().a();
        return a2 != null && (a2.screenLayout & 4) == 4;
    }

    public static float f() {
        try {
            Intent a2 = AndroidPlatformModule.getReceiverProvider().a((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (a2 == null) {
                return -1.0f;
            }
            int intExtra = a2.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1);
            int intExtra2 = a2.getIntExtra("scale", -1);
            if (intExtra == -1 || intExtra2 == -1) {
                return -1.0f;
            }
            return (((float) intExtra) / ((float) intExtra2)) * 100.0f;
        } catch (Exception unused) {
            return -1.0f;
        }
    }

    public static long g() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static long h() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static long i() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static long j() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static int k() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = AndroidPlatformModule.getManagerProvider().getWindowManager();
        if (windowManager == null) {
            return 0;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int l() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = AndroidPlatformModule.getManagerProvider().getWindowManager();
        if (windowManager == null) {
            return 0;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    @Nullable
    public static String m() {
        return AndroidPlatformModule.getAppInfoProvider().e();
    }

    public static List<String> n() {
        try {
            ArrayList arrayList = new ArrayList();
            for (ApplicationInfo applicationInfo : AndroidPlatformModule.getAppInfoProvider().g()) {
                arrayList.add(applicationInfo.packageName);
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String o() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return a(str2);
        }
        return a(str) + " " + str2;
    }

    public static boolean p() {
        PowerManager powerManager;
        KeyguardManager keyguardManager = AndroidPlatformModule.getManagerProvider().getKeyguardManager();
        if (keyguardManager == null || keyguardManager.inKeyguardRestrictedInputMode() || (powerManager = AndroidPlatformModule.getManagerProvider().getPowerManager()) == null) {
            return false;
        }
        if (!(Build.VERSION.SDK_INT < 20 ? powerManager.isScreenOn() : powerManager.isInteractive())) {
            return false;
        }
        ActivityManager activityManager = AndroidPlatformModule.getManagerProvider().getActivityManager();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager == null ? null : activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        String b2 = AndroidPlatformModule.getAppInfoProvider().b();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(b2)) {
                return true;
            }
        }
        return false;
    }

    public static String q() {
        try {
            return AndroidPlatformModule.getApplicationContext().getPackageManager().getPackageInfo("com.google.android.gms", 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "not_installed";
        } catch (NullPointerException unused2) {
            return "undefined";
        }
    }

    public static String r() {
        return Build.DISPLAY;
    }

    public static boolean s() {
        return t() || u();
    }

    private static boolean t() {
        boolean z = false;
        Process process = null;
        try {
            process = new ProcessBuilder("/system/xbin/which", "su").start();
            if (new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null) {
                z = true;
            }
            if (process != null) {
                process.destroy();
            }
            return z;
        } catch (Throwable th) {
            if (process != null) {
                process.destroy();
            }
            throw th;
        }
    }

    private static boolean u() {
        for (String str : System.getenv("PATH").split(":")) {
            if (new File(str, "su").exists()) {
                return true;
            }
        }
        return false;
    }
}
