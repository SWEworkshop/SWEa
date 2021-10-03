package com.pushwoosh.internal.event;

import com.pushwoosh.internal.event.Emitter;

final /* synthetic */ class a implements EventListener {
    private final Emitter.AnonymousClass1 a;

    private a(Emitter.AnonymousClass1 r1) {
        this.a = r1;
    }

    public static EventListener a(Emitter.AnonymousClass1 r1) {
        return new a(r1);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        Emitter.AnonymousClass1.b(this.a, event);
    }
}
