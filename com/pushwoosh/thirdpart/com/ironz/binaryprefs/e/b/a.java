package com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.b;

import java.io.File;

public final class a implements b {
    private final File a;
    private final File b;
    private final File c;

    public a(String str, File file) {
        this.a = a(file, str, "values");
        this.b = a(file, str, "backup");
        this.c = a(file, str, "lock");
    }

    private File a(File file, String str, String str2) {
        File b2 = b(file, str, str2);
        if (b2.exists() || b2.mkdirs()) {
            return b2;
        }
        throw new com.pushwoosh.thirdpart.com.ironz.binaryprefs.d.a(String.format("Can't create preferences directory in %s", b2.getAbsolutePath()));
    }

    private File b(File file, String str, String str2) {
        return new File(new File(new File(file, "preferences"), str), str2);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.b.b
    public File a() {
        return this.a;
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.b.b
    public File b() {
        return this.b;
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.b.b
    public File c() {
        return this.c;
    }
}
