package com.pushwoosh;

import com.pushwoosh.inapp.b;
import com.pushwoosh.inapp.h;
import com.pushwoosh.inapp.view.b.f;
import com.pushwoosh.internal.a.g;
import com.pushwoosh.internal.command.CommandApplayer;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.registrar.PushRegistrar;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.d;
import com.pushwoosh.internal.utils.j;
import com.pushwoosh.internal.utils.k;
import com.pushwoosh.notification.NotificationServiceExtension;
import com.pushwoosh.notification.c;
import com.pushwoosh.notification.e;
import com.pushwoosh.repository.RegistrationPrefs;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.i;
import com.pushwoosh.repository.t;
import com.pushwoosh.repository.z;
import com.pushwoosh.richmedia.RichMediaStyle;
import com.pushwoosh.richmedia.animation.RichMediaAnimationSlideBottom;
import java.security.SecureRandom;

public class q {
    private static boolean a = false;
    private static q e;
    private k b;
    private c c;
    private g d;
    private final d f;
    private final e g;
    private final t h;
    private final RegistrationPrefs i;
    private final com.pushwoosh.inapp.c j;
    private final com.pushwoosh.inapp.a.k k;
    private NotificationServiceExtension l;
    private GDPRManager m;
    private com.pushwoosh.richmedia.a n;
    private com.pushwoosh.internal.utils.c o;
    private i p;
    private s q;
    private com.pushwoosh.repository.d r;
    private RichMediaStyle s;
    private CommandApplayer t;
    private com.pushwoosh.notification.handlers.notification.d u;
    private com.pushwoosh.appevents.d v;

    public static class a {
        private d a;
        private PushRegistrar b;

        public a a(PushRegistrar pushRegistrar) {
            this.b = pushRegistrar;
            return this;
        }

        public a a(d dVar) {
            this.a = dVar;
            return this;
        }

        public q a() {
            q unused = q.e = new q(this);
            return q.e;
        }
    }

    private q(a aVar) {
        this.b = new k();
        this.f = aVar.a;
        this.t = new CommandApplayer();
        this.u = new com.pushwoosh.notification.handlers.notification.d(this.t);
        this.r = new com.pushwoosh.repository.d();
        RepositoryModule.init(this.f, this.b, this.r);
        this.i = RepositoryModule.getRegistrationPreferences();
        NetworkModule.init(this.i);
        this.g = new e(aVar.b, this.f);
        this.j = new com.pushwoosh.inapp.c(new h());
        this.c = new c();
        PrefsProvider prefsProvider = AndroidPlatformModule.getPrefsProvider();
        com.pushwoosh.internal.platform.a.a appInfoProvider = AndroidPlatformModule.getAppInfoProvider();
        j timeProvide = AndroidPlatformModule.getTimeProvide();
        this.o = new com.pushwoosh.internal.utils.c(AndroidPlatformModule.getPrefsProvider().providePrefs("PWAppVersion"));
        this.k = new com.pushwoosh.inapp.a.k(prefsProvider, appInfoProvider, timeProvide, this.o);
        RequestManager requestManager = NetworkModule.getRequestManager();
        z zVar = new z();
        com.pushwoosh.repository.q notificationPreferences = RepositoryModule.getNotificationPreferences();
        this.h = new t(requestManager, zVar, this.i, notificationPreferences, RepositoryModule.getRequestStorage());
        this.m = new GDPRManager(this.h, this.g, this.j);
        this.s = new RichMediaStyle(0, new RichMediaAnimationSlideBottom());
        this.n = new com.pushwoosh.richmedia.a(new f(), new com.pushwoosh.richmedia.e(), b.a(), this.s);
        this.p = new i(requestManager, zVar, notificationPreferences.q(), this.o, this.g, this.i, this.r);
        this.v = new com.pushwoosh.appevents.d();
        this.p.a();
        this.q = new s(this.f, this.i, this.p, this.o, this.h, this.g, this.j, this.r, this.v);
        this.d = new g(new com.pushwoosh.internal.a.f(), new com.pushwoosh.internal.a.a(), new com.pushwoosh.internal.a.d(new SecureRandom()));
    }

    public static void a() {
        if (!a) {
            PWLog.warn("PushwooshPlatform", "Pushwoosh library not initialized. All method calls will be ignored");
            a = true;
        }
    }

    public static q d() {
        return e;
    }

    public c b() {
        return this.c;
    }

    public com.pushwoosh.notification.handlers.notification.d c() {
        return this.u;
    }

    public d e() {
        return this.f;
    }

    public e f() {
        return this.g;
    }

    public t g() {
        return this.h;
    }

    public com.pushwoosh.inapp.c h() {
        return this.j;
    }

    public com.pushwoosh.richmedia.a i() {
        return this.n;
    }

    public com.pushwoosh.inapp.a.k j() {
        return this.k;
    }

    public GDPRManager k() {
        return this.m;
    }

    public NotificationServiceExtension l() {
        if (this.l == null) {
            try {
                Class<?> f2 = this.f.f();
                this.l = f2 != null ? (NotificationServiceExtension) f2.newInstance() : new NotificationServiceExtension();
            } catch (Exception e2) {
                PWLog.exception(e2);
                this.l = new NotificationServiceExtension();
            }
        }
        return this.l;
    }

    public RichMediaStyle m() {
        return this.s;
    }

    public void n() {
        this.q.a();
    }

    public void o() {
        this.q.b();
    }

    /* access modifiers changed from: package-private */
    public com.pushwoosh.internal.utils.c p() {
        return this.o;
    }

    public RegistrationPrefs q() {
        return this.i;
    }

    public g r() {
        return this.d;
    }
}
