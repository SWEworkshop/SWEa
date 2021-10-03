package com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a;

public final class a implements com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a {
    private final boolean a;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a b;

    public a(boolean z, com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a aVar) {
        this.a = z;
        this.b = aVar.a();
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a
    public byte[] a() {
        return this.b.a(this.a);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a
    public Object b() {
        return Boolean.valueOf(this.a);
    }
}
