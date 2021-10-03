package com.pushwoosh.appevents;

/* access modifiers changed from: package-private */
public final /* synthetic */ class c implements Runnable {
    private final b a;

    private c(b bVar) {
        this.a = bVar;
    }

    public static Runnable a(b bVar) {
        return new c(bVar);
    }

    public void run() {
        b bVar;
        bVar.b.a("ScreenOpened", this.a.d);
    }
}
