package com.pushwoosh.inbox.d;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

/* access modifiers changed from: package-private */
public final /* synthetic */ class w implements Callback {
    private final v a;

    private w(v vVar) {
        this.a = vVar;
    }

    public static Callback a(v vVar) {
        return new w(vVar);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        v.a(this.a, result);
    }
}
