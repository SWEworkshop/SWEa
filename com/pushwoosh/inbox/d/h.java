package com.pushwoosh.inbox.d;

/* access modifiers changed from: package-private */
public final /* synthetic */ class h implements Runnable {
    private final b a;

    private h(b bVar) {
        this.a = bVar;
    }

    public static Runnable a(b bVar) {
        return new h(bVar);
    }

    public void run() {
        this.a.e();
    }
}
