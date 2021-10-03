package com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a;

import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.g;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a;

public final class c implements a {
    private final int a;
    private final g b;

    public c(int i, com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a aVar) {
        this.a = i;
        this.b = aVar.c();
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a
    public byte[] a() {
        return this.b.a(this.a);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a
    public Object b() {
        return Integer.valueOf(this.a);
    }
}
