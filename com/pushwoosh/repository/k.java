package com.pushwoosh.repository;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

/* access modifiers changed from: package-private */
public final /* synthetic */ class k implements Callback {
    private final i a;

    private k(i iVar) {
        this.a = iVar;
    }

    public static Callback a(i iVar) {
        return new k(iVar);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        i.a(this.a, result);
    }
}
