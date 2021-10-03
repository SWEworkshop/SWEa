package com.pushwoosh.thirdpart.com.ironz.binaryprefs;

import com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.b;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.d.e;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a.d;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a.f;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;

final class c implements f {
    private final Map<String, a> a = new HashMap();
    private final Set<String> b = new HashSet();
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.a c;
    private final b d;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.j.c e;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a f;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a g;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.a.a h;
    private final Lock i;
    private boolean j;

    c(com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.a aVar, b bVar, com.pushwoosh.thirdpart.com.ironz.binaryprefs.j.c cVar, com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a aVar2, com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a aVar3, com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.a.a aVar4, Lock lock) {
        this.c = aVar;
        this.d = bVar;
        this.e = cVar;
        this.f = aVar2;
        this.g = aVar3;
        this.h = aVar4;
        this.i = lock;
    }

    private void a(List<com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.c> list) {
        for (com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.c cVar : list) {
            String b2 = cVar.b();
            byte[] c2 = cVar.c();
            if (cVar.a() == 3) {
                this.d.a(b2);
            }
            if (cVar.a() == 2) {
                this.d.a(b2, c2);
            }
        }
    }

    private com.pushwoosh.thirdpart.com.ironz.binaryprefs.j.a b() {
        c();
        d();
        e();
        return this.e.a(new Runnable() {
            /* class com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.AnonymousClass1 */

            public void run() {
                c.this.f();
            }
        });
    }

    private void c() {
        for (String str : this.b) {
            this.h.b(str);
            this.g.c(str);
        }
    }

    private void d() {
        for (String str : this.a.keySet()) {
            Object b2 = this.a.get(str).b();
            this.h.a(str);
            this.g.a(str, b2);
        }
    }

    private void e() {
        if (!this.j) {
            this.j = true;
            return;
        }
        throw new e("Transaction should be applied or committed only once!");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void f() {
        List<com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.c> g2 = g();
        this.c.a(g2);
        a(g2);
    }

    private List<com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.c> g() {
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(h());
        linkedList.addAll(i());
        return linkedList;
    }

    private List<com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.c> h() {
        LinkedList linkedList = new LinkedList();
        for (String str : this.b) {
            linkedList.add(com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.c.a(str));
        }
        return linkedList;
    }

    private List<com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.c> i() {
        Set<String> keySet = this.a.keySet();
        LinkedList linkedList = new LinkedList();
        for (String str : keySet) {
            linkedList.add(com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.c.b(str, this.a.get(str).a()));
        }
        return linkedList;
    }

    /* renamed from: a */
    public f clear() {
        this.i.lock();
        try {
            this.b.addAll(this.h.a());
            return this;
        } finally {
            this.i.unlock();
        }
    }

    /* renamed from: a */
    public f remove(String str) {
        this.i.lock();
        try {
            this.b.add(str);
            return this;
        } finally {
            this.i.unlock();
        }
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.f
    /* renamed from: a */
    public f putFloat(String str, float f2) {
        this.i.lock();
        try {
            this.a.put(str, new com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a.b(f2, this.f));
            return this;
        } finally {
            this.i.unlock();
        }
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.f
    /* renamed from: a */
    public f putInt(String str, int i2) {
        this.i.lock();
        try {
            this.a.put(str, new com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a.c(i2, this.f));
            return this;
        } finally {
            this.i.unlock();
        }
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.f
    /* renamed from: a */
    public f putLong(String str, long j2) {
        this.i.lock();
        try {
            this.a.put(str, new d(j2, this.f));
            return this;
        } finally {
            this.i.unlock();
        }
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.f
    /* renamed from: a */
    public f putString(String str, String str2) {
        if (str2 == null) {
            return remove(str);
        }
        this.i.lock();
        try {
            this.a.put(str, new com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a.e(str2, this.f));
            return this;
        } finally {
            this.i.unlock();
        }
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.f
    /* renamed from: a */
    public f putStringSet(String str, Set<String> set) {
        if (set == null) {
            return remove(str);
        }
        this.i.lock();
        try {
            this.a.put(str, new f(set, this.f));
            return this;
        } finally {
            this.i.unlock();
        }
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.f
    /* renamed from: a */
    public f putBoolean(String str, boolean z) {
        this.i.lock();
        try {
            this.a.put(str, new com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.b.a.a(z, this.f));
            return this;
        } finally {
            this.i.unlock();
        }
    }

    public void apply() {
        this.i.lock();
        try {
            b();
        } finally {
            this.i.unlock();
        }
    }

    public boolean commit() {
        this.i.lock();
        try {
            return b().c();
        } finally {
            this.i.unlock();
        }
    }
}
