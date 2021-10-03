package com.pushwoosh.thirdpart.com.ironz.binaryprefs.f;

import com.pushwoosh.thirdpart.com.ironz.binaryprefs.j.c;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

public final class a implements b {
    private final Lock a;
    private final c b;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.a.a c;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a d;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.a e;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a f;

    public a(com.pushwoosh.thirdpart.com.ironz.binaryprefs.g.a aVar, c cVar, com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.a.a aVar2, com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a aVar3, com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.a aVar4, com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a aVar5) {
        this.a = aVar.a();
        this.b = cVar;
        this.c = aVar2;
        this.d = aVar3;
        this.e = aVar4;
        this.f = aVar5;
        b();
    }

    private void b() {
        this.e.a();
        this.a.lock();
        try {
            this.b.a(new Runnable() {
                /* class com.pushwoosh.thirdpart.com.ironz.binaryprefs.f.a.AnonymousClass1 */

                public void run() {
                    a.this.c();
                }
            }).a();
        } finally {
            this.e.b();
            this.a.unlock();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c() {
        if (d()) {
            for (com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.c cVar : this.e.c()) {
                String b2 = cVar.b();
                this.d.a(b2, this.f.a(b2, cVar.c()));
                this.c.a(b2);
            }
        }
    }

    private boolean d() {
        return !this.d.a().containsAll(this.c.a());
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.f.b
    public Object a(String str, Object obj) {
        this.a.lock();
        try {
            Object b2 = this.d.b(str);
            if (b2 == null) {
                return obj;
            }
            Object a2 = this.f.a(b2);
            this.a.unlock();
            return a2;
        } finally {
            this.a.unlock();
        }
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.f.b
    public Map<String, Object> a() {
        this.a.lock();
        try {
            Map<String, Object> b2 = this.d.b();
            HashMap hashMap = new HashMap(b2.size());
            for (String str : b2.keySet()) {
                hashMap.put(str, this.f.a(b2.get(str)));
            }
            return Collections.unmodifiableMap(hashMap);
        } finally {
            this.a.unlock();
        }
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.f.b
    public boolean a(String str) {
        this.a.lock();
        try {
            return this.d.a(str);
        } finally {
            this.a.unlock();
        }
    }
}
