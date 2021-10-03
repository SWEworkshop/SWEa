package com.pushwoosh.inapp.view.inline;

import android.content.res.Configuration;
import com.pushwoosh.inapp.view.inline.InlineInAppView;

/* access modifiers changed from: package-private */
public class k {
    protected InlineInAppView a;
    protected b b;

    /* access modifiers changed from: package-private */
    public interface a {
        void a(int i, int i2);
    }

    public k(InlineInAppView inlineInAppView, b bVar) {
        this.a = inlineInAppView;
        this.b = bVar;
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.a.getWebView().forceLayout();
        this.a.forceLayout();
        if (this.a.getParent() != null) {
            this.a.getParent().requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2, a aVar) {
        if (this.a.getState() == InlineInAppView.c.CLOSED || this.a.getState() == InlineInAppView.c.LOADING) {
            aVar.a(0, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Configuration configuration) {
    }

    public void a(InlineInAppView.c cVar) {
        if (cVar == InlineInAppView.c.CLOSED || cVar == InlineInAppView.c.RENDERED) {
            this.b.a();
            a();
        }
    }

    /* access modifiers changed from: protected */
    public void a(boolean z, int i, int i2, int i3, int i4) {
    }
}
