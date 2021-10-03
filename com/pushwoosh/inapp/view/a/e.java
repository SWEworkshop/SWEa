package com.pushwoosh.inapp.view.a;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

final /* synthetic */ class e implements Callback {
    private final d a;
    private final String b;
    private final String c;

    private e(d dVar, String str, String str2) {
        this.a = dVar;
        this.b = str;
        this.c = str2;
    }

    public static Callback a(d dVar, String str, String str2) {
        return new e(dVar, str, str2);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        d.b(this.a, this.b, this.c, result);
    }
}
