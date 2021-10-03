package com.pushwoosh.notification;

import android.app.NotificationManager;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.b;
import java.io.Serializable;

public class LocalNotificationRequest implements Serializable {
    private int a;

    public LocalNotificationRequest(int i) {
        this.a = i;
    }

    public void cancel() {
        unschedule();
        b b = RepositoryModule.getLocalNotificationStorage().b(this.a);
        if (b != null) {
            int d = b.d();
            String e = b.e();
            NotificationManager notificationManager = AndroidPlatformModule.getManagerProvider().getNotificationManager();
            if (notificationManager != null) {
                notificationManager.cancel(e, d);
            }
        }
    }

    public int getRequestId() {
        return this.a;
    }

    public void unschedule() {
        LocalNotificationReceiver.cancelNotification(this.a);
    }
}
