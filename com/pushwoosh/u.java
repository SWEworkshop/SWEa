package com.pushwoosh;

import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.notification.e;

/* access modifiers changed from: package-private */
public final /* synthetic */ class u implements EventListener {
    private final s a;

    private u(s sVar) {
        this.a = sVar;
    }

    public static EventListener a(s sVar) {
        return new u(sVar);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        e.a aVar = (e.a) event;
        this.a.c.set(true);
    }
}
