package com.pushwoosh.inbox.d;

import com.pushwoosh.function.Result;

/* access modifiers changed from: package-private */
public final /* synthetic */ class u implements Runnable {
    private final t a;
    private final Result b;

    private u(t tVar, Result result) {
        this.a = tVar;
        this.b = result;
    }

    public static Runnable a(t tVar, Result result) {
        return new u(tVar, result);
    }

    public void run() {
        t tVar;
        tVar.b(this.a.c.a(this.b));
    }
}
