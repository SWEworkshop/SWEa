package com.pushwoosh.inapp.a;

import com.pushwoosh.inapp.a.a;
import java.util.concurrent.atomic.AtomicBoolean;

/* access modifiers changed from: package-private */
public final /* synthetic */ class e implements Runnable {
    private final a a;
    private final AtomicBoolean b;
    private final AtomicBoolean c;
    private final String d;
    private final a.AbstractC0005a e;

    private e(a aVar, AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, String str, a.AbstractC0005a aVar2) {
        this.a = aVar;
        this.b = atomicBoolean;
        this.c = atomicBoolean2;
        this.d = str;
        this.e = aVar2;
    }

    public static Runnable a(a aVar, AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, String str, a.AbstractC0005a aVar2) {
        return new e(aVar, atomicBoolean, atomicBoolean2, str, aVar2);
    }

    public void run() {
        a.a(this.a, this.b, this.c, this.d, this.e);
    }
}
