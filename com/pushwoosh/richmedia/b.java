package com.pushwoosh.richmedia;

import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;

/* access modifiers changed from: package-private */
public final /* synthetic */ class b implements EventListener {
    private final a a;

    private b(a aVar) {
        this.a = aVar;
    }

    public static EventListener a(a aVar) {
        return new b(aVar);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        this.a.a((a) ((com.pushwoosh.inapp.event.b) event));
    }
}
