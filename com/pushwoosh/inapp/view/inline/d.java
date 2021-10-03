package com.pushwoosh.inapp.view.inline;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

/* access modifiers changed from: package-private */
public final /* synthetic */ class d implements Callback {
    private final c a;

    private d(c cVar) {
        this.a = cVar;
    }

    public static Callback a(c cVar) {
        return new d(cVar);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        this.a.b(result);
    }
}
