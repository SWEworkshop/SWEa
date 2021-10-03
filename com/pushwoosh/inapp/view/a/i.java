package com.pushwoosh.inapp.view.a;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

final /* synthetic */ class i implements Callback {
    private final d a;
    private final String b;

    private i(d dVar, String str) {
        this.a = dVar;
        this.b = str;
    }

    public static Callback a(d dVar, String str) {
        return new i(dVar, str);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        d.a(this.a, this.b, result);
    }
}
