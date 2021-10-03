package com.pushwoosh.inapp.a;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.pushwoosh.inapp.view.b.a.b;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.j;
import com.pushwoosh.q;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class a {
    private String a;
    private long b;
    private c c;
    private SharedPreferences d;
    private d<String> e = new d<>();
    private Date f;
    private String g;
    private j h;
    private AbstractC0005a i;

    /* renamed from: com.pushwoosh.inapp.a.a$a  reason: collision with other inner class name */
    public interface AbstractC0005a {
        void a(j jVar);
    }

    /* access modifiers changed from: private */
    public interface b<T extends Event> {
        boolean a(T t);
    }

    public interface c {
        boolean a();
    }

    private class d<T> {
        private T b;
        private f<T> c;

        private d() {
        }

        /* access modifiers changed from: package-private */
        public void a(f<T> fVar) {
            synchronized (this) {
                if (this.b != null) {
                    fVar.a(this.b);
                } else {
                    this.c = fVar;
                }
            }
        }

        public void a(T t) {
            synchronized (this) {
                if (this.c != null) {
                    this.c.a(t);
                    this.c = null;
                }
                this.b = t;
            }
        }
    }

    /* access modifiers changed from: private */
    public class e<T extends Event> implements EventListener<T> {
        private b<T> b;
        private Class<T> c;
        private e d;

        e(Class<T> cls, b<T> bVar) {
            this.b = bVar;
            this.c = cls;
            EventBus.subscribe(this.c, this);
        }

        /* access modifiers changed from: package-private */
        public void a() {
            e eVar = this.d;
            if (eVar != null) {
                if (eVar.d == this) {
                    eVar.a(null);
                }
                this.d.a();
            }
            EventBus.unsubscribe(this.c, this);
        }

        /* access modifiers changed from: package-private */
        public void a(e eVar) {
            this.d = eVar;
        }

        @Override // com.pushwoosh.internal.event.EventListener
        public void onReceive(T t) {
            if (this.b.a(t)) {
                a();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public interface f<T> {
        void a(T t);
    }

    public a(String str, float f2, SharedPreferences sharedPreferences, c cVar, j jVar) {
        this.a = str;
        this.b = (long) (f2 * 8.64E7f);
        this.d = sharedPreferences;
        this.c = cVar;
        this.h = jVar;
    }

    static /* synthetic */ void a(a aVar, AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, String str, AbstractC0005a aVar2) {
        if (atomicBoolean.get()) {
            PWLog.debug("BusinessCase", aVar.a + " timeout Exceeded");
            return;
        }
        atomicBoolean2.set(true);
        com.pushwoosh.inapp.e.b.b bVar = null;
        com.pushwoosh.inapp.f.d b2 = com.pushwoosh.inapp.b.b();
        if (b2 != null) {
            bVar = b2.a(str);
        }
        if (bVar != null) {
            if (aVar2 != null) {
                e eVar = new e(com.pushwoosh.inapp.view.d.class, f.a(str));
                e eVar2 = new e(com.pushwoosh.inapp.view.e.class, g.a(str, aVar2));
                eVar.a(eVar2);
                eVar2.a(eVar);
            }
            com.pushwoosh.inapp.view.b.a.b a2 = new b.a().a(bVar).a();
            aVar.g = bVar.a();
            aVar.i = aVar2;
            EventBus.subscribe(com.pushwoosh.inapp.event.b.class, h.a(aVar));
            com.pushwoosh.richmedia.a i2 = q.d().i();
            if (i2 != null) {
                i2.a(a2);
            }
            aVar.c();
        } else if (aVar2 != null) {
            aVar2.a(j.LOADING_FAILED);
        }
    }

    /* access modifiers changed from: private */
    public void a(com.pushwoosh.inapp.event.b bVar) {
        com.pushwoosh.inapp.e.b.b a2 = bVar.a();
        if (a2 == null) {
            PWLog.error("BusinessCase", "resource in event is null");
        } else if (TextUtils.equals(a2.a(), this.g)) {
            EventBus.unsubscribe(com.pushwoosh.inapp.event.b.class, d.a(this));
            new Handler().postDelayed(new Runnable() {
                /* class com.pushwoosh.inapp.a.a.AnonymousClass1 */

                public void run() {
                    if (a.this.i != null) {
                        a.this.i.a(j.RICHMEDIA_CLOSED);
                        a.this.i = null;
                    }
                }
            }, 1000);
        }
    }

    static /* synthetic */ void a(AtomicBoolean atomicBoolean, AbstractC0005a aVar, AtomicBoolean atomicBoolean2) {
        if (!atomicBoolean.get()) {
            if (aVar != null) {
                aVar.a(j.LOADING_FAILED);
            }
            atomicBoolean2.set(true);
        }
    }

    static /* synthetic */ boolean a(String str, AbstractC0005a aVar, com.pushwoosh.inapp.view.e eVar) {
        if (!eVar.a().a().equals(str)) {
            return false;
        }
        aVar.a(j.LOADING_FAILED);
        return true;
    }

    private boolean b() {
        if (this.b == 0) {
            return false;
        }
        long j = this.d.getLong(this.a, Long.MIN_VALUE);
        if (j != Long.MIN_VALUE) {
            this.f = new Date(j);
        }
        return this.f != null && this.h.b() - this.f.getTime() < this.b;
    }

    private void c() {
        this.d.edit().putLong(this.a, this.h.b()).apply();
    }

    public String a() {
        return this.a;
    }

    public void a(AbstractC0005a aVar) {
        PWLog.debug("[BusinessCase]", "trigger " + this.a);
        if (!this.c.a()) {
            if (aVar != null) {
                aVar.a(j.CONDITION_NOT_SATISFIED);
                return;
            }
            PWLog.debug("BusinessCase", this.a + " condition not satisfied");
        } else if (!b()) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            AtomicBoolean atomicBoolean2 = new AtomicBoolean(false);
            Looper myLooper = Looper.myLooper();
            if (myLooper == null) {
                PWLog.debug("BusinessCase", "Looper is null. Using MainLooper instead, which will cause StrictMode policy violation");
            }
            if (myLooper == null) {
                myLooper = Looper.getMainLooper();
            }
            Handler handler = new Handler(myLooper);
            handler.postDelayed(b.a(atomicBoolean2, aVar, atomicBoolean), 4000);
            this.e.a(c.a(this, handler, atomicBoolean, atomicBoolean2, aVar));
        } else if (aVar != null) {
            aVar.a(j.TRIGGER_CAP_EXCEEDED);
        } else {
            PWLog.debug("BusinessCase", this.a + " trigger cap exceeded");
        }
    }

    public void a(String str) {
        this.e.a(str);
    }
}
