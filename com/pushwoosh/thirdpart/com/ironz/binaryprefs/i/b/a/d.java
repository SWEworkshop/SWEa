package com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a;

import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.h;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a;

public final class d implements a {
    private final long a;
    private final h b;

    public d(long j, com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a aVar) {
        this.a = j;
        this.b = aVar.d();
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a
    public byte[] a() {
        return this.b.a(this.a);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a
    public Object b() {
        return Long.valueOf(this.a);
    }
}
