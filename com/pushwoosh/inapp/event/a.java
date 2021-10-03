package com.pushwoosh.inapp.event;

import com.pushwoosh.inapp.e.b.b;
import com.pushwoosh.internal.event.Event;

public class a implements Event {
    private final b a;
    private final EnumC0007a b;

    /* renamed from: com.pushwoosh.inapp.event.a$a  reason: collision with other inner class name */
    public enum EnumC0007a {
        DOWNLOADING_ZIP,
        DOWNLOADED_ZIP,
        DEPLOYED,
        DEPLOY_FAILED
    }

    public a(EnumC0007a aVar, b bVar) {
        this.b = aVar;
        this.a = bVar;
    }

    public String a() {
        return this.a.a();
    }

    public EnumC0007a b() {
        return this.b;
    }
}
