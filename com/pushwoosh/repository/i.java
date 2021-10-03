package com.pushwoosh.repository;

import android.text.TextUtils;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.platform.utils.a;
import com.pushwoosh.internal.preference.PreferenceBooleanValue;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.c;
import com.pushwoosh.notification.e;
import com.pushwoosh.notification.f;
import com.pushwoosh.tags.TagsBundle;
import org.json.JSONObject;

public class i {
    private static final String a = "i";
    private RequestManager b;
    private z c;
    private PreferenceBooleanValue d;
    private c e;
    private e f;
    private RegistrationPrefs g;
    private d h;
    private boolean i;
    private boolean j;
    private TagsBundle k;
    private String l;
    private String m;

    public i(RequestManager requestManager, z zVar, PreferenceBooleanValue preferenceBooleanValue, c cVar, e eVar, RegistrationPrefs registrationPrefs, d dVar) {
        this.b = requestManager;
        this.c = zVar;
        this.d = preferenceBooleanValue;
        this.e = cVar;
        this.f = eVar;
        this.g = registrationPrefs;
        this.h = dVar;
    }

    /* access modifiers changed from: private */
    public void a(Result<Void, PushwooshException> result) {
        if (result.isSuccess()) {
            this.d.set(true);
            PWLog.noise(a, "migration success");
        }
    }

    static /* synthetic */ void a(i iVar, Result result) {
        if (result.isSuccess()) {
            if (((TagsBundle) result.getData()).getMap().size() > 0) {
                iVar.k = (TagsBundle) result.getData();
            } else {
                iVar.d.set(true);
                PWLog.debug(a, "getTags empty");
            }
            synchronized (iVar) {
                iVar.i = true;
                if (iVar.j) {
                    iVar.b();
                }
            }
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(((NetworkException) result.getException()).getMessage());
            int i2 = jSONObject.getInt("status_code");
            String string = jSONObject.getString("status_message");
            if (i2 == 210 && string.equals("Device not found")) {
                iVar.d.set(true);
                PWLog.debug(a, "getTags returned \"Device not found\"");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void c() {
        this.b.sendRequest(new h(this.d.get() ? this.l : a.d()), k.a(this));
    }

    private boolean d() {
        String b2 = this.f.b();
        return b2 != null && !b2.isEmpty() && this.g.isRegisteredForPush().get();
    }

    /* access modifiers changed from: private */
    public void e() {
        TagsBundle tagsBundle = this.k;
        if (tagsBundle != null) {
            JSONObject json = tagsBundle.toJson();
            String str = a;
            PWLog.noise(str, "data for migration:" + json.toString());
            this.c.a(json, l.a(this));
        }
    }

    public void a() {
        PWLog.noise(a, "prepare migration");
        String d2 = a.d();
        if (this.e.e() || TextUtils.isEmpty(d2)) {
            this.d.set(true);
        }
        if (!this.d.get()) {
            c();
        }
    }

    public void a(String str, String str2) {
        if ((!this.d.get() || !str.equals(str2)) && !str2.isEmpty()) {
            this.l = str2;
            this.m = str;
            if (!this.d.get()) {
                synchronized (this) {
                    this.j = true;
                    if (this.i) {
                        b();
                    }
                }
                return;
            }
            synchronized (this) {
                this.j = true;
            }
            c();
            return;
        }
        PWLog.noise(a, "migration tags already done");
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (d()) {
            if (!this.m.equals(this.l)) {
                this.g.lastPushRegistration().set(0);
            }
            if (this.g.lastPushRegistration().get() == 0) {
                f.a(j.a(this));
                this.h.a();
                return;
            }
        }
        e();
    }
}
