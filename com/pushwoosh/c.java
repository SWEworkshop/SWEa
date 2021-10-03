package com.pushwoosh;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

final /* synthetic */ class c implements Callback {
    private final GDPRManager a;
    private final boolean b;
    private final Callback c;

    private c(GDPRManager gDPRManager, boolean z, Callback callback) {
        this.a = gDPRManager;
        this.b = z;
        this.c = callback;
    }

    public static Callback a(GDPRManager gDPRManager, boolean z, Callback callback) {
        return new c(gDPRManager, z, callback);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        GDPRManager.a(this.a, this.b, this.c, result);
    }
}
