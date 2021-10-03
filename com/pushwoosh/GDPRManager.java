package com.pushwoosh;

import androidx.core.app.NotificationCompat;
import com.pushwoosh.exception.GetTagsException;
import com.pushwoosh.exception.PostEventException;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.c;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.e;
import com.pushwoosh.repository.t;
import com.pushwoosh.tags.TagsBundle;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GDPRManager {
    public static final String TAG = "GDPRManager";
    private t a;
    private e b;
    private c c;
    private Set<String> d = new HashSet(Arrays.asList("Application Version", "Language", "Last Application Open", "First Install"));

    GDPRManager(t tVar, e eVar, c cVar) {
        this.a = tVar;
        this.b = eVar;
        this.c = cVar;
    }

    private TagsBundle a(TagsBundle tagsBundle) {
        TagsBundle.Builder builder = new TagsBundle.Builder();
        for (String str : tagsBundle.getMap().keySet()) {
            builder.putString(str, null);
        }
        return builder.build();
    }

    static /* synthetic */ void a(GDPRManager gDPRManager, Callback callback, Result result) {
        gDPRManager.a(callback, result.getException());
        if (result.isSuccess()) {
            gDPRManager.a.j();
        }
    }

    private void a(Callback<Void, PushwooshException> callback) {
        PWLog.debug(TAG, "The GDPR solution isn’t available for this account");
        if (callback != null) {
            callback.process(Result.fromException(new PushwooshException("The GDPR solution isn’t available for this account")));
        }
    }

    /* access modifiers changed from: private */
    public void a(Callback<Void, PushwooshException> callback, PushwooshException pushwooshException) {
        if (callback != null) {
            callback.process(pushwooshException != null ? Result.fromException(pushwooshException) : Result.fromData(null));
        }
    }

    /* access modifiers changed from: private */
    public void a(Callback<Void, PushwooshException> callback, Result<TagsBundle, GetTagsException> result) {
        if (result.isSuccess()) {
            this.a.a(a(result.getData()), h.a(this, callback));
        } else if (callback != null) {
            callback.process(Result.fromException(result.getException()));
        }
    }

    /* access modifiers changed from: private */
    public void a(Result<Void, PostEventException> result, Callback<Void, PushwooshException> callback) {
        if (result.isSuccess()) {
            this.a.a(g.a(this, callback));
            return;
        }
        if (callback != null) {
            callback.process(Result.fromException(result.getException()));
        }
        PWLog.error(TAG, "cant remove all device data", result.getException());
    }

    /* access modifiers changed from: private */
    public void a(boolean z, Result<Void, PostEventException> result, Callback<Void, PushwooshException> callback) {
        if (!result.isSuccess()) {
            String str = TAG;
            PWLog.error(str, "cant set Communication Enable to " + z, result.getException());
            if (callback != null) {
                callback.process(Result.fromException(result.getException()));
                return;
            }
            return;
        }
        this.a.a(z);
        if (z) {
            this.b.a(d.a(this, callback));
        } else {
            this.b.b(e.a(this, callback));
        }
    }

    /* access modifiers changed from: private */
    public void b(Result<Void, PushwooshException> result, Callback<Void, PushwooshException> callback) {
        if (result.isSuccess()) {
            this.b.b(i.a(this, callback));
        } else if (callback != null) {
            callback.process(result);
        }
    }

    public static GDPRManager getInstance() {
        return q.d().k();
    }

    public boolean isAvailable() {
        PWLog.debug(TAG, "isAvailable");
        return this.a.m();
    }

    public boolean isCommunicationEnabled() {
        PWLog.debug(TAG, "isCommunicationEnabled");
        return this.a.l();
    }

    public boolean isDeviceDataRemoved() {
        PWLog.debug(TAG, "isDeviceDataRemoved");
        return this.a.k();
    }

    public void removeAllDeviceData(Callback<Void, PushwooshException> callback) {
        if (!isAvailable()) {
            a(callback);
            return;
        }
        TagsBundle build = new TagsBundle.Builder().putBoolean(NotificationCompat.CATEGORY_STATUS, true).putInt("device_type", 3).build();
        c cVar = this.c;
        if (cVar != null) {
            cVar.a("GDPRDelete", build, f.a(this, callback));
        }
    }

    public void setCommunicationEnabled(boolean z, Callback<Void, PushwooshException> callback) {
        if (!isAvailable()) {
            a(callback);
            return;
        }
        this.c.a("GDPRConsent", new TagsBundle.Builder().putBoolean("channel", z).putInt("device_type", 3).build(), c.a(this, z, callback));
    }

    public void showGDPRConsentUI() {
        PWLog.debug(TAG, "showGDPRConsentUI");
        this.c.c();
    }

    public void showGDPRDeletionUI() {
        PWLog.debug(TAG, "showGDPRDeletionUI");
        this.c.d();
    }
}
