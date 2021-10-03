package com.pushwoosh.inbox.d;

import com.pushwoosh.inbox.e.a;

/* access modifiers changed from: package-private */
public final /* synthetic */ class n implements s {
    private final a a;

    private n(a aVar) {
        this.a = aVar;
    }

    public static s a(a aVar) {
        return new n(aVar);
    }

    @Override // com.pushwoosh.inbox.d.s
    public Object a() {
        return Integer.valueOf(this.a.b());
    }
}
