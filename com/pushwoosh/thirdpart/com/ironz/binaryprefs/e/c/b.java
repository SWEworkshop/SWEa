package com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c;

import com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.a.a;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;

public final class b implements a {
    private final a a;
    private final Lock b;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.b.a c;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.b.b d;

    public b(a aVar, com.pushwoosh.thirdpart.com.ironz.binaryprefs.g.a aVar2, com.pushwoosh.thirdpart.com.ironz.binaryprefs.b.a aVar3, com.pushwoosh.thirdpart.com.ironz.binaryprefs.b.b bVar) {
        this.a = aVar;
        this.b = aVar2.c();
        this.d = bVar;
        this.c = aVar3;
    }

    private c b(String str) {
        return c.a(str, this.d.b(this.a.a(this.c.b(str))));
    }

    private void b(List<c> list) {
        for (c cVar : list) {
            int a2 = cVar.a();
            String b2 = this.c.b(cVar.b());
            if (a2 == 2) {
                this.a.a(b2, this.d.a(cVar.c()));
            }
            if (a2 == 3) {
                this.a.b(b2);
            }
        }
    }

    private List<c> e() {
        String[] a2 = this.a.a();
        ArrayList arrayList = new ArrayList(a2.length);
        for (String str : a2) {
            arrayList.add(b(str));
        }
        return arrayList;
    }

    private Set<String> f() {
        String[] a2 = this.a.a();
        HashSet hashSet = new HashSet();
        for (String str : a2) {
            hashSet.add(this.c.a(str));
        }
        return hashSet;
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.a
    public c a(String str) {
        return b(str);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.a
    public void a() {
        this.b.lock();
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.a
    public void a(List<c> list) {
        b(list);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.a
    public void b() {
        this.b.unlock();
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.a
    public List<c> c() {
        return e();
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.a
    public Set<String> d() {
        return f();
    }
}
