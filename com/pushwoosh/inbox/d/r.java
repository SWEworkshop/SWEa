package com.pushwoosh.inbox.d;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inbox.d.b;

/* access modifiers changed from: package-private */
public final /* synthetic */ class r implements Callback {
    private final b a;
    private final Callback b;
    private final long c;
    private final int d;

    private r(b bVar, Callback callback, long j, int i) {
        this.a = bVar;
        this.b = callback;
        this.c = j;
        this.d = i;
    }

    public static Callback a(b bVar, Callback callback, long j, int i) {
        return new r(bVar, callback, j, i);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        new b.a(this.b, result, this.c, this.d).execute((b) new Void[0]);
    }
}
