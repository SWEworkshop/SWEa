package com.pushwoosh.inbox.d;

import com.pushwoosh.function.Callback;
import java.util.Map;

/* access modifiers changed from: package-private */
public final /* synthetic */ class q implements Runnable {
    private final b a;
    private final Map b;
    private final boolean c;
    private final Callback d;

    private q(b bVar, Map map, boolean z, Callback callback) {
        this.a = bVar;
        this.b = map;
        this.c = z;
        this.d = callback;
    }

    public static Runnable a(b bVar, Map map, boolean z, Callback callback) {
        return new q(bVar, map, z, callback);
    }

    public void run() {
        b.a(this.a, this.b, this.c, this.d);
    }
}
