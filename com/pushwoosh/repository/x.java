package com.pushwoosh.repository;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

/* access modifiers changed from: package-private */
public final /* synthetic */ class x implements Callback {
    private final t a;
    private final Callback b;

    private x(t tVar, Callback callback) {
        this.a = tVar;
        this.b = callback;
    }

    public static Callback a(t tVar, Callback callback) {
        return new x(tVar, callback);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        t.a(this.a, this.b, result);
    }
}
