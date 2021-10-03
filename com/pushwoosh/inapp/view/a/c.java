package com.pushwoosh.inapp.view.a;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

final /* synthetic */ class c implements Callback {
    private final b a;
    private final String b;
    private final String c;

    private c(b bVar, String str, String str2) {
        this.a = bVar;
        this.b = str;
        this.c = str2;
    }

    public static Callback a(b bVar, String str, String str2) {
        return new c(bVar, str, str2);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        b.a(this.a, this.b, this.c, result);
    }
}
