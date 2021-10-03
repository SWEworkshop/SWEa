package com.pushwoosh;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

final /* synthetic */ class f implements Callback {
    private final GDPRManager a;
    private final Callback b;

    private f(GDPRManager gDPRManager, Callback callback) {
        this.a = gDPRManager;
        this.b = callback;
    }

    public static Callback a(GDPRManager gDPRManager, Callback callback) {
        return new f(gDPRManager, callback);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        GDPRManager.d(this.a, this.b, result);
    }
}
