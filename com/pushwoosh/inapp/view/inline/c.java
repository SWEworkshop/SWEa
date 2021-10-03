package com.pushwoosh.inapp.view.inline;

import android.os.Parcel;
import com.pushwoosh.exception.PostEventException;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.e.b.b;
import com.pushwoosh.inapp.view.a;
import com.pushwoosh.inapp.view.d;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.tags.TagsBundle;

/* access modifiers changed from: package-private */
public class c implements a.AbstractC0008a {
    private com.pushwoosh.inapp.view.a a;
    private b b;
    private com.pushwoosh.inapp.e.c c;
    private InlineInAppView d;
    private boolean e;

    static class a {
        private boolean a;

        a(Parcel parcel) {
            this.a = parcel.readInt() != 1 ? false : true;
        }

        private a(boolean z) {
            this.a = z;
        }

        public void a(Parcel parcel) {
            parcel.writeInt(this.a ? 1 : 0);
        }
    }

    c(InlineInAppView inlineInAppView, com.pushwoosh.inapp.e.c cVar) {
        this.c = cVar;
        this.d = inlineInAppView;
    }

    /* access modifiers changed from: private */
    public void b(Result<b, PostEventException> result) {
        this.b = result.getData();
        this.d.a(this.b);
        if (this.b != null && result.getException() == null) {
            this.a = new com.pushwoosh.inapp.view.a(this.b, this);
            this.a.execute(new Void[0]);
        }
    }

    @Override // com.pushwoosh.inapp.view.a.AbstractC0008a
    public void a() {
    }

    @Override // com.pushwoosh.inapp.view.a.AbstractC0008a
    public void a(Result<com.pushwoosh.inapp.d.a, com.pushwoosh.inapp.b.a> result) {
        if (result.isSuccess()) {
            this.d.b(result.getData());
        }
    }

    /* access modifiers changed from: package-private */
    public void a(a aVar) {
        this.e = aVar.a;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        if (str != null && !str.isEmpty()) {
            this.e = false;
            this.c.a("inlineInApp", new TagsBundle.Builder().putString("identifier", str).build(), d.a(this));
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        b bVar = this.b;
        if (bVar != null && !this.e) {
            EventBus.sendEvent(new d(bVar));
            this.e = true;
        }
    }

    /* access modifiers changed from: package-private */
    public a c() {
        return new a(this.e);
    }
}
