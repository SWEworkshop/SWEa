package com.pushwoosh.inapp.a;

import android.os.Handler;
import com.pushwoosh.inapp.a.a;
import java.util.concurrent.atomic.AtomicBoolean;

/* access modifiers changed from: package-private */
public final /* synthetic */ class c implements a.f {
    private final a a;
    private final Handler b;
    private final AtomicBoolean c;
    private final AtomicBoolean d;
    private final a.AbstractC0005a e;

    private c(a aVar, Handler handler, AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, a.AbstractC0005a aVar2) {
        this.a = aVar;
        this.b = handler;
        this.c = atomicBoolean;
        this.d = atomicBoolean2;
        this.e = aVar2;
    }

    public static a.f a(a aVar, Handler handler, AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, a.AbstractC0005a aVar2) {
        return new c(aVar, handler, atomicBoolean, atomicBoolean2, aVar2);
    }

    @Override // com.pushwoosh.inapp.a.a.f
    public void a(Object obj) {
        this.b.post(e.a(this.a, this.c, this.d, (String) obj, this.e));
    }
}
