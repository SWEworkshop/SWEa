package com.pushwoosh;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

final /* synthetic */ class d implements Callback {
    private final GDPRManager a;
    private final Callback b;

    private d(GDPRManager gDPRManager, Callback callback) {
        this.a = gDPRManager;
        this.b = callback;
    }

    public static Callback a(GDPRManager gDPRManager, Callback callback) {
        return new d(gDPRManager, callback);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        GDPRManager.f(this.a, this.b, result);
    }
}
