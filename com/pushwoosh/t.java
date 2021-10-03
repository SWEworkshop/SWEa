package com.pushwoosh;

import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.platform.a;

/* access modifiers changed from: package-private */
public final /* synthetic */ class t implements EventListener {
    private final s a;

    private t(s sVar) {
        this.a = sVar;
    }

    public static EventListener a(s sVar) {
        return new t(sVar);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        a.C0012a aVar = (a.C0012a) event;
        this.a.b.set(true);
    }
}
