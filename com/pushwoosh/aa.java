package com.pushwoosh;

import com.pushwoosh.BootReceiver;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;

/* access modifiers changed from: package-private */
public final /* synthetic */ class aa implements EventListener {
    private final s a;

    private aa(s sVar) {
        this.a = sVar;
    }

    public static EventListener a(s sVar) {
        return new aa(sVar);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        BootReceiver.DeviceBootedEvent deviceBootedEvent = (BootReceiver.DeviceBootedEvent) event;
        this.a.l.e();
    }
}
