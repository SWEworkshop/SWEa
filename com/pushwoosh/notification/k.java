package com.pushwoosh.notification;

import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.notification.event.DeregistrationSuccessEvent;

/* access modifiers changed from: package-private */
public final /* synthetic */ class k implements EventListener {
    private final j a;

    private k(j jVar) {
        this.a = jVar;
    }

    public static EventListener a(j jVar) {
        return new k(jVar);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        j.a(this.a, (DeregistrationSuccessEvent) event);
    }
}
