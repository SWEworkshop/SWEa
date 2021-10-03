package com.pushwoosh.inbox.d;

import com.pushwoosh.function.Result;
import com.pushwoosh.inbox.internal.b.c;

/* access modifiers changed from: package-private */
public final /* synthetic */ class k implements c {
    private final b a;

    private k(b bVar) {
        this.a = bVar;
    }

    public static c a(b bVar) {
        return new k(bVar);
    }

    @Override // com.pushwoosh.inbox.internal.b.c
    public Object a(Object obj) {
        return b.b(this.a, (Result) obj);
    }
}
