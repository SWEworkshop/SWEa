package com.pushwoosh.inbox.d;

import com.pushwoosh.function.Result;
import com.pushwoosh.inbox.internal.b.c;

/* access modifiers changed from: package-private */
public final /* synthetic */ class l implements c {
    private final b a;

    private l(b bVar) {
        this.a = bVar;
    }

    public static c a(b bVar) {
        return new l(bVar);
    }

    @Override // com.pushwoosh.inbox.internal.b.c
    public Object a(Object obj) {
        return b.a(this.a, (Result) obj);
    }
}
