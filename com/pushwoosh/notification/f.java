package com.pushwoosh.notification;

import com.pushwoosh.exception.RegisterForPushNotificationsException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.Subscription;
import com.pushwoosh.notification.event.RegistrationErrorEvent;
import com.pushwoosh.notification.event.RegistrationSuccessEvent;

public final class f {
    private final Callback<String, RegisterForPushNotificationsException> a;
    private Subscription<RegistrationSuccessEvent> b;
    private Subscription<RegistrationErrorEvent> c;

    private f(Callback<String, RegisterForPushNotificationsException> callback) {
        this.a = callback;
    }

    private void a() {
        this.b = EventBus.subscribe(RegistrationSuccessEvent.class, g.a(this));
        this.c = EventBus.subscribe(RegistrationErrorEvent.class, h.a(this));
    }

    public static void a(Callback<String, RegisterForPushNotificationsException> callback) {
        if (callback != null) {
            new f(callback).a();
        }
    }

    static /* synthetic */ void a(f fVar, RegistrationErrorEvent registrationErrorEvent) {
        fVar.b();
        fVar.a.process(Result.fromException(new RegisterForPushNotificationsException((String) registrationErrorEvent.getData())));
    }

    static /* synthetic */ void a(f fVar, RegistrationSuccessEvent registrationSuccessEvent) {
        fVar.b();
        fVar.a.process(Result.fromData(registrationSuccessEvent.getData()));
    }

    private void b() {
        Subscription<RegistrationSuccessEvent> subscription = this.b;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        Subscription<RegistrationErrorEvent> subscription2 = this.c;
        if (subscription2 != null) {
            subscription2.unsubscribe();
        }
    }
}
