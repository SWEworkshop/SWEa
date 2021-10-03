package com.pushwoosh.inapp.view.inline;

import androidx.annotation.RequiresApi;
import com.pushwoosh.inapp.view.inline.InlineInAppView;

@RequiresApi(19)
class j extends k {
    public j(InlineInAppView inlineInAppView, b bVar) {
        super(inlineInAppView, bVar);
    }

    @Override // com.pushwoosh.inapp.view.inline.k
    public void a(InlineInAppView.c cVar) {
        super.a(cVar);
        if (cVar == InlineInAppView.c.LOADED) {
            int i = 1;
            int width = this.a.getWebView().getWidth() == 0 ? 1 : this.a.getWebView().getWidth();
            if (this.a.getWebView().getHeight() != 0) {
                i = this.a.getWebView().getHeight();
            }
            this.a.getWebView().layout(0, 0, width, i);
            this.a.setState(InlineInAppView.c.RENDERED);
        }
    }
}
