package com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.a;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public final class b implements a {
    private final Set<String> a;

    public b(String str, Map<String, Set<String>> map) {
        this.a = a(str, map);
    }

    private Set<String> a(String str, Map<String, Set<String>> map) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        ConcurrentSkipListSet concurrentSkipListSet = new ConcurrentSkipListSet();
        map.put(str, concurrentSkipListSet);
        return concurrentSkipListSet;
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.a.a
    public Set<String> a() {
        return Collections.unmodifiableSet(this.a);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.a.a
    public void a(String str) {
        this.a.add(str);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.a.a
    public void b(String str) {
        this.a.remove(str);
    }
}
