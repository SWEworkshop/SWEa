package com.pushwoosh.repository;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

/* access modifiers changed from: package-private */
public final /* synthetic */ class j implements Callback {
    private final i a;

    private j(i iVar) {
        this.a = iVar;
    }

    public static Callback a(i iVar) {
        return new j(iVar);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        this.a.e();
    }
}
