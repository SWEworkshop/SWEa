package com.pushwoosh.inapp.a;

import com.pushwoosh.inapp.event.b;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;

/* access modifiers changed from: package-private */
public final /* synthetic */ class d implements EventListener {
    private final a a;

    private d(a aVar) {
        this.a = aVar;
    }

    public static EventListener a(a aVar) {
        return new d(aVar);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        this.a.a((a) ((b) event));
    }
}
