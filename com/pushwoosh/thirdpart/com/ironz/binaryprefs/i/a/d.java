package com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a;

public final class d {
    public char a(byte[] bArr) {
        return a(bArr, 0);
    }

    public char a(byte[] bArr, int i) {
        return (char) ((bArr[i + 1] << 8) + (bArr[i + 2] & 255));
    }

    public boolean a(byte b) {
        return b == -10;
    }
}
