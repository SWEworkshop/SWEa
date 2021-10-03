package com.pushwoosh.thirdpart.com.ironz.binaryprefs.i;

import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.b;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.c;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.d;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.e;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.f;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.g;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.h;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.i;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.j;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.k;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.l;
import java.util.HashSet;
import java.util.Set;

public final class a {
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a a = new com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a();
    private final c b = new c();
    private final b c = new b();
    private final d d = new d();
    private final e e = new e();
    private final f f = new f();
    private final g g = new g();
    private final h h = new h();
    private final j i = new j();
    private final k j = new k();
    private final l k = new l();
    private final i l;

    public a(com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a.b bVar) {
        this.l = new i(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, bVar);
    }

    public com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a a() {
        return this.a;
    }

    public Object a(Object obj) {
        return obj instanceof com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a.a ? ((com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a.a) obj).a() : obj instanceof Set ? new HashSet((Set) obj) : obj;
    }

    public Object a(String str, byte[] bArr) {
        if (bArr.length != 0) {
            byte b2 = bArr[0];
            if (this.a.a(b2)) {
                return Boolean.valueOf(this.a.a(bArr));
            }
            if (this.g.a(b2)) {
                return Integer.valueOf(this.g.a(bArr));
            }
            if (this.h.a(b2)) {
                return Long.valueOf(this.h.a(bArr));
            }
            if (this.e.a(b2)) {
                return Double.valueOf(this.e.a(bArr));
            }
            if (this.f.a(b2)) {
                return Float.valueOf(this.f.a(bArr));
            }
            if (this.j.a(b2)) {
                return this.j.a(bArr);
            }
            if (this.k.a(b2)) {
                return this.k.a(bArr);
            }
            if (this.l.a(b2)) {
                return this.l.a(str, bArr);
            }
            if (this.i.a(b2)) {
                return Short.valueOf(this.i.a(bArr));
            }
            if (this.b.a(b2)) {
                return Byte.valueOf(this.b.a(bArr));
            }
            if (this.c.a(b2)) {
                return this.c.a(bArr);
            }
            if (this.d.a(b2)) {
                return Character.valueOf(this.d.a(bArr));
            }
            throw new UnsupportedClassVersionError(String.format("Flag verification failed. Incorrect flag '%s'", Byte.valueOf(b2)));
        }
        throw new com.pushwoosh.thirdpart.com.ironz.binaryprefs.d.d(String.format("%s key's value is zero bytes for deserialize", str));
    }

    public f b() {
        return this.f;
    }

    public g c() {
        return this.g;
    }

    public h d() {
        return this.h;
    }

    public k e() {
        return this.j;
    }

    public l f() {
        return this.k;
    }
}
