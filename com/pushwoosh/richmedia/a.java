package com.pushwoosh.richmedia;

import android.text.TextUtils;
import com.pushwoosh.inapp.event.b;
import com.pushwoosh.inapp.event.d;
import com.pushwoosh.inapp.f.c;
import com.pushwoosh.inapp.view.b.f;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.utils.PWLog;

public class a {
    private String a = a.class.getSimpleName();
    private f b;
    private e c;
    private RichMediaPresentingDelegate d;
    private c e;
    private RichMediaStyle f;

    public a(f fVar, e eVar, c cVar, RichMediaStyle richMediaStyle) {
        this.f = richMediaStyle;
        this.b = fVar;
        this.c = eVar;
        this.e = cVar;
        EventBus.subscribe(b.class, b.a(this));
        EventBus.subscribe(d.class, c.a(this));
        EventBus.subscribe(com.pushwoosh.inapp.event.c.class, d.a(this));
    }

    /* access modifiers changed from: private */
    public void a(b bVar) {
        PWLog.noise(this.a, "handle close RichMedia");
        if (this.d != null) {
            PWLog.noise(this.a, "try use delegate onClose");
            com.pushwoosh.inapp.e.b.b a2 = bVar.a();
            if (a2 == null) {
                PWLog.error(this.a, "resource in event is null");
            } else if (!a(a2)) {
                this.d.onClose(this.c.a(a2));
            }
        } else {
            PWLog.noise(this.a, "delegate is null");
        }
    }

    /* access modifiers changed from: private */
    public void a(com.pushwoosh.inapp.event.c cVar) {
        if (cVar != null) {
            if (cVar.b() != null) {
                PWLog.error(cVar.b().getMessage());
            }
            PWLog.noise(this.a, "handle error RichMedia");
            if (this.d != null) {
                PWLog.noise(this.a, "try use delegate onError");
                com.pushwoosh.inapp.e.b.b a2 = cVar.a();
                if (a2 == null) {
                    PWLog.error(this.a, "resource in event is null");
                } else if (!a(a2)) {
                    this.d.onError(this.c.a(a2), cVar.b());
                }
            } else {
                PWLog.noise(this.a, "delegate is null");
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(d dVar) {
        PWLog.noise(this.a, "handle present RichMedia");
        if (this.d != null) {
            PWLog.noise(this.a, "try use delegate onPresent");
            com.pushwoosh.inapp.e.b.b a2 = dVar.a();
            if (a2 == null) {
                PWLog.error(this.a, "resource in event is null");
            } else if (!a(a2)) {
                this.d.onPresent(this.c.a(a2));
            }
        } else {
            PWLog.noise(this.a, "delegate is null");
        }
    }

    private boolean a(com.pushwoosh.inapp.e.b.b bVar) {
        if (!TextUtils.isEmpty(bVar.a())) {
            return false;
        }
        PWLog.noise(this.a, "code is empty, resource is not RichMedia, abort use delegate");
        return true;
    }

    private RichMedia b(com.pushwoosh.inapp.view.b.a.b bVar) {
        return this.c.a(bVar);
    }

    private void c(com.pushwoosh.inapp.view.b.a.b bVar) {
        if (!d(bVar) && this.d.shouldPresent(b(bVar))) {
            this.b.b(bVar);
        }
    }

    private boolean d(com.pushwoosh.inapp.view.b.a.b bVar) {
        String str;
        String str2;
        com.pushwoosh.inapp.e.b.b b2 = bVar.b();
        if (b2 == null) {
            str = this.a;
            str2 = "resource is null, abort show RichMedia";
        } else if (bVar.e() != com.pushwoosh.inapp.view.b.a.a.IN_APP || b2.f() || this.e.d(b2.a())) {
            return false;
        } else {
            str = this.a;
            str2 = "resource is not downloaded, abort show RichMedia";
        }
        PWLog.error(str, str2);
        return true;
    }

    private boolean e(com.pushwoosh.inapp.view.b.a.b bVar) {
        return bVar.e() != com.pushwoosh.inapp.view.b.a.a.REMOTE_URL;
    }

    public RichMediaStyle a() {
        return this.f;
    }

    public void a(com.pushwoosh.inapp.view.b.a.b bVar) {
        if (this.d == null || !e(bVar)) {
            this.b.b(bVar);
        } else {
            c(bVar);
        }
    }

    public void a(RichMedia richMedia) {
        PWLog.noise(this.a, "try show richMedia");
        if (richMedia != null) {
            String str = this.a;
            PWLog.noise(str, "showRichMedia with content:" + richMedia.getContent());
            this.b.b(richMedia.a());
            return;
        }
        PWLog.error(this.a, "richMedia is null");
    }

    public void a(RichMediaPresentingDelegate richMediaPresentingDelegate) {
        this.d = richMediaPresentingDelegate;
    }
}
