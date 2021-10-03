package com.pushwoosh.internal.network;

import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.event.InitHwidEvent;
import java.util.concurrent.CountDownLatch;

/* access modifiers changed from: package-private */
public final /* synthetic */ class d implements EventListener {
    private final CountDownLatch a;

    private d(CountDownLatch countDownLatch) {
        this.a = countDownLatch;
    }

    public static EventListener a(CountDownLatch countDownLatch) {
        return new d(countDownLatch);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        InitHwidEvent initHwidEvent = (InitHwidEvent) event;
        this.a.countDown();
    }
}
