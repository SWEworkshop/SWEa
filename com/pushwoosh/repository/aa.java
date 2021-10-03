package com.pushwoosh.repository;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import java.util.List;

final /* synthetic */ class aa implements Callback {
    private final z a;
    private final List b;

    private aa(z zVar, List list) {
        this.a = zVar;
        this.b = list;
    }

    public static Callback a(z zVar, List list) {
        return new aa(zVar, list);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        z.a(this.a, this.b, result);
    }
}
