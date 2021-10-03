package com.pushwoosh;

import android.content.Intent;
import com.pushwoosh.NotificationOpenReceiver;

final /* synthetic */ class l implements NotificationOpenReceiver.a {
    private final NotificationOpenReceiver a;
    private final Intent b;

    private l(NotificationOpenReceiver notificationOpenReceiver, Intent intent) {
        this.a = notificationOpenReceiver;
        this.b = intent;
    }

    public static NotificationOpenReceiver.a a(NotificationOpenReceiver notificationOpenReceiver, Intent intent) {
        return new l(notificationOpenReceiver, intent);
    }

    @Override // com.pushwoosh.NotificationOpenReceiver.a
    public void a() {
        NotificationOpenReceiver.a(this.a, this.b);
    }
}
