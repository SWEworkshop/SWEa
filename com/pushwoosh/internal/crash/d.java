package com.pushwoosh.internal.crash;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.platform.utils.GeneralUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* access modifiers changed from: package-private */
public class d {
    static WeakReference<Context> a;
    private static String b;
    private static String c;
    private static long d;

    public static int a() {
        String[] e = e();
        if (e == null || e.length <= 0) {
            return 0;
        }
        List list = null;
        try {
            Context d2 = d();
            if (d2 != null) {
                list = Arrays.asList(d2.getSharedPreferences("SdkCrashAnalytics", 0).getString("ConfirmedFilenames", "").split("\\|"));
            }
        } catch (Exception unused) {
        }
        if (list == null) {
            return 1;
        }
        for (String str : e) {
            if (!list.contains(str)) {
                return 1;
            }
        }
        return 2;
    }

    public static void a(Context context, String str, String str2) {
        a(context, str, str2, null);
    }

    public static void a(Context context, String str, String str2, @Nullable f fVar) {
        a(context, str, str2, fVar, false);
        a(fVar);
    }

    private static void a(Context context, String str, String str2, f fVar, boolean z) {
        if (context != null) {
            if (d == 0) {
                d = System.currentTimeMillis();
            }
            c = str;
            b = k.a(str2);
            a = new WeakReference<>(context);
            if (b == null) {
                b = "";
            }
            if (z) {
                d(fVar, fVar != null && fVar.a());
            }
        }
    }

