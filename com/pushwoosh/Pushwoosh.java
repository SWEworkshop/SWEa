package com.pushwoosh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.exception.GetTagsException;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.exception.RegisterForPushNotificationsException;
import com.pushwoosh.exception.UnregisterForPushNotificationException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.a.a;
import com.pushwoosh.inapp.a.j;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.Subscription;
import com.pushwoosh.notification.LocalNotification;
import com.pushwoosh.notification.LocalNotificationRequest;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.notification.e;
import com.pushwoosh.notification.event.RegistrationSuccessEvent;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.t;
import com.pushwoosh.tags.TagsBundle;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Pushwoosh {
    public static final int PUSH_HISTORY_CAPACITY = 16;
    public static final String PUSH_RECEIVE_EVENT = "PUSH_RECEIVE_EVENT";
    private static final Pushwoosh a = new Pushwoosh();
    private final e b;
    private final t c;
    private final GDPRManager d;
    private Subscription<RegistrationSuccessEvent> e;
    private AtomicBoolean f;

    private Pushwoosh() {
        q d2 = q.d();
        if (d2 == null) {
            q.a();
            this.b = null;
            this.c = null;
            this.d = null;
            return;
        }
        this.b = d2.f();
        this.c = d2.g();
        this.d = d2.k();
        this.f = new AtomicBoolean();
    }

    private void a() {
        Subscription<RegistrationSuccessEvent> subscription = this.e;
        if (subscription != null) {
            subscription.unsubscribe();
            this.e = null;
        }
    }

    static /* synthetic */ void a(Pushwoosh pushwoosh, Callback callback, RegistrationSuccessEvent registrationSuccessEvent) {
        pushwoosh.a();
        callback.process(Result.fromData(registrationSuccessEvent.getData()));
    }

    static /* synthetic */ void a(Pushwoosh pushwoosh, AtomicBoolean atomicBoolean, Callback callback, j jVar) {
        pushwoosh.f.set(false);
        if (jVar != j.RICHMEDIA_CLOSED) {
            atomicBoolean.set(true);
            pushwoosh.a();
            if (jVar != j.TRIGGER_CAP_EXCEEDED) {
                pushwoosh.b.a(callback);
            } else if (callback != null) {
                callback.process(Result.from(null, new RegisterForPushNotificationsException("Stopped by in-app")));
            }
        }
    }

    private void a(Callback<String, RegisterForPushNotificationsException> callback) {
        if (callback != null) {
            this.e = EventBus.subscribe(RegistrationSuccessEvent.class, o.a(this, callback));
        }
    }

    @NonNull
    public static Pushwoosh getInstance() {
        return a;
    }

    public void clearLaunchNotification() {
        e eVar = this.b;
        if (eVar != null) {
            eVar.d();
        }
    }

    public void clearPushHistory() {
        if (this.c != null) {
            RepositoryModule.getNotificationPreferences().o().clear();
        }
    }

    public String getAppId() {
        return this.c != null ? RepositoryModule.getRegistrationPreferences().applicationId().get() : "";
    }

    @NonNull
    public String getHwid() {
        return this.b != null ? this.c.n() : "";
    }

    public String getLanguage() {
        return this.c != null ? RepositoryModule.getRegistrationPreferences().language().get() : "";
    }

    @Nullable
    public PushMessage getLaunchNotification() {
        e eVar = this.b;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    @NonNull
    public List<PushMessage> getPushHistory() {
        t tVar = this.c;
        return tVar != null ? tVar.i() : new ArrayList();
    }

    @Nullable
    public String getPushToken() {
        e eVar = this.b;
        return eVar != null ? eVar.b() : "";
    }

    public String getSenderId() {
        return this.b != null ? RepositoryModule.getRegistrationPreferences().projectId().get() : "";
    }

    public void getTags(@NonNull Callback<TagsBundle, GetTagsException> callback) {
        t tVar = this.c;
        if (tVar != null) {
            tVar.a(callback);
        }
    }

    public void registerForPushNotifications() {
        registerForPushNotifications(null);
    }

    public void registerForPushNotifications(Callback<String, RegisterForPushNotificationsException> callback) {
        if (this.b == null) {
            return;
        }
        if (!this.f.get()) {
            this.f.set(true);
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            q.d().j().a("subscription-segments", n.a(this, atomicBoolean, callback));
            if (!atomicBoolean.get()) {
                a(callback);
            }
            q.d().j().a("push-unregister", (a.AbstractC0005a) null);
            return;
        }
        this.b.a(callback);
    }

    @NonNull
    public LocalNotificationRequest scheduleLocalNotification(LocalNotification localNotification) {
        e eVar = this.b;
        if (eVar != null) {
            return eVar.a(localNotification);
        }
        return null;
    }

    public void sendAppOpen() {
        t tVar = this.c;
        if (tVar != null) {
            tVar.b();
        }
    }

    public void sendInappPurchase(@NonNull String str, @NonNull BigDecimal bigDecimal, @NonNull String str2) {
        t tVar = this.c;
        if (tVar != null) {
            tVar.a(str, bigDecimal, str2, new Date());
        }
    }

    public void sendTags(@NonNull TagsBundle tagsBundle) {
        sendTags(tagsBundle, null);
    }

    public void sendTags(@NonNull TagsBundle tagsBundle, Callback<Void, PushwooshException> callback) {
        t tVar = this.c;
        if (tVar != null) {
            tVar.a(tagsBundle, callback);
        }
    }

    public void setAppId(@NonNull String str) {
        e eVar = this.b;
        if (eVar != null) {
            eVar.a(str);
        }
    }

    public void setLanguage(@Nullable String str) {
        RepositoryModule.getRegistrationPreferences().setLanguage(str);
    }

    public void setSenderId(@NonNull String str) {
        e eVar = this.b;
        if (eVar != null) {
            eVar.b(str);
        }
    }

    public void unregisterForPushNotifications() {
        unregisterForPushNotifications(null);
    }

    public void unregisterForPushNotifications(Callback<String, UnregisterForPushNotificationException> callback) {
        e eVar = this.b;
        if (eVar != null) {
            eVar.b(callback);
        }
    }
}
