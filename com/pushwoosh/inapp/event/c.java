package com.pushwoosh.inapp.event;

import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.inapp.e.b.b;
import com.pushwoosh.internal.event.Event;

public class c implements Event {
    private b a;
    private PushwooshException b;

    public c(b bVar, PushwooshException pushwooshException) {
        this.a = bVar;
        this.b = pushwooshException;
    }

    public b a() {
        return this.a;
    }

    public PushwooshException b() {
        return this.b;
    }
}
