package com.pushwoosh.repository;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

/* access modifiers changed from: package-private */
public final /* synthetic */ class w implements Callback {
    private final t a;

    private w(t tVar) {
        this.a = tVar;
    }

    public static Callback a(t tVar) {
        return new w(tVar);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        t.a(this.a, result);
    }
}
