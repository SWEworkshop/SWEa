package com.pushwoosh.internal.platform.utils;

import android.os.Handler;
import com.pushwoosh.internal.platform.utils.a;

/* access modifiers changed from: package-private */
public final /* synthetic */ class c implements a.d.AbstractC0014a {
    private final a.d a;
    private final Handler b;
    private final a.f.AbstractC0015a c;

    private c(a.d dVar, Handler handler, a.f.AbstractC0015a aVar) {
        this.a = dVar;
        this.b = handler;
        this.c = aVar;
    }

    public static a.d.AbstractC0014a a(a.d dVar, Handler handler, a.f.AbstractC0015a aVar) {
        return new c(dVar, handler, aVar);
    }

    @Override // com.pushwoosh.internal.platform.utils.a.d.AbstractC0014a
    public void a(String str) {
        a.d.a(this.a, this.b, this.c, str);
    }
}
