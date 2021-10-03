package com.pushwoosh.thirdpart.com.ironz.binaryprefs.j;

import com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.c;
import java.util.concurrent.Future;

public final class a {
    private final Future<?> a;
    private final c b;

    a(Future<?> future, c cVar) {
        this.a = future;
        this.b = cVar;
    }

    public Object a(Object obj) {
        try {
            return this.a.get();
        } catch (Exception e) {
            this.b.a(e);
            return obj;
        }
    }

    public void a() {
        try {
            this.a.get();
        } catch (Exception e) {
            throw new com.pushwoosh.thirdpart.com.ironz.binaryprefs.d.a(e);
        }
    }

    public Object b() {
        try {
            return this.a.get();
        } catch (Exception e) {
            throw new com.pushwoosh.thirdpart.com.ironz.binaryprefs.d.a(e);
        }
    }

    public boolean c() {
        try {
            this.a.get();
            return true;
        } catch (Exception e) {
            this.b.a(e);
            return false;
        }
    }
}
