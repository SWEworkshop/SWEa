package com.pushwoosh;

import com.pushwoosh.internal.event.Subscription;
import com.pushwoosh.internal.platform.utils.f;

/* access modifiers changed from: package-private */
public final /* synthetic */ class w implements f {
    private final s a;
    private final Subscription b;
    private final Subscription c;

    private w(s sVar, Subscription subscription, Subscription subscription2) {
        this.a = sVar;
        this.b = subscription;
        this.c = subscription2;
    }

    public static f a(s sVar, Subscription subscription, Subscription subscription2) {
        return new w(sVar, subscription, subscription2);
    }

    @Override // com.pushwoosh.internal.platform.utils.f
    public void a(String str) {
        this.a.a(str, this.b, this.c);
    }
}
