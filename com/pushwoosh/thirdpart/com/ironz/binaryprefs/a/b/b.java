package com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class b implements a {
    private final Map<String, Object> a;

    public b(String str, Map<String, Map<String, Object>> map) {
        this.a = a(str, map);
    }

    private Map<String, Object> a(String str, Map<String, Map<String, Object>> map) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        map.put(str, concurrentHashMap);
        return concurrentHashMap;
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a
    public Set<String> a() {
        return Collections.unmodifiableSet(this.a.keySet());
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a
    public void a(String str, Object obj) {
        this.a.put(str, obj);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a
    public boolean a(String str) {
        return this.a.containsKey(str);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a
    public Object b(String str) {
        return this.a.get(str);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a
    public Map<String, Object> b() {
        return this.a;
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a
    public void c(String str) {
        this.a.remove(str);
    }
}
