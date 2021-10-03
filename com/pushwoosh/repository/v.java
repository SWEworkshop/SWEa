package com.pushwoosh.repository;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

/* access modifiers changed from: package-private */
public final /* synthetic */ class v implements Callback {
    private final t a;

    private v(t tVar) {
        this.a = tVar;
    }

    public static Callback a(t tVar) {
        return new v(tVar);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        t.b(this.a, result);
    }
}
