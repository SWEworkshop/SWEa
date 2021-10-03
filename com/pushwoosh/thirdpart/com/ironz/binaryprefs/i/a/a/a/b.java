package com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a.a;

import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.c;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.d;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.e;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.f;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.g;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.h;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.j;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.k;

public final class b implements a {
    private final a a;
    private final c b;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.b c;
    private final d d;
    private final e e;
    private final f f;
    private final g g;
    private final h h;
    private final j i;
    private final k j;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a.b k;
    private int l = 0;
    private byte[] m;
    private String n;

    public b(a aVar, c cVar, com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.b bVar, d dVar, e eVar, f fVar, g gVar, h hVar, j jVar, k kVar, com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a.b bVar2) {
        this.a = aVar;
        this.b = cVar;
        this.c = bVar;
        this.d = dVar;
        this.e = eVar;
        this.f = fVar;
        this.g = gVar;
        this.h = hVar;
        this.i = jVar;
        this.j = kVar;
        this.k = bVar2;
    }

    private com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a.a a(Class<? extends com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a.a> cls) {
        try {
            return (com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a.a) cls.newInstance();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    private void a(int i2) {
        int i3 = this.l + i2;
        int length = this.m.length;
        if (i3 > length) {
            throw new ArrayIndexOutOfBoundsException(String.format("Can't read out of bounds array (expected size: %s bytes > disk size: %s bytes) for %s! keyMay be your read/write contract isn't mirror-implemented or old disk version is not backward compatible with new class version?", this.n, Integer.valueOf(i3), Integer.valueOf(length)));
        }
    }

    private com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a.a b() {
        com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a.a a2 = a(this.k.a(this.n));
        a2.a(this);
        return a2;
    }

    private void c() {
        if (this.m.length == 0) {
            throw new UnsupportedOperationException(String.format("Cannot deserialize empty byte array for %s key! May be your read/write contract isn't mirror-implemented or old disk version is not backward compatible with new class version?", this.n));
        }
    }

    private void d() {
        this.l++;
    }

    public int a() {
        int a2 = this.g.a();
        a(a2);
        byte b2 = this.m[this.l];
        if (this.g.a(b2)) {
            int a3 = this.g.a(this.m, this.l);
            this.l += a2;
            return a3;
        }
        throw new ClassCastException(String.format("int cannot be deserialized in '%s' flag type", Byte.valueOf(b2)));
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a.a.a
    public com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a.a a(String str, byte[] bArr) {
        this.l = 0;
        this.n = str;
        this.m = bArr;
        c();
        d();
        a();
        return b();
    }
}
