package com.pushwoosh.notification;

import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.notification.event.DeregistrationErrorEvent;

/* access modifiers changed from: package-private */
public final /* synthetic */ class l implements EventListener {
    private final j a;

    private l(j jVar) {
        this.a = jVar;
    }

    public static EventListener a(j jVar) {
        return new l(jVar);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        j.a(this.a, (DeregistrationErrorEvent) event);
    }
}