    public static void a(final f fVar) {
        new AsyncTask<Void, Object, Integer>() {
            /* class com.pushwoosh.internal.crash.d.AnonymousClass1 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Integer doInBackground(Void... voidArr) {
                return Integer.valueOf(d.a());
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Integer num) {
                f fVar = fVar;
                boolean z = fVar != null && fVar.a();
                if (num.intValue() == 1) {
                    f fVar2 = fVar;
                    if (fVar2 != null) {
                        fVar2.e();
                    }
                } else if (num.intValue() == 2) {
                    f fVar3 = fVar;
                    if (fVar3 != null) {
                        fVar3.f();
                    }
                } else {
                    d.d(fVar, z);
                    return;
                }
                d.c(fVar, z);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static void a(String str) {
        Context d2 = d();
        if (d2 != null) {
            SharedPreferences.Editor edit = d2.getSharedPreferences("SdkCrashAnalytics", 0).edit();
            edit.remove("RETRY_COUNT: " + str);
            edit.apply();
        }
    }

    private static void a(String str, int i) {
        Context d2;
        if (i != -1 && (d2 = d()) != null) {
            SharedPreferences sharedPreferences = d2.getSharedPreferences("SdkCrashAnalytics", 0);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            int i2 = sharedPreferences.getInt("RETRY_COUNT: " + str, 0);
            if (i2 >= i) {
                b(str);
                a(str);
                return;
            }
            edit.putInt("RETRY_COUNT: " + str, i2 + 1);
            edit.apply();
        }
    }

    private static void b(String str) {
        Context d2 = d();
        if (d2 != null) {
            d2.deleteFile(str);
            d2.deleteFile(str.replace(h.a(), ".user"));
            d2.deleteFile(str.replace(h.a(), ".contact"));
            d2.deleteFile(str.replace(h.a(), ".description"));
        }
    }

    /* access modifiers changed from: private */
    public static void b(String str, f fVar) {
        boolean z = false;
        Boolean bool = false;
        HttpURLConnection httpURLConnection = null;
        try {
            String c2 = c(str);
            if (c2.length() > 0) {
                a.b("Transmitting crash data: \n" + c2);
                String c3 = c(str.replace(h.a(), ".description"));
                HashMap hashMap = new HashMap();
                hashMap.put("raw", c2);
                hashMap.put("description", c3);
                hashMap.put("version", "5.22.6");
                httpURLConnection = new i(c()).a("POST").a(hashMap).a();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 202 || responseCode == 201) {
                    z = true;
                }
                bool = Boolean.valueOf(z);
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (bool.booleanValue()) {
                a.b("Transmission succeeded");
                b(str);
                if (fVar == null) {
                    return;
                }
                fVar.g();
                a(str);
                return;
            }
            a.b("Transmission failed, will retry on next register() call");
            if (fVar == null) {
                return;
            }
            fVar.h();
            a(str, fVar.i());
        } catch (Exception e) {
            a.a("Failed to transmit crash data", e);
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            if (bool.booleanValue()) {
                a.b("Transmission succeeded");
                b(str);
                if (fVar == null) {
                }
            } else {
                a.b("Transmission failed, will retry on next register() call");
                if (fVar == null) {
                }
            }
        } catch (Throwable th) {
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            if (bool.booleanValue()) {
                a.b("Transmission succeeded");
                b(str);
                if (fVar != null) {
                    fVar.g();
                    a(str);
                }
            } else {
                a.b("Transmission failed, will retry on next register() call");
                if (fVar != null) {
                    fVar.h();
                    a(str, fVar.i());
                }
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public static void b(String[] strArr) {
        Context d2 = d();
        if (d2 != null) {
            try {
                SharedPreferences.Editor edit = d2.getSharedPreferences("SdkCrashAnalytics", 0).edit();
                edit.putString("ConfirmedFilenames", TextUtils.join(",", strArr));
                edit.apply();
            } catch (Exception unused) {
            }
        }
    }

    private static String c() {
        return c + "api/2/apps/" + b + "/crashes/";
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0067 A[SYNTHETIC, Splitter:B:29:0x0067] */
    private static String c(String str) {
        File fileStreamPath;
        Throwable th;
        IOException e;
        Context d2 = d();
        if (d2 == null || (fileStreamPath = d2.getFileStreamPath(str)) == null || !fileStreamPath.exists()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(d2.openFileInput(str)));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                        sb.append(System.getProperty("line.separator"));
                    } else {
                        try {
                            break;
                        } catch (IOException unused) {
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    bufferedReader = bufferedReader2;
                    try {
                        a.a("Failed to read content of " + str, e);
                        if (bufferedReader != null) {
                        }
                        return sb.toString();
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused2) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                    }
                    throw th;
                }
            }
            bufferedReader2.close();
        } catch (IOException e3) {
            e = e3;
            a.a("Failed to read content of " + str, e);
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return sb.toString();
        }
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public static void c(final f fVar, boolean z) {
        d(fVar, z);
        final boolean isNetworkAvailable = GeneralUtils.isNetworkAvailable();
        if (!isNetworkAvailable && fVar != null) {
            fVar.h();
        }
        new AsyncTask<Void, Object, Object>() {
            /* class com.pushwoosh.internal.crash.d.AnonymousClass2 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Object doInBackground(Void... voidArr) {
                String[] e = d.e();
                if (e == null) {
                    return null;
                }
                d.b(e);
                if (!isNetworkAvailable) {
                    return null;
                }
                for (String str : e) {
                    d.b(str, fVar);
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static Context d() {
        WeakReference<Context> weakReference = a;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static void d(f fVar, boolean z) {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            a.b("Current handler class = " + defaultUncaughtExceptionHandler.getClass().getName());
        }
        if (defaultUncaughtExceptionHandler instanceof g) {
            ((g) defaultUncaughtExceptionHandler).a(fVar);
        } else {
            Thread.setDefaultUncaughtExceptionHandler(new g(defaultUncaughtExceptionHandler, fVar, z));
        }
    }

    /* access modifiers changed from: private */
    public static String[] e() {
        Context d2 = d();
        if (d2 == null) {
            return null;
        }
        File a2 = h.a(d2);
        if (a2 != null) {
            a.b("Looking for exceptions in: " + a2.getAbsolutePath());
            return (a2.exists() || a2.mkdir()) ? a2.list(e.a()) : new String[0];
        }
        a.b("Can't search for exception as file path is null.");
        return null;
    }
}
