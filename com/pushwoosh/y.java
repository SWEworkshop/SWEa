package com.pushwoosh;

import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.notification.e;

/* access modifiers changed from: package-private */
public final /* synthetic */ class y implements EventListener {
    private final s a;

    private y(s sVar) {
        this.a = sVar;
    }

    public static EventListener a(s sVar) {
        return new y(sVar);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        e.a aVar = (e.a) event;
        this.a.j();
    }
}
