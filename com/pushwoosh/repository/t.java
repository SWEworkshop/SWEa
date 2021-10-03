package com.pushwoosh.repository;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.exception.GetTagsException;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.a.a;
import com.pushwoosh.inapp.a.k;
import com.pushwoosh.internal.event.ConfigLoadedEvent;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.network.f;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.q;
import com.pushwoosh.repository.config.Channel;
import com.pushwoosh.repository.config.a;
import com.pushwoosh.repository.config.b;
import com.pushwoosh.repository.config.c;
import com.pushwoosh.tags.Tags;
import com.pushwoosh.tags.TagsBundle;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class t {
    @Nullable
    private final RequestManager a;
    private final z b;
    private final RegistrationPrefs c;
    private final q d;
    private final f e;
    private String f;
    private List<Channel> g;
    private List<b> h;
    private String i;

    public t(RequestManager requestManager, z zVar, RegistrationPrefs registrationPrefs, q qVar, f fVar) {
        JSONObject jSONObject;
        this.a = requestManager;
        this.b = zVar;
        this.c = registrationPrefs;
        this.d = qVar;
        this.e = fVar;
        if (registrationPrefs.setTagsFailed().get() && (jSONObject = qVar.p().get()) != null) {
            PWLog.debug("Resending application tags");
            zVar.a(jSONObject, u.a(registrationPrefs));
        }
    }

    static /* synthetic */ void a(RegistrationPrefs registrationPrefs, Result result) {
        if (result.isSuccess()) {
            registrationPrefs.setTagsFailed().set(false);
        }
    }

    static /* synthetic */ void a(@Nullable t tVar, Callback callback, Result result) {
        Result result2;
        if (callback != null) {
            if (result.isSuccess()) {
                TagsBundle empty = result.getData() == null ? Tags.empty() : (TagsBundle) result.getData();
                tVar.d.p().set(empty.toJson());
                result2 = Result.fromData(empty);
            } else {
                JSONObject jSONObject = tVar.d.p().get();
                if (jSONObject != null) {
                    result2 = Result.fromData(Tags.fromJson(jSONObject));
                } else {
                    result2 = Result.fromException(new GetTagsException(result.getException() == null ? "" : ((NetworkException) result.getException()).getMessage()));
                }
            }
            callback.process(result2);
        }
    }

    static /* synthetic */ void a(t tVar, Result result) {
        a aVar = (a) result.getData();
        if (aVar != null) {
            tVar.g = aVar.a();
            tVar.h = aVar.b();
            tVar.i = aVar.c();
            EventBus.sendEvent(new ConfigLoadedEvent());
        }
    }

    static /* synthetic */ void b(t tVar, Result result) {
        if (result.isSuccess()) {
            tVar.c.lastAttributesRegistration().set(new Date().getTime());
        }
    }

    private boolean o() {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.add(10, -24);
        Calendar instance3 = Calendar.getInstance();
        instance3.setTime(new Date(this.c.lastAttributesRegistration().get()));
        return !instance2.before(instance3) || !instance3.before(instance);
    }

    public String a() {
        return this.f;
    }

    public void a(@Nullable Callback<TagsBundle, GetTagsException> callback) {
        g gVar = new g();
        RequestManager requestManager = this.a;
        if (requestManager != null) {
            requestManager.sendRequest(gVar, x.a(this, callback));
        } else if (callback != null) {
            callback.process(Result.fromException(new GetTagsException("Request Manager is null")));
        }
    }

    public void a(@NonNull TagsBundle tagsBundle, Callback<Void, PushwooshException> callback) {
        JSONObject json = tagsBundle.toJson();
        try {
            this.d.p().merge(json);
        } catch (Exception e2) {
            PWLog.exception(e2);
        }
        this.b.a(json, callback);
    }

    public void a(String str) {
        this.f = str;
    }

    public void a(String str, String str2) {
        if (str == null || !TextUtils.equals(str, this.d.j().get())) {
            this.d.j().set(str);
            s sVar = new s(str, str2);
            RequestManager requestManager = this.a;
            if (requestManager != null) {
                requestManager.sendRequest(sVar, new com.pushwoosh.function.a(sVar, this.e));
                return;
            }
            return;
        }
        PWLog.warn("Push stat for (" + str + ") already sent");
    }

    public void a(String str, BigDecimal bigDecimal, String str2, Date date) {
        ad adVar = new ad(str, bigDecimal, str2, date);
        RequestManager requestManager = this.a;
        if (requestManager != null) {
            requestManager.sendRequest(adVar, new com.pushwoosh.function.a(adVar, this.e));
        }
    }

    public void a(boolean z) {
        this.c.communicationEnable().set(z);
    }

    public void b() {
        a aVar = new a();
        RequestManager requestManager = this.a;
        if (requestManager != null) {
            requestManager.sendRequest(aVar, new com.pushwoosh.function.a(aVar, this.e));
            k j = q.d().j();
            j.a("welcome-inapp", (a.AbstractC0005a) null);
            j.a("app-update-message", (a.AbstractC0005a) null);
        }
    }

    public void b(String str) {
        p pVar = new p(str);
        RequestManager requestManager = this.a;
        if (requestManager != null) {
            requestManager.sendRequest(pVar, new com.pushwoosh.function.a(pVar, this.e));
        }
    }

    public void c() {
        RequestManager requestManager;
        if (o() && (requestManager = this.a) != null) {
            requestManager.sendRequest(new ab(null), v.a(this));
        }
    }

    public void d() {
        RequestManager requestManager = this.a;
        if (requestManager != null) {
            requestManager.sendRequest(new c(), w.a(this));
        }
    }

    public List<Channel> e() {
        return this.g;
    }

    public List<b> f() {
        return this.h;
    }

    public String g() {
        return this.i;
    }

    public void h() {
        g gVar = new g();
        RequestManager requestManager = this.a;
        if (requestManager != null) {
            Result sendRequestSync = requestManager.sendRequestSync(gVar);
            if (sendRequestSync.isSuccess() && sendRequestSync.getData() != null) {
                JSONObject json = ((TagsBundle) sendRequestSync.getData()).toJson();
                if (json.length() > 0) {
                    this.d.p().set(json);
                }
            }
        }
    }

    public List<PushMessage> i() {
        ArrayList<String> arrayList = this.d.o().get();
        ArrayList arrayList2 = new ArrayList();
        for (String str : arrayList) {
            Bundle bundle = new Bundle();
            try {
                JSONObject jSONObject = new JSONObject(str);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (jSONObject.get(next) instanceof String) {
                        bundle.putString(next, jSONObject.getString(next));
                    }
                }
                arrayList2.add(new PushMessage(bundle));
            } catch (Exception e2) {
                PWLog.exception(e2);
            }
        }
        return arrayList2;
    }

    public void j() {
        this.d.p().set(null);
        this.c.removeAllDeviceData().set(true);
    }

    public boolean k() {
        return this.c.removeAllDeviceData().get();
    }

    public boolean l() {
        return this.c.communicationEnable().get();
    }

    public boolean m() {
        return this.c.gdprEnable().get();
    }

    public String n() {
        return this.c.hwid().get();
    }
}
