package com.pushwoosh.badge;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.pushwoosh.badge.d.a.c;
import com.pushwoosh.internal.platform.AndroidPlatformModule;

public class PushwooshBadge {
    static /* synthetic */ void a(int i) {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext != null) {
            c.a(applicationContext, i);
        }
    }

    public static void addBadgeNumber(int i) {
        setBadgeNumber(getBadgeNumber() + i);
    }

    public static int getBadgeNumber() {
        return a.b().a().get();
    }

    public static void setBadgeNumber(int i) {
        a.c().a(i);
        new Handler(Looper.getMainLooper()).post(b.a(i));
    }
}
