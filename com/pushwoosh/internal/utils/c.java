package com.pushwoosh.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.pushwoosh.internal.platform.AndroidPlatformModule;

public class c {
    private SharedPreferences a;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private final Object e = new Object();

    public c(SharedPreferences sharedPreferences) {
        this.a = sharedPreferences;
    }

    private SharedPreferences f() {
        return this.a;
    }

    private Integer g() {
        try {
            if (f().contains("LastLaunchVersion")) {
                return Integer.valueOf(f().getInt("LastLaunchVersion", -1));
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void h() {
        try {
            f().edit().putInt("LastLaunchVersion", a()).apply();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public int a() {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext == null) {
            return 0;
        }
        try {
            return applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0).versionCode;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public void b() {
        synchronized (this.e) {
            if (!this.d) {
                Integer g = g();
                if (g == null) {
                    this.b = true;
                } else if (g.intValue() != a()) {
                    this.b = false;
                    this.c = true;
                    h();
                    this.d = true;
                } else {
                    this.b = false;
                }
                this.c = false;
                h();
                this.d = true;
            }
        }
    }

    public boolean c() {
        b();
        boolean z = this.b;
        this.b = false;
        return z;
    }

    public boolean d() {
        b();
        boolean z = this.c;
        this.c = false;
        return z;
    }

    public boolean e() {
        b();
        return this.b;
    }
}
