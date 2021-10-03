package com.pushwoosh.repository.config;

import androidx.annotation.NonNull;
import java.util.List;

public class a {
    private List<Channel> a;
    private List<b> b;
    private String c;

    public a(@NonNull List<Channel> list, @NonNull List<b> list2, @NonNull String str) {
        this.a = list;
        this.b = list2;
        this.c = str;
    }

    public List<Channel> a() {
        return this.a;
    }

    public List<b> b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }
}
