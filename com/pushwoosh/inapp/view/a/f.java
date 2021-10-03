package com.pushwoosh.inapp.view.a;

final /* synthetic */ class f implements Runnable {
    private final d a;
    private final String b;

    private f(d dVar, String str) {
        this.a = dVar;
        this.b = str;
    }

    public static Runnable a(d dVar, String str) {
        return new f(dVar, str);
    }

    public void run() {
        d.b(this.a, this.b);
    }
}
