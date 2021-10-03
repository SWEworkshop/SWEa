package com.pushwoosh.inapp.e.a;

import com.pushwoosh.inapp.e.b.b;
import java.util.Collections;
import java.util.List;

public class a {
    private final List<b> a;
    private final List<b> b;

    private a() {
        this.a = Collections.emptyList();
        this.b = Collections.emptyList();
    }

    a(List<b> list, List<b> list2) {
        this.a = list;
        this.b = list2;
    }

    public static a a() {
        return new a();
    }

    public List<b> b() {
        return this.a;
    }
}
