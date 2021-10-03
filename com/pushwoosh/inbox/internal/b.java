package com.pushwoosh.inbox.internal;

import android.content.SharedPreferences;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.inbox.e.a;
import com.pushwoosh.internal.command.CommandApplayer;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;

public class b {
    private static volatile com.pushwoosh.inbox.d.b a;
    private static final Object b = new Object();
    private static volatile a c;
    private static final Object d = new Object();
    private static com.pushwoosh.inbox.e.b.b e;
    private static RequestManager f;
    private static CommandApplayer g;

    public static com.pushwoosh.inbox.d.b a() {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    if (f == null) {
                        throw new IllegalArgumentException("Incorrect state.");
                    } else if (g != null) {
                        a = new com.pushwoosh.inbox.d.b(f, b(), g);
                        f = null;
                    } else {
                        throw new IllegalArgumentException("Incorrect state.");
                    }
                }
            }
        }
        return a;
    }

    public static void a(com.pushwoosh.inbox.e.b.b bVar, RequestManager requestManager, PrefsProvider prefsProvider) {
        e = bVar;
        f = requestManager;
        g = new CommandApplayer();
        SharedPreferences providePrefs = prefsProvider.providePrefs("pwInbox");
        String appId = Pushwoosh.getInstance().getAppId();
        String string = providePrefs == null ? appId : providePrefs.getString("appId", appId);
        if (providePrefs != null) {
            providePrefs.edit().putString("appId", appId).apply();
        }
        if (!string.equals(appId)) {
            e.b();
        }
    }

    private static a b() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    if (e != null) {
                        c = new com.pushwoosh.inbox.e.b.a(e);
                        e = null;
                    } else {
                        throw new IllegalArgumentException("Incorrect state.");
                    }
                }
            }
        }
        return c;
    }
}
