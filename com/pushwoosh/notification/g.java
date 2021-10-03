package com.pushwoosh.notification;

import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.notification.event.RegistrationSuccessEvent;

/* access modifiers changed from: package-private */
public final /* synthetic */ class g implements EventListener {
    private final f a;

    private g(f fVar) {
        this.a = fVar;
    }

    public static EventListener a(f fVar) {
        return new g(fVar);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        f.a(this.a, (RegistrationSuccessEvent) event);
    }
}
