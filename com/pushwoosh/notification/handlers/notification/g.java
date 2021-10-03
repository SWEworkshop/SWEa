package com.pushwoosh.notification.handlers.notification;

import android.os.Bundle;
import com.pushwoosh.inapp.view.b.a.b;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.q;
import com.pushwoosh.richmedia.a;

/* access modifiers changed from: package-private */
public class g implements PushNotificationOpenHandler {
    private final q a = RepositoryModule.getNotificationPreferences();

    g() {
    }

    @Override // com.pushwoosh.notification.handlers.notification.PushNotificationOpenHandler
    public void postHandleNotification(Bundle bundle) {
        b a2 = new b.a().a(com.pushwoosh.notification.b.B(bundle)).a((long) this.a.i().get()).a();
        RepositoryModule.getNotificationPreferences().r().set(com.pushwoosh.notification.b.y(bundle));
        a i = com.pushwoosh.q.d().i();
        if (i != null) {
            i.a(a2);
        }
    }
}
