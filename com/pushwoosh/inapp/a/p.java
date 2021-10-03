package com.pushwoosh.inapp.a;

import com.pushwoosh.inapp.a.a;

/* access modifiers changed from: package-private */
public final /* synthetic */ class p implements Runnable {
    private final a a;
    private final a.AbstractC0005a b;

    private p(a aVar, a.AbstractC0005a aVar2) {
        this.a = aVar;
        this.b = aVar2;
    }

    public static Runnable a(a aVar, a.AbstractC0005a aVar2) {
        return new p(aVar, aVar2);
    }

    public void run() {
        this.a.a((a) this.b);
    }
}
