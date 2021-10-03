package com.pushwoosh.inbox.d;

import com.pushwoosh.inbox.e.a;

/* access modifiers changed from: package-private */
public final /* synthetic */ class m implements s {
    private final a a;

    private m(a aVar) {
        this.a = aVar;
    }

    public static s a(a aVar) {
        return new m(aVar);
    }

    @Override // com.pushwoosh.inbox.d.s
    public Object a() {
        return Integer.valueOf(this.a.c());
    }
}
