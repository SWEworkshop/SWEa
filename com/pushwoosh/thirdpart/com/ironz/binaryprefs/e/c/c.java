package com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c;

public final class c {
    private static final byte[] a = new byte[0];
    private final int b;
    private final String c;
    private final byte[] d;

    private c(int i, String str, byte[] bArr) {
        this.b = i;
        this.c = str;
        this.d = bArr;
    }

    public static c a(String str) {
        return new c(3, str, a);
    }

    static c a(String str, byte[] bArr) {
        return new c(1, str, bArr);
    }

    public static c b(String str, byte[] bArr) {
        return new c(2, str, bArr);
    }

    public int a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public byte[] c() {
        return this.d;
    }
}
