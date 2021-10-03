package com.pushwoosh.appevents;

import android.app.Application;
import com.pushwoosh.inapp.PushwooshInApp;
import com.pushwoosh.internal.crash.LogSender;
import com.pushwoosh.internal.platform.AndroidPlatformModule;

public class PushwooshAppEvents {
    private static volatile Application.ActivityLifecycleCallbacks a;
    private static final Object b = new Object();

    private static Application.ActivityLifecycleCallbacks a() {
        synchronized (b) {
            if (a == null) {
                a = new b(a.a());
                Application application = (Application) AndroidPlatformModule.getApplicationContext();
                if (application == null) {
                    LogSender.submitCustomError(new Exception("AndroidPlatformModule.getApplicationContext() returned null in PushwooshAppEvents.registerActivityLifecycleCallbacks() method"));
                } else {
                    application.registerActivityLifecycleCallbacks(a);
                }
            }
        }
        return a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    static /* synthetic */ void a(String str, String str2) {
        char c;
        String str3;
        PushwooshInApp pushwooshInApp;
        int hashCode = str.hashCode();
        if (hashCode != -1632974827) {
            if (hashCode != -1140992324) {
                if (hashCode == -794051143 && str.equals("ApplicationOpened")) {
                    c = 0;
                    switch (c) {
                        case 0:
                            pushwooshInApp = PushwooshInApp.getInstance();
                            str3 = "_ApplicationOpened";
                            break;
                        case 1:
                            pushwooshInApp = PushwooshInApp.getInstance();
                            str3 = "_ApplicationClosed";
                            break;
                        case 2:
                            pushwooshInApp = PushwooshInApp.getInstance();
                            str3 = "_ScreenOpened";
                            break;
                        default:
                            return;
                    }
                    pushwooshInApp.postEvent(str3);
                }
            } else if (str.equals("ApplicationClosed")) {
                c = 1;
                switch (c) {
                }
                pushwooshInApp.postEvent(str3);
            }
        } else if (str.equals("ScreenOpened")) {
            c = 2;
            switch (c) {
            }
            pushwooshInApp.postEvent(str3);
        }
        c = 65535;
        switch (c) {
        }
        pushwooshInApp.postEvent(str3);
    }

    public static void init() {
        a();
    }
}
