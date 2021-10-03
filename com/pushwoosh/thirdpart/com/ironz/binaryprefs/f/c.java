package com.pushwoosh.thirdpart.com.ironz.binaryprefs.f;

import com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.a.a;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

public final class c implements b {
    private final Lock a;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.j.c b;
    private final a c;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a d;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.a e;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a f;

    public c(com.pushwoosh.thirdpart.com.ironz.binaryprefs.g.a aVar, com.pushwoosh.thirdpart.com.ironz.binaryprefs.j.c cVar, a aVar2, com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a aVar3, com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.a aVar4, com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a aVar5) {
        this.a = aVar.a();
        this.b = cVar;
        this.c = aVar2;
        this.d = aVar3;
        this.e = aVar4;
        this.f = aVar5;
        b();
    }

    private Object b(final String str) {
        Object b2 = this.d.b(str);
        if (b2 != null) {
            return b2;
        }
        this.e.a();
        try {
            return this.b.a(new Callable<Object>() {
                /* class com.pushwoosh.thirdpart.com.ironz.binaryprefs.f.c.AnonymousClass1 */

                @Override // java.util.concurrent.Callable
                public Object call() throws Exception {
                    return c.this.c(str);
                }
            }).b();
        } finally {
            this.e.b();
        }
    }

    private Object b(final String str, Object obj) {
        Object b2 = this.d.b(str);
        if (b2 != null) {
            return b2;
        }
        if (!this.c.a().contains(str)) {
            return obj;
        }
        this.e.a();
        try {
            return this.b.a(new Callable<Object>() {
                /* class com.pushwoosh.thirdpart.com.ironz.binaryprefs.f.c.AnonymousClass2 */

                @Override // java.util.concurrent.Callable
                public Object call() throws Exception {
                    return c.this.c(str);
                }
            }).a(obj);
        } finally {
            this.e.b();
        }
    }

    private void b() {
        this.a.lock();
        try {
            for (String str : this.e.d()) {
                this.c.a(str);
            }
        } finally {
            this.a.unlock();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Object c(String str) {
        Object a2 = this.f.a(str, this.e.a(str).c());
        this.d.a(str, a2);
        return a2;
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.f.b
    public Object a(String str, Object obj) {
        this.a.lock();
        try {
            return this.f.a(b(str, obj));
        } finally {
            this.a.unlock();
        }
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.f.b
    public Map<String, Object> a() {
        this.a.lock();
        try {
            Set<String> a2 = this.c.a();
            HashMap hashMap = new HashMap(a2.size());
            for (String str : a2) {
                hashMap.put(str, this.f.a(b(str)));
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
            return this.c.a().contains(str) && this.d.a(str);
        } finally {
            this.a.unlock();
        }
    }
}
