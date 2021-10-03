package com.pushwoosh.thirdpart.com.ironz.binaryprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.c;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.d;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.h.a;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public final class b {
    private final d a = new d();
    private final Map<String, ReadWriteLock> b = this.a.a();
    private final Map<String, Lock> c = this.a.b();
    private final Map<String, ExecutorService> d = this.a.e();
    private final Map<String, Map<String, Object>> e = this.a.c();
    private final Map<String, Set<String>> f = this.a.f();
    private final Map<String, List<SharedPreferences.OnSharedPreferenceChangeListener>> g = this.a.d();
    private final Context h;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a.b i = new com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a.a.b();
    private final a j = new a();
    private File k;
    private String l = "default";
    private boolean m = false;
    private boolean n = true;
    private com.pushwoosh.thirdpart.com.ironz.binaryprefs.b.a o = com.pushwoosh.thirdpart.com.ironz.binaryprefs.b.a.a;
    private com.pushwoosh.thirdpart.com.ironz.binaryprefs.b.b p = com.pushwoosh.thirdpart.com.ironz.binaryprefs.b.b.a;
    private c q = c.b;

    public b(Context context) {
        this.h = context;
        this.k = context.getFilesDir();
    }

    private a b() {
        com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a aVar;
        com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.b bVar;
        com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.b.a aVar2 = new com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.b.a(this.l, this.k);
        com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.a.b bVar2 = new com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.a.b(aVar2);
        com.pushwoosh.thirdpart.com.ironz.binaryprefs.g.c cVar = new com.pushwoosh.thirdpart.com.ironz.binaryprefs.g.c(this.l, aVar2, this.b, this.c);
        com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.b bVar3 = new com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.b(bVar2, cVar, this.o, this.p);
        com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.a.b bVar4 = new com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.a.b(this.l, this.f);
        com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.b bVar5 = new com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.b(this.l, this.e);
        com.pushwoosh.thirdpart.com.ironz.binaryprefs.j.b bVar6 = new com.pushwoosh.thirdpart.com.ironz.binaryprefs.j.b(this.l, this.q, this.d);
        com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a aVar3 = new com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a(this.i);
        if (this.m) {
            aVar = aVar3;
            bVar = new com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.a(this.h, this.l, bVar4, bVar5, aVar3, bVar6, this.p, aVar2, this.g);
        } else {
            aVar = aVar3;
            bVar = new d(this.l, this.g);
        }
        return new a(bVar3, bVar, bVar4, bVar5, bVar6, aVar, cVar, this.n ? new com.pushwoosh.thirdpart.com.ironz.binaryprefs.f.c(cVar, bVar6, bVar4, bVar5, bVar3, aVar) : new com.pushwoosh.thirdpart.com.ironz.binaryprefs.f.a(cVar, bVar6, bVar4, bVar5, bVar3, aVar));
    }

    public b a(c cVar) {
        this.q = cVar;
        return this;
    }

    public b a(String str) {
        this.l = str;
        return this;
    }

    public b a(boolean z) {
        this.m = z;
        return this;
    }

    public e a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new com.pushwoosh.thirdpart.com.ironz.binaryprefs.d.c("Preferences should be instantiated in the main thread.");
        } else if (!this.n || !this.m) {
            a b2 = b();
            this.j.a(b2);
            return b2;
        } else {
            throw new UnsupportedOperationException("IPC mode can't be used with lazy in-memory cache strategy!");
        }
    }
}
