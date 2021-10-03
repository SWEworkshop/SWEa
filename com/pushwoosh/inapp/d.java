package com.pushwoosh.inapp;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

/* access modifiers changed from: package-private */
public final /* synthetic */ class d implements Callback {
    private final c a;
    private final Callback b;

    private d(c cVar, Callback callback) {
        this.a = cVar;
        this.b = callback;
    }

    public static Callback a(c cVar, Callback callback) {
        return new d(cVar, callback);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        c.a(this.a, this.b, result);
    }
}
