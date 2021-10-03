package com.pushwoosh.inapp.view.inline;

import com.pushwoosh.inapp.view.inline.k;

final /* synthetic */ class a implements k.a {
    private final InlineInAppView a;
    private final int b;
    private final int c;

    private a(InlineInAppView inlineInAppView, int i, int i2) {
        this.a = inlineInAppView;
        this.b = i;
        this.c = i2;
    }

    public static k.a a(InlineInAppView inlineInAppView, int i, int i2) {
        return new a(inlineInAppView, i, i2);
    }

    @Override // com.pushwoosh.inapp.view.inline.k.a
    public void a(int i, int i2) {
        InlineInAppView.a(this.a, this.b, this.c, i, i2);
    }
}
