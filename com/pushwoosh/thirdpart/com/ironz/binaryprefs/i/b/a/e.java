package com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a;

import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.k;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a;

public final class e implements a {
    private final String a;
    private final k b;

    public e(String str, com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a aVar) {
        this.a = str;
        this.b = aVar.e();
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a
    public byte[] a() {
        return this.b.a(this.a);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a
    public Object b() {
        return this.a;
    }
}
