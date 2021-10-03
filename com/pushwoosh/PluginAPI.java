package com.pushwoosh;

import com.pushwoosh.exception.RegisterForPushNotificationsException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.internal.utils.NotificationRegistrarHelper;

final class PluginAPI {
    PluginAPI() {
    }

    public static void handleTokenRefresh() {
        NotificationRegistrarHelper.clearToken();
        if (q.d() != null) {
            q.d().f().a((Callback<String, RegisterForPushNotificationsException>) null);
        }
    }
}
