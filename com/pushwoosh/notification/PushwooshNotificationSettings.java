package com.pushwoosh.notification;

import androidx.annotation.ColorInt;
import com.pushwoosh.internal.utils.g;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.q;

public class PushwooshNotificationSettings {
    private static final q a = RepositoryModule.getNotificationPreferences();

    private static boolean a() {
        if (a != null) {
            return true;
        }
        com.pushwoosh.q.a();
        return false;
    }

    public static boolean areNotificationsEnabled() {
        return a() && g.b() && a.k().get();
    }

    public static void enableNotifications(boolean z) {
        if (a()) {
            a.k().set(z);
        }
    }

    public static void setColorLED(@ColorInt int i) {
        if (a()) {
            a.e().set(i);
        }
    }

    public static void setEnableLED(boolean z) {
        if (a()) {
            a.d().set(z);
        }
    }

    public static void setLightScreenOnNotification(boolean z) {
        if (a()) {
            a.c().set(z);
        }
    }

    public static void setMultiNotificationMode(boolean z) {
        if (a()) {
            a.a().set(z);
        }
    }

    public static void setNotificationChannelName(String str) {
        if (a()) {
            a.n().set(str);
        }
    }

    public static void setNotificationIconBackgroundColor(@ColorInt int i) {
        if (a()) {
            a.h().set(i);
        }
    }

    public static void setSoundNotificationType(SoundType soundType) {
        if (a()) {
            a.l().set(soundType);
        }
    }

    public static void setVibrateNotificationType(VibrateType vibrateType) {
        if (a()) {
            a.m().set(vibrateType);
        }
    }
}
