package com.pushwoosh.thirdpart.com.ironz.binaryprefs.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Process;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.b.b;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.j.c;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class a implements b {
    private final List<SharedPreferences.OnSharedPreferenceChangeListener> a;
    private final Handler b = new Handler();
    private final Context c;
    private final String d;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.a.a e;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a f;
    private final com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a g;
    private final c h;
    private final b i;
    private final String j;
    private final String k;
    private final int l;
    private final BroadcastReceiver m;
    private final BroadcastReceiver n;

    public a(Context context, String str, com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.a.a aVar, com.pushwoosh.thirdpart.com.ironz.binaryprefs.a.b.a aVar2, com.pushwoosh.thirdpart.com.ironz.binaryprefs.i.a aVar3, c cVar, b bVar, com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.b.b bVar2, Map<String, List<SharedPreferences.OnSharedPreferenceChangeListener>> map) {
        this.c = context;
        this.d = str;
        this.e = aVar;
        this.f = aVar2;
        this.g = aVar3;
        this.h = cVar;
        this.i = bVar;
        this.j = a(bVar2);
        this.k = b(bVar2);
        this.a = a(str, map);
        this.m = b();
        this.n = a();
        this.l = Process.myPid();
    }

    private BroadcastReceiver a() {
        return new BroadcastReceiver() {
            /* class com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.a.AnonymousClass1 */

            public void onReceive(Context context, Intent intent) {
                a.this.b((a) intent);
            }
        };
    }

    private String a(com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.b.b bVar) {
        return "com.ironz.binaryprefs.ACTION_PREFERENCE_UPDATED_" + bVar.a().getAbsolutePath();
    }

    private List<SharedPreferences.OnSharedPreferenceChangeListener> a(String str, Map<String, List<SharedPreferences.OnSharedPreferenceChangeListener>> map) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        ArrayList arrayList = new ArrayList();
        map.put(str, arrayList);
        return arrayList;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(Intent intent) {
        if (this.d.equals(intent.getStringExtra("preference_name")) && this.l != intent.getIntExtra("preference_process_id", 0)) {
            b(intent.getStringExtra("preference_key"), intent.getByteArrayExtra("preference_value"));
        }
    }

    private void a(String str, Object obj) {
        this.e.a(str);
        this.f.a(str, obj);
        c(str);
    }

    private BroadcastReceiver b() {
        return new BroadcastReceiver() {
            /* class com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.a.AnonymousClass2 */

            public void onReceive(Context context, Intent intent) {
                a.this.a((a) intent);
            }
        };
    }

    private String b(com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.b.b bVar) {
        return "com.ironz.binaryprefs.ACTION_PREFERENCE_REMOVED_" + bVar.a().getAbsolutePath();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(Intent intent) {
        if (this.d.equals(intent.getStringExtra("preference_name")) && this.l != intent.getIntExtra("preference_process_id", 0)) {
            c(intent);
        }
    }

    private void b(String str) {
        this.e.b(str);
        this.f.c(str);
        c(str);
    }

    private void b(final String str, final byte[] bArr) {
        this.h.a(new Runnable() {
            /* class com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.a.AnonymousClass3 */

            public void run() {
                a.this.c((a) str, (String) bArr);
            }
        });
    }

    private void c() {
        this.c.registerReceiver(this.m, new IntentFilter(this.j));
        this.c.registerReceiver(this.n, new IntentFilter(this.k));
    }

    private void c(final Intent intent) {
        this.h.a(new Runnable() {
            /* class com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.a.AnonymousClass4 */

            public void run() {
                a.this.d((a) intent);
            }
        });
    }

    private void c(final String str) {
        this.b.post(new Runnable() {
            /* class com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.a.AnonymousClass5 */

            public void run() {
                a.this.d((a) str);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c(String str, byte[] bArr) {
        a(str, d(str, bArr));
    }

    private Object d(String str, byte[] bArr) {
        return this.g.a(str, this.i.b(bArr));
    }

    private void d() {
        this.c.unregisterReceiver(this.m);
        this.c.unregisterReceiver(this.n);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d(Intent intent) {
        b(intent.getStringExtra("preference_key"));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d(String str) {
        for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : this.a) {
            onSharedPreferenceChangeListener.onSharedPreferenceChanged(null, str);
        }
    }

    private void e(final String str) {
        this.h.a(new Runnable() {
            /* class com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.a.AnonymousClass7 */

            public void run() {
                a.this.f(str);
            }
        });
    }

    private void e(final String str, final byte[] bArr) {
        this.h.a(new Runnable() {
            /* class com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.a.AnonymousClass6 */

            public void run() {
                a.this.f(str, bArr);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void f(String str) {
        Intent intent = new Intent(this.k);
        intent.putExtra("preference_process_id", this.l);
        intent.putExtra("preference_name", this.d);
        intent.putExtra("preference_key", str);
        this.c.sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void f(String str, byte[] bArr) {
        Intent intent = new Intent(this.j);
        intent.putExtra("preference_process_id", this.l);
        intent.putExtra("preference_name", this.d);
        intent.putExtra("preference_key", str);
        intent.putExtra("preference_value", bArr);
        this.c.sendBroadcast(intent);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.b
    public void a(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        if (this.a.isEmpty()) {
            c();
        }
        this.a.add(onSharedPreferenceChangeListener);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.b
    public void a(String str) {
        c(str);
        e(str);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.b
    public void a(String str, byte[] bArr) {
        c(str);
        e(str, bArr);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.b
    public void b(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.a.remove(onSharedPreferenceChangeListener);
        if (this.a.isEmpty()) {
            d();
        }
    }
}
