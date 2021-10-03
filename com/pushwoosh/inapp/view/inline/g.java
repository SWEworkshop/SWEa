package com.pushwoosh.inapp.view.inline;

final /* synthetic */ class g implements Runnable {
    private final e a;

    private g(e eVar) {
        this.a = eVar;
    }

    public static Runnable a(e eVar) {
        return new g(eVar);
    }

    public void run() {
        this.a.b();
    }
}
