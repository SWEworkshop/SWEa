package com.pushwoosh.notification;

import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.notification.event.RegistrationErrorEvent;

/* access modifiers changed from: package-private */
public final /* synthetic */ class h implements EventListener {
    private final f a;

    private h(f fVar) {
        this.a = fVar;
    }

    public static EventListener a(f fVar) {
        return new h(fVar);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        f.a(this.a, (RegistrationErrorEvent) event);
    }
}
