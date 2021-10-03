package com.pushwoosh.notification.handlers.notification;

import android.os.Bundle;
import com.pushwoosh.inapp.view.b.a.b;
import com.pushwoosh.notification.b;
import com.pushwoosh.q;

/* access modifiers changed from: package-private */
public class a implements PushNotificationOpenHandler {
    a() {
    }

    @Override // com.pushwoosh.notification.handlers.notification.PushNotificationOpenHandler
    public void postHandleNotification(Bundle bundle) {
        String F = b.F(bundle);
        if (F != null) {
            com.pushwoosh.inapp.view.b.a.b a = new b.a().b(String.format("https://go.pushwoosh.com/content/%s", F)).a();
            com.pushwoosh.richmedia.a i = q.d().i();
            if (i != null) {
                i.a(a);
            }
        }
    }
}
