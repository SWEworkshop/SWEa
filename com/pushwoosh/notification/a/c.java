package com.pushwoosh.notification.a;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import com.pushwoosh.repository.RepositoryModule;

public class c {
    @RequiresApi(api = 26)
    private static int a(NotificationChannel notificationChannel) {
        if (c(notificationChannel)) {
            return 0;
        }
        return b(notificationChannel) ? 4 : 6;
    }

    @RequiresApi(api = 26)
    public static int a(NotificationManager notificationManager) {
        NotificationChannel notificationChannel;
        String a = a.a(RepositoryModule.getNotificationPreferences().n().get());
        if (TextUtils.isEmpty(a) || (notificationChannel = notificationManager.getNotificationChannel(a)) == null) {
            return 6;
        }
        return a(notificationChannel);
    }

    @RequiresApi(api = 26)
    private static boolean b(NotificationChannel notificationChannel) {
        return notificationChannel.getImportance() <= 2 || notificationChannel.getSound() == null;
    }

    @RequiresApi(api = 26)
    private static boolean c(NotificationChannel notificationChannel) {
        return notificationChannel.getImportance() == 0;
    }
}
