package com.pushwoosh.richmedia;

import com.pushwoosh.inapp.event.d;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;

/* access modifiers changed from: package-private */
public final /* synthetic */ class c implements EventListener {
    private final a a;

    private c(a aVar) {
        this.a = aVar;
    }

    public static EventListener a(a aVar) {
        return new c(aVar);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        this.a.a((a) ((d) event));
    }
}
