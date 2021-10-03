package com.pushwoosh.notification;

import com.pushwoosh.exception.UnregisterForPushNotificationException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.Subscription;
import com.pushwoosh.notification.event.DeregistrationErrorEvent;
import com.pushwoosh.notification.event.DeregistrationSuccessEvent;

/* access modifiers changed from: package-private */
public final class j {
    private final Callback<String, UnregisterForPushNotificationException> a;
    private Subscription<DeregistrationSuccessEvent> b;
    private Subscription<DeregistrationErrorEvent> c;

    private j(Callback<String, UnregisterForPushNotificationException> callback) {
        this.a = callback;
    }

    private void a() {
        this.b = EventBus.subscribe(DeregistrationSuccessEvent.class, k.a(this));
        this.c = EventBus.subscribe(DeregistrationErrorEvent.class, l.a(this));
    }

    public static void a(Callback<String, UnregisterForPushNotificationException> callback) {
        if (callback != null) {
            new j(callback).a();
        }
    }

    static /* synthetic */ void a(j jVar, DeregistrationErrorEvent deregistrationErrorEvent) {
        jVar.b();
        jVar.a.process(Result.fromException(new UnregisterForPushNotificationException((String) deregistrationErrorEvent.getData())));
    }

    static /* synthetic */ void a(j jVar, DeregistrationSuccessEvent deregistrationSuccessEvent) {
        jVar.b();
        jVar.a.process(Result.fromData(deregistrationSuccessEvent.getData()));
    }

    private void b() {
        Subscription<DeregistrationSuccessEvent> subscription = this.b;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        Subscription<DeregistrationErrorEvent> subscription2 = this.c;
        if (subscription2 != null) {
            subscription2.unsubscribe();
        }
    }
}
