package com.pushwoosh.inapp.view.inline;

import com.pushwoosh.inapp.view.inline.e;

final /* synthetic */ class i implements Runnable {
    private final e.a a;

    private i(e.a aVar) {
        this.a = aVar;
    }

    public static Runnable a(e.a aVar) {
        return new i(aVar);
    }

    public void run() {
        e.a.lambda$resize$0(this.a);
    }
}
