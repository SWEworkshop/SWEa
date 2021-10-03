package com.pushwoosh;

import com.pushwoosh.NotificationOpenReceiver;
import java.util.List;

final /* synthetic */ class k implements NotificationOpenReceiver.b {
    private final NotificationOpenReceiver a;

    private k(NotificationOpenReceiver notificationOpenReceiver) {
        this.a = notificationOpenReceiver;
    }

    public static NotificationOpenReceiver.b a(NotificationOpenReceiver notificationOpenReceiver) {
        return new k(notificationOpenReceiver);
    }

    @Override // com.pushwoosh.NotificationOpenReceiver.b
    public void a(List list) {
        NotificationOpenReceiver.a(this.a, list);
    }
}
