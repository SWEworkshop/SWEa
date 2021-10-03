package com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a;

import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.f;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a;

public final class b implements a {
    private final float a;
    private final f b;

    public b(float f, com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a aVar) {
        this.a = f;
        this.b = aVar.b();
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a
    public byte[] a() {
        return this.b.a(this.a);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a
    public Object b() {
        return Float.valueOf(this.a);
    }
}
