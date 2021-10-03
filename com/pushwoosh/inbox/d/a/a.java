package com.pushwoosh.inbox.d.a;

import androidx.annotation.NonNull;
import java.util.Collection;
import java.util.Collections;

public class a {
    public static final a a = new a(Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    private Collection<String> b;
    private Collection<String> c;
    private Collection<String> d;

    public a(Collection<String> collection, Collection<String> collection2, Collection<String> collection3) {
        this.b = collection == null ? Collections.emptyList() : collection;
        this.c = collection2 == null ? Collections.emptyList() : collection2;
        this.d = collection3 == null ? Collections.emptyList() : collection3;
    }

    @NonNull
    public Collection<String> a() {
        return this.b;
    }

    @NonNull
    public Collection<String> b() {
        return this.c;
    }

    @NonNull
    public Collection<String> c() {
        return this.d;
    }

    public boolean d() {
        return this.b.isEmpty() && this.c.isEmpty() && this.d.isEmpty();
    }
}
