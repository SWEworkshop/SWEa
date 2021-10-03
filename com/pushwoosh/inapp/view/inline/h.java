package com.pushwoosh.inapp.view.inline;

/* access modifiers changed from: package-private */
public final /* synthetic */ class h implements Runnable {
    private final e a;

    private h(e eVar) {
        this.a = eVar;
    }

    public static Runnable a(e eVar) {
        return new h(eVar);
    }

    public void run() {
        this.a.b();
    }
}
