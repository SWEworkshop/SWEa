package com.pushwoosh;

import com.pushwoosh.function.Callback;
import com.pushwoosh.inapp.a.a;
import com.pushwoosh.inapp.a.j;
import java.util.concurrent.atomic.AtomicBoolean;

final /* synthetic */ class n implements a.AbstractC0005a {
    private final Pushwoosh a;
    private final AtomicBoolean b;
    private final Callback c;

    private n(Pushwoosh pushwoosh, AtomicBoolean atomicBoolean, Callback callback) {
        this.a = pushwoosh;
        this.b = atomicBoolean;
        this.c = callback;
    }

    public static a.AbstractC0005a a(Pushwoosh pushwoosh, AtomicBoolean atomicBoolean, Callback callback) {
        return new n(pushwoosh, atomicBoolean, callback);
    }

    @Override // com.pushwoosh.inapp.a.a.AbstractC0005a
    public void a(j jVar) {
        Pushwoosh.a(this.a, this.b, this.c, jVar);
    }
}
