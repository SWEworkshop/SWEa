package com.pushwoosh.appevents;

import com.pushwoosh.appevents.b;

final /* synthetic */ class g implements b.a {
    private final d a;

    private g(d dVar) {
        this.a = dVar;
    }

    public static b.a a(d dVar) {
        return new g(dVar);
    }

    @Override // com.pushwoosh.appevents.b.a
    public void a(String str, String str2) {
        d.a(this.a, str, str2);
    }
}
