package com.pushwoosh.internal.crash;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

/* access modifiers changed from: package-private */
public class g implements Thread.UncaughtExceptionHandler {
    private boolean a = false;
    private f b;
    private Thread.UncaughtExceptionHandler c;

    g(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, f fVar, boolean z) {
        this.c = uncaughtExceptionHandler;
        this.a = z;
        this.b = fVar;
    }

    private static String a(Context context) {
        String packageName = context.getPackageName();
        return packageName == null ? "com.pushwoosh" : packageName;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x005a  */
    private static void a(Context context, String str, String str2) throws IOException {
        Throwable th;
        IOException e;
        BufferedWriter bufferedWriter;
        if (!TextUtils.isEmpty(str)) {
            BufferedWriter bufferedWriter2 = null;
            try {
                File file = new File(h.a(context), str2);
                if (TextUtils.isEmpty(str) || TextUtils.getTrimmedLength(str) <= 0) {
                    bufferedWriter = null;
                } else {
                    bufferedWriter = new BufferedWriter(new FileWriter(file));
                    try {
                        bufferedWriter.write(str);
                        bufferedWriter.flush();
                    } catch (IOException e2) {
                        e = e2;
                        bufferedWriter2 = bufferedWriter;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedWriter2 = bufferedWriter;
                        if (bufferedWriter2 != null) {
                        }
                        throw th;
                    }
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e3) {
                e = e3;
                try {
                    a.a("Failed to write value to " + str2, e);
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                    throw th;
                }
            }
        }
    }

    public static void a(Throwable th, Thread thread, f fVar) {
        Date date = new Date();
        th.printStackTrace(new PrintWriter(new StringWriter()));
        Context context = d.a != null ? d.a.get() : null;
        if (context == null) {
            a.a("Failed to save exception: context in CrashManager is null");
            return;
        }
        String uuid = UUID.randomUUID().toString();
        c cVar = new c(uuid, th);
        cVar.e(a(context));
        cVar.g("5.22.6");
        cVar.f("5.22.6");
        cVar.j(b.a());
        cVar.a(date);
        cVar.i(b.b());
        if (fVar == null || fVar.b()) {
            cVar.a(Build.VERSION.RELEASE);
            cVar.b(Build.DISPLAY);
            cVar.c(Build.MANUFACTURER);
            cVar.d(Build.DEVICE);
        }
        if (thread != null && (fVar == null || fVar.c())) {
            cVar.h(thread.getName() + "-" + thread.getId());
        }
        cVar.a(context);
        if (fVar != null) {
            try {
                String d = fVar.d();
                a(context, d, uuid + ".description");
            } catch (IOException e) {
                a.a("Error saving crash meta data!", e);
            }
        }
    }

    private boolean a(Context context, Throwable th) {
        String packageName = context.getPackageName();
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            String className = stackTraceElement.getClassName();
            for (String str : b.a) {
                if (className.contains(str)) {
                    return true;
                }
            }
            if (className.contains(packageName)) {
                return false;
            }
        }
        return false;
    }

    public void a(f fVar) {
        this.b = fVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        Context context = d.a != null ? d.a.get() : null;
        if (!(context == null || h.a(context) == null)) {
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                sb.append(stackTraceElement.getClassName());
            }
            String sb2 = sb.toString();
            if (a(context, th)) {
                Iterator<String> it = b.a.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (sb2.contains(it.next())) {
                            a(th, thread, this.b);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (this.a) {
                Process.killProcess(Process.myPid());
                System.exit(10);
                return;
            }
        }
        this.c.uncaughtException(thread, th);
    }
}
