package com.pushwoosh.inapp;

import androidx.annotation.Nullable;
import com.pushwoosh.inapp.f.a;
import com.pushwoosh.inapp.f.c;
import com.pushwoosh.inapp.f.d;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.repository.RepositoryModule;

public class b {
    private static final c a = new a(AndroidPlatformModule.getApplicationContext());
    private static final com.pushwoosh.inapp.e.a.c b = new com.pushwoosh.inapp.e.a.c(a());
    private static final com.pushwoosh.inapp.c.b c = new com.pushwoosh.inapp.c.b(a());
    private static volatile d d;
    private static final Object e = new Object();
    private static volatile com.pushwoosh.inapp.e.c f;
    private static final Object g = new Object();

    public static c a() {
        return a;
    }

    @Nullable
    public static d b() {
        synchronized (e) {
            if (d == null) {
                if (AndroidPlatformModule.getApplicationContext() == null) {
                    return null;
                }
                d = new com.pushwoosh.inapp.f.b(AndroidPlatformModule.getApplicationContext());
            }
            return d;
        }
    }

    @Nullable
    public static com.pushwoosh.inapp.e.c c() {
        synchronized (g) {
            if (f == null) {
                if (AndroidPlatformModule.getApplicationContext() == null) {
                    return null;
                }
                f = new com.pushwoosh.inapp.e.c(NetworkModule.getRequestManager(), b(), d(), e(), a(), RepositoryModule.getRegistrationPreferences());
            }
            return f;
        }
    }

    private static com.pushwoosh.inapp.e.a.c d() {
        return b;
    }

    private static com.pushwoosh.inapp.c.b e() {
        return c;
    }
}
