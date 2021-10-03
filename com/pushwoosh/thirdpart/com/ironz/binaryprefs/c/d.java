package com.pushwoosh.thirdpart.com.ironz.binaryprefs.c;

import android.content.SharedPreferences;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class d implements b {
    private final List<SharedPreferences.OnSharedPreferenceChangeListener> a;
    private final Handler b = new Handler();

    public d(String str, Map<String, List<SharedPreferences.OnSharedPreferenceChangeListener>> map) {
        this.a = a(str, map);
    }

    private List<SharedPreferences.OnSharedPreferenceChangeListener> a(String str, Map<String, List<SharedPreferences.OnSharedPreferenceChangeListener>> map) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        ArrayList arrayList = new ArrayList();
        map.put(str, arrayList);
        return arrayList;
    }

    private void b(final String str) {
        this.b.post(new Runnable() {
            /* class com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.d.AnonymousClass1 */

            public void run() {
                for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : new ArrayList(d.this.a)) {
                    onSharedPreferenceChangeListener.onSharedPreferenceChanged(null, str);
                }
            }
        });
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.b
    public void a(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.a.add(onSharedPreferenceChangeListener);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.b
    public void a(String str) {
        b(str);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.b
    public void a(String str, byte[] bArr) {
        b(str);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.b
    public void b(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.a.remove(onSharedPreferenceChangeListener);
    }
}
