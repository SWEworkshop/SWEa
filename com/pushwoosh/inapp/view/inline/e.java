package com.pushwoosh.inapp.view.inline;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import com.pushwoosh.inapp.view.inline.InlineInAppView;
import com.pushwoosh.inapp.view.inline.k;

/* access modifiers changed from: package-private */
public class e extends k {
    private int c;
    private int d;
    private final Handler e = new Handler();
    private boolean f;
    private int g;
    private int h;

    /* access modifiers changed from: private */
    public class a {
        static final String JS_INTEFACE_NAME = "pwInlineInappSizeDelegate";

        private a() {
        }

        static /* synthetic */ void lambda$resize$0(a aVar) {
            if (e.this.a.getState() == InlineInAppView.c.RENDERED) {
                e.this.a();
            } else {
                e.this.a.setState(InlineInAppView.c.RENDERED);
            }
        }

        @JavascriptInterface
        public void resize(float f, float f2) {
            if (f > 0.0f && f2 > 0.0f) {
                synchronized (e.this.e) {
                    if (((float) e.this.c) != f || ((float) e.this.d) != f2) {
                        e.this.e.removeCallbacksAndMessages(null);
                        e.this.c = (int) (f * e.this.a.getResources().getDisplayMetrics().density);
                        e.this.d = (int) (f2 * e.this.a.getResources().getDisplayMetrics().density);
                        e.this.e.post(i.a(this));
                    }
                }
            }
        }
    }

    @SuppressLint({"AddJavascriptInterface"})
    public e(InlineInAppView inlineInAppView, b bVar) {
        super(inlineInAppView, bVar);
        inlineInAppView.getWebView().addJavascriptInterface(new a(), "pwInlineInappSizeDelegate");
    }

    /* access modifiers changed from: private */
    public void b() {
        this.a.getWebView().loadUrl("javascript:pwInlineInappSizeDelegate.resize(document.body.clientWidth, document.body.clientHeight)");
        synchronized (this.e) {
            this.e.postDelayed(h.a(this), 400);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.inline.k
    public void a(int i, int i2, k.a aVar) {
        int i3;
        int i4;
        if (this.a.getState() == InlineInAppView.c.RENDERED && (i3 = this.c) > 0 && (i4 = this.d) > 0) {
            aVar.a(i3, i4);
        } else if (this.a.getState() == InlineInAppView.c.LOADED) {
            aVar.a(1, 1);
        } else {
            super.a(i, i2, aVar);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.inline.k
    public void a(Configuration configuration) {
        super.a(configuration);
        if (Build.VERSION.SDK_INT < 19) {
            this.g = this.a.getWebView().getWidth();
            this.h = this.a.getWebView().getHeight();
            this.f = true;
        }
    }

    @Override // com.pushwoosh.inapp.view.inline.k
    public void a(InlineInAppView.c cVar) {
        super.a(cVar);
        if (cVar == InlineInAppView.c.LOADED) {
            this.a.getContainer().setAlpha(0.01f);
            a();
            this.e.postDelayed(g.a(this), 400);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.inline.k
    public void a(boolean z, int i, int i2, int i3, int i4) {
        if (z && this.f) {
            if (i3 != this.g || i4 != this.h) {
                this.c = 0;
                this.d = 0;
                this.e.post(f.a(this));
                this.f = false;
            }
        }
    }
}
