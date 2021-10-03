package com.pushwoosh.notification;

import android.text.TextUtils;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import com.pushwoosh.PushwooshWorkManagerHelper;
import com.pushwoosh.exception.RegisterForPushNotificationsException;
import com.pushwoosh.exception.UnregisterForPushNotificationException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.internal.event.AppIdChangedEvent;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.registrar.PushRegistrar;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.d;
import com.pushwoosh.notification.event.DeregistrationErrorEvent;
import com.pushwoosh.notification.event.RegistrationErrorEvent;
import com.pushwoosh.notification.event.RegistrationSuccessEvent;
import com.pushwoosh.notification.handlers.message.system.MessageSystemHandleChainProvider;
import com.pushwoosh.notification.handlers.message.user.MessageHandleChainProvider;
import com.pushwoosh.notification.handlers.notification.NotificationOpenHandlerChainProvider;
import com.pushwoosh.q;
import com.pushwoosh.repository.RegistrationPrefs;
import com.pushwoosh.repository.RepositoryModule;
import java.util.concurrent.atomic.AtomicBoolean;

public class e {
    private final RegistrationPrefs a;
    private final PushRegistrar b;
    private PushMessage c;
    private d d;
    private AtomicBoolean e = new AtomicBoolean(false);
    private AtomicBoolean f = new AtomicBoolean(false);

    public static class a implements Event {
        a() {
        }
    }

    public e(PushRegistrar pushRegistrar, d dVar) {
        this.d = dVar;
        this.b = pushRegistrar;
        this.a = RepositoryModule.getRegistrationPreferences();
    }

    public LocalNotificationRequest a(LocalNotification localNotification) {
        return new LocalNotificationRequest(LocalNotificationReceiver.scheduleNotification(localNotification.b(), localNotification.a()));
    }

    public void a() {
        MessageHandleChainProvider.init();
        MessageSystemHandleChainProvider.init();
        NotificationOpenHandlerChainProvider.init();
        this.b.init();
        String a2 = TextUtils.isEmpty(this.d.a()) ? this.a.applicationId().get() : this.d.a();
        String projectId = DeviceSpecificProvider.getInstance().projectId();
        if (!TextUtils.isEmpty(projectId)) {
            b(projectId);
        }
        if (!TextUtils.isEmpty(a2)) {
            a(a2);
        }
    }

    public void a(Callback<String, RegisterForPushNotificationsException> callback) {
        try {
            if (!this.a.communicationEnable().get()) {
                PWLog.debug("NotificationManager", "Communication with Pushwoosh is disabled");
                return;
            }
            this.a.isRegisteredForPush().set(true);
            f.a(callback);
            this.b.checkDevice(this.a.applicationId().get());
            String str = this.a.pushToken().get();
            if (TextUtils.isEmpty(str)) {
                this.b.registerPW();
            } else {
                EventBus.sendEvent(new RegistrationSuccessEvent(str));
            }
        } catch (Exception e2) {
            PWLog.exception(e2);
            EventBus.sendEvent(new RegistrationErrorEvent(e2.getMessage()));
        }
    }

    public void a(PushMessage pushMessage) {
        this.c = pushMessage;
    }

    public void a(String str) {
        RequestManager requestManager;
        PWLog.info("NotificationManager", "App ID: " + str);
        if (!TextUtils.isEmpty(str)) {
            String str2 = this.a.applicationId().get();
            boolean z = false;
            if (!str2.equals(str)) {
                this.f.set(false);
                if (this.a.registeredOnServer().get()) {
                    PWLog.info("NotificationManager", "App id changed unregister form previous application");
                    com.pushwoosh.repository.d.a(this.a.pushToken().get(), this.a.baseUrl().get());
                }
                q.d().o();
                RepositoryModule.getRequestStorage().a();
                this.a.removeAppId();
                this.a.forceRegister().set(this.a.isRegisteredForPush().get());
                EventBus.sendEvent(new AppIdChangedEvent(str, str2));
                z = true;
            }
            this.a.setAppId(str);
            if (z && (requestManager = NetworkModule.getRequestManager()) != null) {
                requestManager.updateBaseUrl(this.a.baseUrl().get());
            }
            if (!this.f.get()) {
                EventBus.sendEvent(new a());
                this.f.set(true);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Application id is empty");
    }

    public String b() {
        String str = this.a.pushToken().get();
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return null;
    }

    public void b(Callback<String, UnregisterForPushNotificationException> callback) {
        j.a(callback);
        this.a.isRegisteredForPush().set(false);
        this.b.unregisterPW();
    }

    public void b(String str) {
        PWLog.info("NotificationManager", "Sender ID: " + str);
        if (!TextUtils.isEmpty(str)) {
            String str2 = this.a.projectId().get();
            boolean z = false;
            if (!TextUtils.equals(str2, str) && !TextUtils.isEmpty(str2)) {
                PWLog.info("NotificationManager", "Sender ID changed, clearing token");
                z = !this.a.pushToken().get().isEmpty();
                this.a.removeSenderId();
            }
            this.a.projectId().set(str);
            if (z) {
                this.b.registerPW();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Sender id is empty");
    }

    public PushMessage c() {
        return this.c;
    }

    public void c(String str) {
        RepositoryModule.getRegistrationPreferences().pushToken().set(str);
        com.pushwoosh.repository.d.a(str);
    }

    public void d() {
        this.c = null;
    }

    public void d(String str) {
        EventBus.sendEvent(new RegistrationErrorEvent(str));
    }

    public void e() {
        if (this.e.get()) {
            PWLog.warn("NotificationManager", "Local pushes already rescheduled");
            return;
        }
        PushwooshWorkManagerHelper.enqueueOneTimeUniqueWork((OneTimeWorkRequest) new OneTimeWorkRequest.Builder(RescheduleNotificationsWorker.class).build(), "RescheduleNotificationsWorker", ExistingWorkPolicy.KEEP);
        this.e.set(true);
    }

    public void e(String str) {
        this.a.clearSenderIdInfo();
        com.pushwoosh.repository.d.b(str);
    }

    public void f(String str) {
        EventBus.sendEvent(new DeregistrationErrorEvent(str));
    }
}
