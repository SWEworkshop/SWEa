package com.pushwoosh.badge;

class a {
    private static com.pushwoosh.badge.b.a a;
    private static com.pushwoosh.badge.c.a b;

    static void a() {
        a = new com.pushwoosh.badge.b.a();
        b = new com.pushwoosh.badge.c.a(a);
    }

    static com.pushwoosh.badge.b.a b() {
        return a;
    }

    static com.pushwoosh.badge.c.a c() {
        return b;
    }
}
