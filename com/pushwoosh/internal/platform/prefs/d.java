package com.pushwoosh.internal.platform.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.prefs.migration.a;
import com.pushwoosh.internal.platform.prefs.migration.b;
import com.pushwoosh.internal.utils.PWLog;

public class d {
    private static volatile d a;
    private int b;

    private d() {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        SharedPreferences sharedPreferences = applicationContext == null ? null : applicationContext.getSharedPreferences("com.pushwoosh.migration", 0);
        this.b = sharedPreferences == null ? 3 : sharedPreferences.getInt("lastVersion", 1);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt("lastVersion", 3).apply();
        }
        PWLog.noise("PrefsFactory created. LastVersion: " + this.b + "; CurrentVersion: " + 3);
    }

    private PrefsProvider a(int i) {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (i == 1) {
            return new c(applicationContext);
        }
        if (i == 2) {
            return new a(applicationContext);
        }
        if (i == 3) {
            return new c(applicationContext);
        }
        PWLog.noise("PrefsFactory", "Unknown version: " + i);
        return null;
    }

    @Nullable
    public static b a() {
        if (a == null) {
            d();
        }
        return a.f();
    }

    public static PrefsProvider b() {
        if (a == null) {
            d();
        }
        return a.a(3);
    }

    public static PrefsProvider c() {
        if (a == null) {
            return null;
        }
        return a.e();
    }

    private static void d() {
        synchronized (d.class) {
            if (a == null) {
                a = new d();
            }
        }
    }

    private PrefsProvider e() {
        return a(this.b);
    }

    private b f() {
        if (this.b == 2) {
            return new a(a(3));
        }
        return null;
    }
}
