package com.pushwoosh.inapp.a;

import com.pushwoosh.inapp.a.a;
import com.pushwoosh.inapp.view.d;
import com.pushwoosh.internal.event.Event;

/* access modifiers changed from: package-private */
public final /* synthetic */ class f implements a.b {
    private final String a;

    private f(String str) {
        this.a = str;
    }

    public static a.b a(String str) {
        return new f(str);
    }

    @Override // com.pushwoosh.inapp.a.a.b
    public boolean a(Event event) {
        return ((d) event).a().a().equals(this.a);
    }
}
