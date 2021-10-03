package com.pushwoosh.notification.handlers.notification;

import android.os.Bundle;
import android.text.TextUtils;
import com.pushwoosh.inapp.view.b.a.b;
import com.pushwoosh.notification.b;
import com.pushwoosh.q;
import com.pushwoosh.richmedia.a;

/* access modifiers changed from: package-private */
public class f implements PushNotificationOpenHandler {
    f() {
    }

    @Override // com.pushwoosh.notification.handlers.notification.PushNotificationOpenHandler
    public void postHandleNotification(Bundle bundle) {
        String G = b.G(bundle);
        if (!TextUtils.isEmpty(G)) {
            com.pushwoosh.inapp.view.b.a.b a = new b.a().b(G).a();
            a i = q.d().i();
            if (i != null) {
                i.a(a);
            }
        }
    }
}
