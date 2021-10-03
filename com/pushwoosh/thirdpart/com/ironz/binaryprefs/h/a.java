package com.pushwoosh.thirdpart.com.ironz.binaryprefs.h;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.e;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.f;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class a {
    private final List<SharedPreferences> a = new ArrayList();

    @SuppressLint({"ApplySharedPref"})
    private void a(SharedPreferences sharedPreferences, e eVar) {
        Map<String, ?> all = sharedPreferences.getAll();
        if (!all.isEmpty()) {
            f a2 = eVar.a();
            for (String str : all.keySet()) {
                a(all, a2, str);
            }
            if (a2.commit()) {
                sharedPreferences.edit().clear().commit();
            }
        }
    }

    private void a(Map<String, ?> map, f fVar, String str) {
        Object obj = map.get(str);
        if (obj instanceof String) {
            fVar.a(str, (String) obj);
        }
        if (obj instanceof Set) {
            fVar.a(str, (Set) obj);
        }
        if (obj instanceof Integer) {
            fVar.a(str, ((Integer) obj).intValue());
        }
        if (obj instanceof Long) {
            fVar.a(str, ((Long) obj).longValue());
        }
        if (obj instanceof Float) {
            fVar.a(str, ((Float) obj).floatValue());
        }
        if (obj instanceof Boolean) {
            fVar.a(str, ((Boolean) obj).booleanValue());
        }
    }

    public void a(e eVar) {
        for (SharedPreferences sharedPreferences : this.a) {
            a(sharedPreferences, eVar);
        }
    }
}
