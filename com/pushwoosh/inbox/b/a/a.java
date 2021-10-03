package com.pushwoosh.inbox.b.a;

import android.text.TextUtils;
import com.pushwoosh.internal.checker.Checker;
import com.pushwoosh.repository.RepositoryModule;
import java.util.concurrent.atomic.AtomicBoolean;

public class a implements Checker {
    private final AtomicBoolean a = new AtomicBoolean(false);
    private long b;
    private final Object c = new Object();
    private String d;
    private final long e;

    public a(long j) {
        this.e = j;
    }

    public boolean a() {
        return this.a.get();
    }

    public void b() {
        synchronized (this.c) {
            this.b = System.currentTimeMillis();
            this.a.set(true);
        }
    }

    public void c() {
        synchronized (this.c) {
            this.a.set(false);
        }
    }

    @Override // com.pushwoosh.internal.checker.Checker
    public boolean check() {
        boolean z;
        synchronized (this.c) {
            String str = RepositoryModule.getRegistrationPreferences().userId().get();
            if (this.d != null && !TextUtils.equals(str, this.d)) {
                this.b = 0;
            }
            this.d = str;
            z = System.currentTimeMillis() - this.b > this.e;
        }
        return z;
    }
}
