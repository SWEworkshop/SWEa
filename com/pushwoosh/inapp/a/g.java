package com.pushwoosh.inapp.a;

import com.pushwoosh.inapp.a.a;
import com.pushwoosh.inapp.view.e;
import com.pushwoosh.internal.event.Event;

/* access modifiers changed from: package-private */
public final /* synthetic */ class g implements a.b {
    private final String a;
    private final a.AbstractC0005a b;

    private g(String str, a.AbstractC0005a aVar) {
        this.a = str;
        this.b = aVar;
    }

    public static a.b a(String str, a.AbstractC0005a aVar) {
        return new g(str, aVar);
    }

    @Override // com.pushwoosh.inapp.a.a.b
    public boolean a(Event event) {
        return a.a(this.a, this.b, (e) event);
    }
}
