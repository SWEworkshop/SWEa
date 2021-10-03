package com.pushwoosh.internal.crash;

import android.os.Looper;
import androidx.annotation.NonNull;
import com.pushwoosh.exception.PushwooshException;

public class LogSender {

    public static class a extends PushwooshException {
        public a(String str) {
            super(str);
        }
    }

    public static void submitCustomError(@NonNull Throwable th) {
        g.a(th, Looper.getMainLooper().getThread(), new f() {
            /* class com.pushwoosh.internal.crash.LogSender.AnonymousClass1 */

            @Override // com.pushwoosh.internal.crash.f
            public boolean b() {
                return true;
            }

            @Override // com.pushwoosh.internal.crash.f
            public boolean c() {
                return true;
            }

            @Override // com.pushwoosh.internal.crash.f
            public String d() {
                return "custom_log";
            }
        });
    }

    public static void submitCustomLog(@NonNull String str) {
        submitCustomError(new a(str));
    }
}
