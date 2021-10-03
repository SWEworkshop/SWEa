package com.pushwoosh.internal.platform.utils;

import com.pushwoosh.internal.platform.utils.a;

/* access modifiers changed from: package-private */
public final /* synthetic */ class e implements a.f.AbstractC0015a {
    private final a.f a;
    private final f b;

    private e(a.f fVar, f fVar2) {
        this.a = fVar;
        this.b = fVar2;
    }

    public static a.f.AbstractC0015a a(a.f fVar, f fVar2) {
        return new e(fVar, fVar2);
    }

    @Override // com.pushwoosh.internal.platform.utils.a.f.AbstractC0015a
    public void a(String str) {
        a.f.a(this.a, this.b, str);
    }
}
