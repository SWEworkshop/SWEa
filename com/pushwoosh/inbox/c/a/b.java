package com.pushwoosh.inbox.c.a;

import android.os.Bundle;
import com.pushwoosh.function.Result;
import com.pushwoosh.inbox.c.a;
import com.pushwoosh.inbox.internal.data.InboxMessageStatus;
import com.pushwoosh.notification.handlers.notification.PushNotificationOpenHandler;

public class b implements PushNotificationOpenHandler {
    static /* synthetic */ void a(Result result) {
    }

    @Override // com.pushwoosh.notification.handlers.notification.PushNotificationOpenHandler
    public void postHandleNotification(Bundle bundle) {
        String a = a.a(bundle);
        if (a != null) {
            com.pushwoosh.inbox.internal.b.a().a(a, InboxMessageStatus.OPEN, c.a());
        }
    }
}
