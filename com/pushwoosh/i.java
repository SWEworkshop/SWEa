package com.pushwoosh;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

final /* synthetic */ class i implements Callback {
    private final GDPRManager a;
    private final Callback b;

    private i(GDPRManager gDPRManager, Callback callback) {
        this.a = gDPRManager;
        this.b = callback;
    }

    public static Callback a(GDPRManager gDPRManager, Callback callback) {
        return new i(gDPRManager, callback);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        GDPRManager.a(this.a, this.b, result);
    }
}
