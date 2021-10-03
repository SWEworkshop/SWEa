package com.pushwoosh.thirdpart.com.ironz.binaryprefs;

import android.content.SharedPreferences;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/* access modifiers changed from: package-private */
public final class d {
    private static final Map<String, ReadWriteLock> a = new ConcurrentHashMap();
    private static final Map<String, Lock> b = new ConcurrentHashMap();
    private static final Map<String, Map<String, Object>> c = new ConcurrentHashMap();
    private static final Map<String, Set<String>> d = new ConcurrentHashMap();
    private static final Map<String, List<SharedPreferences.OnSharedPreferenceChangeListener>> e = new ConcurrentHashMap();
    private static final Map<String, ExecutorService> f = new ConcurrentHashMap();

    d() {
    }

    /* access modifiers changed from: package-private */
    public Map<String, ReadWriteLock> a() {
        return a;
    }

    /* access modifiers changed from: package-private */
    public Map<String, Lock> b() {
        return b;
    }

    /* access modifiers changed from: package-private */
    public Map<String, Map<String, Object>> c() {
        return c;
    }

    /* access modifiers changed from: package-private */
    public Map<String, List<SharedPreferences.OnSharedPreferenceChangeListener>> d() {
        return e;
    }

    /* access modifiers changed from: package-private */
    public Map<String, ExecutorService> e() {
        return f;
    }

    /* access modifiers changed from: package-private */
    public Map<String, Set<String>> f() {
        return d;
    }
}
