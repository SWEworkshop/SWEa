package com.pushwoosh.internal.utils;

final /* synthetic */ class b implements Runnable {
    private final a a;

    private b(a aVar) {
        this.a = aVar;
    }

    public static Runnable a(a aVar) {
        return new b(aVar);
    }

    public void run() {
        a.a(this.a);
    }
}
