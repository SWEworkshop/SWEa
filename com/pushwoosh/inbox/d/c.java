package com.pushwoosh.inbox.d;

import com.pushwoosh.function.Result;

/* access modifiers changed from: package-private */
public final /* synthetic */ class c implements com.pushwoosh.inbox.internal.b.c {
    private final b a;

    private c(b bVar) {
        this.a = bVar;
    }

    public static com.pushwoosh.inbox.internal.b.c a(b bVar) {
        return new c(bVar);
    }

    @Override // com.pushwoosh.inbox.internal.b.c
    public Object a(Object obj) {
        return b.c(this.a, (Result) obj);
    }
}
