package com.pushwoosh.inbox.d;

/* access modifiers changed from: package-private */
public final /* synthetic */ class g implements Runnable {
    private final b a;

    private g(b bVar) {
        this.a = bVar;
    }

    public static Runnable a(b bVar) {
        return new g(bVar);
    }

    public void run() {
        this.a.e();
    }
}
