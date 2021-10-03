package com.pushwoosh.inbox;

import com.pushwoosh.internal.event.AppIdChangedEvent;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;

final /* synthetic */ class b implements EventListener {
    private static final b a = new b();

    private b() {
    }

    public static EventListener a() {
        return a;
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        PushwooshInboxPlugin.a((AppIdChangedEvent) event);
    }
}
