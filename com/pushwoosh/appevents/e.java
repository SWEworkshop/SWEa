package com.pushwoosh.appevents;

import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.platform.a;

final /* synthetic */ class e implements EventListener {
    private final d a;

    private e(d dVar) {
        this.a = dVar;
    }

    public static EventListener a(d dVar) {
        return new e(dVar);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        d.a(this.a, (a.C0012a) event);
    }
}
