package com.pushwoosh.badge;

/* access modifiers changed from: package-private */
public final /* synthetic */ class b implements Runnable {
    private final int a;

    private b(int i) {
        this.a = i;
    }

    public static Runnable a(int i) {
        return new b(i);
    }

    public void run() {
        PushwooshBadge.a(this.a);
    }
}
