package com.pushwoosh.inapp.view;

final /* synthetic */ class j implements Runnable {
    private final c a;

    private j(c cVar) {
        this.a = cVar;
    }

    public static Runnable a(c cVar) {
        return new j(cVar);
    }

    public void run() {
        this.a.c();
    }
}
