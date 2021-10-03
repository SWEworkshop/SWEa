package com.pushwoosh.inapp.view.a;

final /* synthetic */ class g implements Runnable {
    private final d a;
    private final String b;

    private g(d dVar, String str) {
        this.a = dVar;
        this.b = str;
    }

    public static Runnable a(d dVar, String str) {
        return new g(dVar, str);
    }

    public void run() {
        d.a(this.a, this.b);
    }
}
