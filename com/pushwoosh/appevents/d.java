package com.pushwoosh.appevents;

import android.app.Application;
import com.pushwoosh.inapp.PushwooshInApp;
import com.pushwoosh.internal.crash.LogSender;
import com.pushwoosh.internal.event.ConfigLoadedEvent;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.a;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;
import com.pushwoosh.q;
import com.pushwoosh.repository.config.b;
import com.pushwoosh.tags.TagsBundle;
import java.util.List;

public class d {
    private static volatile Application.ActivityLifecycleCallbacks a;
    private static final Object b = new Object();
    private boolean c = false;
    private EventListener<a.C0012a> d = e.a(this);
    private EventListener<ConfigLoadedEvent> e = f.a(this);

    static TagsBundle a(String str, String str2) {
        TagsBundle.Builder builder = new TagsBundle.Builder();
        builder.putInt("device_type", DeviceSpecificProvider.getInstance().deviceType());
        builder.putString("application_version", AndroidPlatformModule.getAppInfoProvider().d());
        if (str.equals("PW_ScreenOpen")) {
            builder.putString("screen_name", str2);
        }
        return builder.build();
    }

    static /* synthetic */ void a(d dVar, ConfigLoadedEvent configLoadedEvent) {
        EventBus.unsubscribe(ConfigLoadedEvent.class, dVar.e);
        dVar.c = true;
        dVar.a("PW_ApplicationOpen", a("PW_ApplicationOpen", ""));
    }

    static /* synthetic */ void a(d dVar, a.C0012a aVar) {
        EventBus.unsubscribe(a.C0012a.class, dVar.d);
        dVar.b();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    static /* synthetic */ void a(d dVar, String str, String str2) {
        char c2;
        String str3;
        String str4;
        int hashCode = str.hashCode();
        if (hashCode != -1632974827) {
            if (hashCode != -1140992324) {
                if (hashCode == -794051143 && str.equals("ApplicationOpened")) {
                    c2 = 0;
                    switch (c2) {
                        case 0:
                            str3 = "PW_ApplicationOpen";
                            str4 = "PW_ApplicationOpen";
                            break;
                        case 1:
                            str3 = "PW_ApplicationMinimized";
                            str4 = "PW_ApplicationMinimized";
                            break;
                        case 2:
                            str3 = "PW_ScreenOpen";
                            str4 = "PW_ScreenOpen";
                            break;
                        default:
                            return;
                    }
                    dVar.a(str3, a(str4, str2));
                }
            } else if (str.equals("ApplicationClosed")) {
                c2 = 1;
                switch (c2) {
                }
                dVar.a(str3, a(str4, str2));
            }
        } else if (str.equals("ScreenOpened")) {
            c2 = 2;
            switch (c2) {
            }
            dVar.a(str3, a(str4, str2));
        }
        c2 = 65535;
        switch (c2) {
        }
        dVar.a(str3, a(str4, str2));
    }

    private void b() {
        synchronized (b) {
            if (a == null) {
                Application application = (Application) AndroidPlatformModule.getApplicationContext();
                if (application == null) {
                    EventBus.subscribe(a.C0012a.class, this.d);
                    LogSender.submitCustomError(new Exception("AndroidPlatformModule.getApplicationContext() returned null in PushwooshDefaultEvents.registerActivityLifecycleCallbacks() method"));
                } else {
                    a = new b(g.a(this));
                    application.registerActivityLifecycleCallbacks(a);
                }
            }
        }
    }

    public void a() {
        EventBus.subscribe(ConfigLoadedEvent.class, this.e);
        b();
    }

    /* access modifiers changed from: package-private */
    public void a(String str, TagsBundle tagsBundle) {
        List<b> f = q.d().g().f();
        if (f != null && this.c) {
            for (b bVar : f) {
                if (bVar.a().equals(str)) {
                    PushwooshInApp.getInstance().postEvent(str, tagsBundle);
                    return;
                }
            }
        }
    }
}
