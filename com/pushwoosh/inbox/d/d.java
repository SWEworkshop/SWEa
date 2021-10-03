package com.pushwoosh.inbox.d;

import com.pushwoosh.function.Callback;

/* access modifiers changed from: package-private */
public final /* synthetic */ class d implements Runnable {
    private final b a;
    private final boolean b;
    private final Callback c;

    private d(b bVar, boolean z, Callback callback) {
        this.a = bVar;
        this.b = z;
        this.c = callback;
    }

    public static Runnable a(b bVar, boolean z, Callback callback) {
        return new d(bVar, z, callback);
    }

    public void run() {
        b.a(this.a, this.b, this.c);
    }
}
