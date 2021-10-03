package com.pushwoosh.inapp.view;

/* access modifiers changed from: package-private */
public final /* synthetic */ class g implements Runnable {
    private final f a;

    private g(f fVar) {
        this.a = fVar;
    }

    public static Runnable a(f fVar) {
        return new g(fVar);
    }

    public void run() {
        f.a(this.a);
    }
}
