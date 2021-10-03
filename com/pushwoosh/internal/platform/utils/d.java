package com.pushwoosh.internal.platform.utils;

import com.pushwoosh.internal.platform.utils.a;

/* access modifiers changed from: package-private */
public final /* synthetic */ class d implements Runnable {
    private final a.d a;

    private d(a.d dVar) {
        this.a = dVar;
    }

    public static Runnable a(a.d dVar) {
        return new d(dVar);
    }

    public void run() {
        a.d.a(this.a);
    }
}
