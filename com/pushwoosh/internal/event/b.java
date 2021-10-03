package com.pushwoosh.internal.event;

import com.pushwoosh.internal.event.Emitter;

final /* synthetic */ class b implements EventListener {
    private final Emitter.AnonymousClass1 a;

    private b(Emitter.AnonymousClass1 r1) {
        this.a = r1;
    }

    public static EventListener a(Emitter.AnonymousClass1 r1) {
        return new b(r1);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        Emitter.AnonymousClass1.a(this.a, event);
    }
}
