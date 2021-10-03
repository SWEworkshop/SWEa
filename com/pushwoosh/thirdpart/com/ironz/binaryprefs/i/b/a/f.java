package com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a;

import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.l;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a;
import java.util.HashSet;
import java.util.Set;

public final class f implements a {
    private final Set<String> a;
    private final l b;

    public f(Set<String> set, com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a aVar) {
        this.a = set;
        this.b = aVar.f();
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a
    public byte[] a() {
        return this.b.a(this.a);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a
    public Object b() {
        return new HashSet(this.a);
    }
}
