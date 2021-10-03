package com.pushwoosh.repository;

import android.text.TextUtils;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.event.DeregistrationErrorEvent;
import com.pushwoosh.notification.event.DeregistrationSuccessEvent;
import com.pushwoosh.notification.event.RegistrationErrorEvent;
import com.pushwoosh.notification.event.RegistrationSuccessEvent;
import java.util.Calendar;
import java.util.Date;

public class d {
    static /* synthetic */ void a(RegistrationPrefs registrationPrefs, String str, Result result) {
        if (result.isSuccess()) {
            registrationPrefs.registeredOnServer().set(true);
            EventBus.sendEvent(new RegistrationSuccessEvent(str));
            registrationPrefs.lastPushRegistration().set(new Date().getTime());
            PWLog.info("DeviceRegistrar", "Registered for push notifications: " + str);
            return;
        }
        String message = result.getException() == null ? "" : ((NetworkException) result.getException()).getMessage();
        if (TextUtils.isEmpty(message)) {
            message = "Pushwoosh registration error";
        }
        PWLog.error("DeviceRegistrar", "Registration error: " + message);
        EventBus.sendEvent(new RegistrationErrorEvent(message));
    }

    public static void a(String str) {
        PWLog.debug("DeviceRegistrar", "Registering for pushes...");
        RegistrationPrefs registrationPreferences = RepositoryModule.getRegistrationPreferences();
        y yVar = new y(str);
        RequestManager requestManager = NetworkModule.getRequestManager();
        if (requestManager == null) {
            EventBus.sendEvent(new RegistrationErrorEvent("Request manager is null"));
        } else {
            requestManager.sendRequest(yVar, e.a(registrationPreferences, str));
        }
    }

    static /* synthetic */ void a(String str, RegistrationPrefs registrationPrefs, Result result) {
        if (result.isSuccess()) {
            PWLog.info("DeviceRegistrar", "Unregistered for pushes: " + str);
            EventBus.sendEvent(new DeregistrationSuccessEvent(str));
            registrationPrefs.lastPushRegistration().set(0);
            return;
        }
        String message = result.getException() == null ? "" : ((NetworkException) result.getException()).getMessage();
        if (TextUtils.isEmpty(message)) {
            message = "Pushwoosh unregistration error";
        }
        PWLog.error("DeviceRegistrar", "Unregistration error: " + message);
        EventBus.sendEvent(new DeregistrationErrorEvent(message));
    }

    public static void a(String str, String str2) {
        PWLog.debug("DeviceRegistrar", "Unregistering for pushes...");
        RegistrationPrefs registrationPreferences = RepositoryModule.getRegistrationPreferences();
        registrationPreferences.registeredOnServer().set(false);
        ae aeVar = new ae();
        RequestManager requestManager = NetworkModule.getRequestManager();
        if (requestManager == null) {
            EventBus.sendEvent(new DeregistrationErrorEvent("Request manager is null"));
        } else {
            requestManager.sendRequest(aeVar, str2, f.a(str, registrationPreferences));
        }
    }

    public static void b(String str) {
        a(str, null);
    }

    private static boolean b() {
        RegistrationPrefs registrationPreferences = RepositoryModule.getRegistrationPreferences();
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.add(12, -10);
        Calendar instance3 = Calendar.getInstance();
        instance3.setTime(new Date(registrationPreferences.lastPushRegistration().get()));
        return !instance2.before(instance3) || !instance3.before(instance);
    }

    public void a() {
        RegistrationPrefs registrationPreferences = RepositoryModule.getRegistrationPreferences();
        String str = registrationPreferences.pushToken().get();
        if (str != null && !str.equals("")) {
            boolean z = registrationPreferences.forceRegister().get();
            registrationPreferences.forceRegister().set(false);
            if (z || b()) {
                a(str);
            }
        }
    }
}
