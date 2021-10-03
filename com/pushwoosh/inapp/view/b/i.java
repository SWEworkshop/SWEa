package com.pushwoosh.inapp.view.b;

import android.content.Intent;

final /* synthetic */ class i implements Runnable {
    private final h a;
    private final Intent b;

    private i(h hVar, Intent intent) {
        this.a = hVar;
        this.b = intent;
    }

    public static Runnable a(h hVar, Intent intent) {
        return new i(hVar, intent);
    }

    public void run() {
        this.a.a.startActivity(this.b);
    }
}
