package com.pushwoosh.appevents;

import com.pushwoosh.internal.event.ConfigLoadedEvent;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;

final /* synthetic */ class f implements EventListener {
    private final d a;

    private f(d dVar) {
        this.a = dVar;
    }

    public static EventListener a(d dVar) {
        return new f(dVar);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        d.a(this.a, (ConfigLoadedEvent) event);
    }
}
