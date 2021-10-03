package com.pushwoosh.inapp.e;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

/* access modifiers changed from: package-private */
public final /* synthetic */ class f implements Callback {
    private final Callback a;

    private f(Callback callback) {
        this.a = callback;
    }

    public static Callback a(Callback callback) {
        return new f(callback);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        c.b(this.a, result);
    }
}
