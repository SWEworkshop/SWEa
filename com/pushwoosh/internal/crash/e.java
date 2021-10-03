package com.pushwoosh.internal.crash;

import java.io.File;
import java.io.FilenameFilter;

final /* synthetic */ class e implements FilenameFilter {
    private static final e a = new e();

    private e() {
    }

    public static FilenameFilter a() {
        return a;
    }

    public boolean accept(File file, String str) {
        return d.a(file, str);
    }
}
