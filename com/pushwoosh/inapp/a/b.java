package com.pushwoosh.inapp.a;

import com.pushwoosh.inapp.a.a;
import java.util.concurrent.atomic.AtomicBoolean;

/* access modifiers changed from: package-private */
public final /* synthetic */ class b implements Runnable {
    private final AtomicBoolean a;
    private final a.AbstractC0005a b;
    private final AtomicBoolean c;

    private b(AtomicBoolean atomicBoolean, a.AbstractC0005a aVar, AtomicBoolean atomicBoolean2) {
        this.a = atomicBoolean;
        this.b = aVar;
        this.c = atomicBoolean2;
    }

    public static Runnable a(AtomicBoolean atomicBoolean, a.AbstractC0005a aVar, AtomicBoolean atomicBoolean2) {
        return new b(atomicBoolean, aVar, atomicBoolean2);
    }

    public void run() {
        a.a(this.a, this.b, this.c);
    }
}
