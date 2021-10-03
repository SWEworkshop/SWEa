package com.pushwoosh.thirdpart.com.ironz.binaryprefs;

import android.content.SharedPreferences;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.b;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.e;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.j.c;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;

/* access modifiers changed from: package-private */
public final class a implements e {
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.a a;
    private final b b;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.a.a c;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a d;
    private final c e;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a f;
    private final Lock g;
    private final Lock h;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.f.b i;

    a(com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.c.a aVar, b bVar, com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.a.a aVar2, com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a aVar3, c cVar, com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a aVar4, com.pushwoosh.thirdpart.com.ironz.binaryprefs.g.a aVar5, com.pushwoosh.thirdpart.com.ironz.binaryprefs.f.b bVar2) {
        this.a = aVar;
        this.b = bVar;
        this.c = aVar2;
        this.d = aVar3;
        this.e = cVar;
        this.f = aVar4;
        this.g = aVar5.a();
        this.h = aVar5.b();
        this.i = bVar2;
    }

    private f b() {
        this.g.lock();
        try {
            return new c(this.a, this.b, this.e, this.f, this.d, this.c, this.h);
        } finally {
            this.g.unlock();
        }
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.e
    /* renamed from: a */
    public f edit() {
        return b();
    }

    public boolean contains(String str) {
        return this.i.a(str);
    }

    @Override // android.content.SharedPreferences, com.pushwoosh.thirdpart.com.ironz.binaryprefs.e
    public Map<String, Object> getAll() {
        return this.i.a();
    }

    public boolean getBoolean(String str, boolean z) {
        return ((Boolean) this.i.a(str, Boolean.valueOf(z))).booleanValue();
    }

    public float getFloat(String str, float f2) {
        return ((Float) this.i.a(str, Float.valueOf(f2))).floatValue();
    }

    public int getInt(String str, int i2) {
        return ((Integer) this.i.a(str, Integer.valueOf(i2))).intValue();
    }

    public long getLong(String str, long j) {
        return ((Long) this.i.a(str, Long.valueOf(j))).longValue();
    }

    public String getString(String str, String str2) {
        return (String) this.i.a(str, str2);
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String str, Set<String> set) {
        return (Set) this.i.a(str, set);
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.h.lock();
        try {
            this.b.a(new e(this, onSharedPreferenceChangeListener));
        } finally {
            this.h.unlock();
        }
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.h.lock();
        try {
            this.b.b(new e(this, onSharedPreferenceChangeListener));
        } finally {
            this.h.unlock();
        }
    }
}
