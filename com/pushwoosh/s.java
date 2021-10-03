package com.pushwoosh;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.pushwoosh.BootReceiver;
import com.pushwoosh.internal.Plugin;
import com.pushwoosh.internal.event.ConfigLoadedEvent;
import com.pushwoosh.internal.event.Emitter;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.event.InitHwidEvent;
import com.pushwoosh.internal.event.Subscription;
import com.pushwoosh.internal.platform.a;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.c;
import com.pushwoosh.internal.utils.d;
import com.pushwoosh.notification.e;
import com.pushwoosh.repository.RegistrationPrefs;
import com.pushwoosh.repository.i;
import com.pushwoosh.repository.t;
import com.pushwoosh.secure.crypt.manager.RsaDecryptorManager;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class s {
    public static final String a = "s";
    private final AtomicBoolean b = new AtomicBoolean(false);
    private final AtomicBoolean c = new AtomicBoolean(false);
    private final AtomicReference<String> d = new AtomicReference<>("");
    private final AtomicReference<String> e = new AtomicReference<>("");
    private final AtomicBoolean f = new AtomicBoolean(false);
    private final d g;
    private final RegistrationPrefs h;
    private final i i;
    private final c j;
    private final t k;
    private final e l;
    private final com.pushwoosh.inapp.c m;
    private final com.pushwoosh.repository.d n;
    private final com.pushwoosh.appevents.d o;

    public s(d dVar, RegistrationPrefs registrationPrefs, i iVar, c cVar, t tVar, e eVar, com.pushwoosh.inapp.c cVar2, com.pushwoosh.repository.d dVar2, com.pushwoosh.appevents.d dVar3) {
        this.g = dVar;
        this.h = registrationPrefs;
        this.i = iVar;
        this.j = cVar;
        this.k = tVar;
        this.l = eVar;
        this.m = cVar2;
        this.n = dVar2;
        this.o = dVar3;
    }

    private void a(Pair<String, String> pair) {
        if (this.b.get()) {
            this.j.b();
            if (this.c.get()) {
                this.i.a((String) pair.first, (String) pair.second);
                this.k.b();
            }
        }
    }

    private void a(Subscription<a.C0012a> subscription, Subscription<e.a> subscription2) {
        PWLog.debug("initHwid");
        com.pushwoosh.internal.platform.utils.a.a(w.a(this, subscription, subscription2));
    }

    /* access modifiers changed from: private */
    public void a(String str, Subscription<a.C0012a> subscription, Subscription<e.a> subscription2) {
        this.e.set(this.h.hwid().get());
        this.d.set(str);
        this.h.hwid().set(this.d.get());
        c();
        EventBus.sendEvent(new InitHwidEvent(this.d.get()));
        a(new Pair<>(this.d.get(), this.e.get()));
        g();
        subscription.unsubscribe();
        subscription2.unsubscribe();
    }

    private void c() {
        if (TextUtils.isEmpty(this.h.userId().get())) {
            this.m.a(this.h.hwid().get());
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.f.compareAndSet(false, true)) {
            e();
            this.k.d();
            this.n.a();
            this.m.a();
        }
    }

    private void e() {
        EventBus.subscribe(ConfigLoadedEvent.class, new EventListener<ConfigLoadedEvent>() {
            /* class com.pushwoosh.s.AnonymousClass1 */

            /* renamed from: a */
            public void onReceive(ConfigLoadedEvent configLoadedEvent) {
                EventBus.unsubscribe(ConfigLoadedEvent.class, this);
                s.this.k.c();
            }
        });
    }

    private void f() {
        Log.i(RsaDecryptorManager.ALIAS, "HWID: " + this.h.hwid().get());
        PWLog.debug("PushwooshModule", "onApplicationCreated");
        PWLog.info(a, String.format("This is %s device", DeviceSpecificProvider.getInstance().type()));
        for (Plugin plugin : this.g.m()) {
            plugin.init();
        }
    }

    private void g() {
        EventBus.subscribe(a.C0012a.class, x.a(this));
        PWLog.debug("appOpen:" + this.b.get() + " onAppReady:" + this.c.get());
        if (this.b.get()) {
            EventBus.subscribe(e.a.class, y.a(this));
        } else {
            Emitter.when(Emitter.forEvent(a.C0012a.class), Emitter.forEvent(e.a.class)).bind(z.a(this));
        }
        EventBus.subscribe(BootReceiver.DeviceBootedEvent.class, aa.a(this));
    }

    /* access modifiers changed from: private */
    public void h() {
        PWLog.debug("onAppOpen");
        this.j.b();
        this.b.set(true);
        if (this.c.get()) {
            j();
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        PWLog.debug("onAppReady");
        if (this.b.get()) {
            j();
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        PWLog.debug("sendAppOpenEndTagMigrate");
        if (!this.d.get().isEmpty()) {
            this.i.a(this.d.get(), this.e.get());
            this.k.b();
        }
    }

    public void a() {
        PWLog.init();
        Subscription<a.C0012a> subscribe = EventBus.subscribe(a.C0012a.class, t.a(this));
        Subscription<e.a> subscribe2 = EventBus.subscribe(e.a.class, u.a(this));
        Emitter.when(Emitter.forEvent(e.a.class), Emitter.forEvent(InitHwidEvent.class)).bind(v.a(this));
        this.l.a();
        a(subscribe, subscribe2);
        f();
        this.o.a();
    }

    public void b() {
        this.f.set(false);
    }
}
