package com.pushwoosh.notification.handlers.notification;

import androidx.annotation.NonNull;
import com.pushwoosh.internal.chain.Chain;
import com.pushwoosh.notification.handlers.notification.c;
import com.pushwoosh.q;

public final class NotificationOpenHandlerChainProvider {
    private static Chain<PushNotificationOpenHandler> a;

    private NotificationOpenHandlerChainProvider() {
    }

    @NonNull
    private static Chain<PushNotificationOpenHandler> a() {
        return new c.a().a(q.d().c()).a(new a()).a(new f()).a(new g()).a(new b()).a();
    }

    @NonNull
    public static Chain<PushNotificationOpenHandler> getNotificationOpenHandlerChain() {
        return a;
    }

    public static void init() {
        a = a();
    }
}
