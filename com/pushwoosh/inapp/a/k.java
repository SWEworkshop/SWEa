package com.pushwoosh.inapp.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.core.app.NotificationManagerCompat;
import com.pushwoosh.inapp.a.a;
import com.pushwoosh.inapp.e.b.b;
import com.pushwoosh.inapp.f.d;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.a.a;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.c;
import com.pushwoosh.internal.utils.j;
import com.pushwoosh.q;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class k {
    public static final String a = "k";
    private Map<String, a> b;
    private SharedPreferences c;
    private HandlerThread d = new HandlerThread("BusinessCasesThread");

    public k(PrefsProvider prefsProvider, a aVar, j jVar, c cVar) {
        float a2 = a(aVar);
        this.d.start();
        this.c = prefsProvider.providePrefs("PWBusinessCasesState");
        if (this.c != null) {
            this.b = new HashMap();
            Map<String, a> map = this.b;
            SharedPreferences sharedPreferences = this.c;
            cVar.getClass();
            map.put("welcome-inapp", new a("welcome-inapp", 0.0f, sharedPreferences, l.a(cVar), jVar));
            Map<String, a> map2 = this.b;
            SharedPreferences sharedPreferences2 = this.c;
            cVar.getClass();
            map2.put("app-update-message", new a("app-update-message", 0.0f, sharedPreferences2, m.a(cVar), jVar));
            this.b.put("push-unregister", new a("push-unregister", a2, this.c, n.a(this), jVar));
            this.b.put("subscription-segments", new a("subscription-segments", a2, this.c, o.a(this), jVar));
        }
    }

    private float a(a aVar) {
        Object obj;
        float f;
        Bundle bundle = aVar.a().metaData;
        if (bundle == null || (obj = bundle.get("com.pushwoosh.in_app_business_solutions_capping")) == null) {
            return 1.0f;
        }
        if (obj instanceof Integer) {
            f = ((Integer) obj).floatValue();
        } else if (obj instanceof Float) {
            f = ((Float) obj).floatValue();
        } else {
            PWLog.error(a, "wrong format capping, capping must be positive number");
            f = 1.0f;
        }
        if (f < 0.0f) {
            PWLog.error(a, "wrong format capping, capping must be positive number");
            f = 1.0f;
        }
        String str = a;
        PWLog.noise(str, "set Up capping:" + f);
        return f;
    }

    public static void a(List<b> list) {
        try {
            HashMap hashMap = new HashMap();
            for (b bVar : list) {
                if (bVar.j() != null && !bVar.j().isEmpty()) {
                    hashMap.put(bVar.j(), i.a(bVar));
                }
            }
            q.d().j().a((Map<String, i>) hashMap, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                HashMap hashMap = new HashMap();
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, i.a(jSONObject.optJSONObject(next)));
                }
                q.d().j().a((Map<String, i>) hashMap, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean b() {
        return q.d().f().b() == null;
    }

    /* access modifiers changed from: private */
    public boolean c() {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        return (applicationContext == null || NotificationManagerCompat.from(applicationContext).areNotificationsEnabled() || q.d().f().b() == null) ? false : true;
    }

    public void a() {
        this.c.edit().clear().apply();
    }

    public void a(String str, a.AbstractC0005a aVar) {
        a aVar2 = this.b.get(str);
        if (aVar2 != null) {
            new Handler(this.d.getLooper()).post(p.a(aVar2, aVar));
        }
    }

    public void a(Map<String, i> map, boolean z) {
        d b2;
        b a2;
        for (a aVar : this.b.values()) {
            i iVar = map.get(aVar.a());
            if (iVar != null && (z || !((b2 = com.pushwoosh.inapp.b.b()) == null || (a2 = b2.a(iVar.a())) == null || a2.d() != iVar.b()))) {
                aVar.a(iVar.a());
            }
        }
    }
}
