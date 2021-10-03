package com.pushwoosh.inbox.c.a;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

final /* synthetic */ class c implements Callback {
    private static final c a = new c();

    private c() {
    }

    public static Callback a() {
        return a;
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        b.a(result);
    }
}
