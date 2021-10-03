package com.pushwoosh.inapp.view.inline;

final /* synthetic */ class f implements Runnable {
    private final e a;

    private f(e eVar) {
        this.a = eVar;
    }

    public static Runnable a(e eVar) {
        return new f(eVar);
    }

    public void run() {
        this.a.b();
    }
}
