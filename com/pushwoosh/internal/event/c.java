package com.pushwoosh.internal.event;

import java.util.List;

/* access modifiers changed from: package-private */
public final /* synthetic */ class c implements Runnable {
    private final Event a;
    private final List b;

    private c(Event event, List list) {
        this.a = event;
        this.b = list;
    }

    public static Runnable a(Event event, List list) {
        return new c(event, list);
    }

    public void run() {
        EventBus.b(this.a, this.b);
    }
}
