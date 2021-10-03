package com.pushwoosh.inapp.e;

import com.pushwoosh.inapp.e.b.b;
import com.pushwoosh.inapp.event.a;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;
import java.util.concurrent.CountDownLatch;

/* access modifiers changed from: package-private */
public final /* synthetic */ class e implements EventListener {
    private final b a;
    private final a.EnumC0007a[] b;
    private final CountDownLatch c;

    private e(b bVar, a.EnumC0007a[] aVarArr, CountDownLatch countDownLatch) {
        this.a = bVar;
        this.b = aVarArr;
        this.c = countDownLatch;
    }

    public static EventListener a(b bVar, a.EnumC0007a[] aVarArr, CountDownLatch countDownLatch) {
        return new e(bVar, aVarArr, countDownLatch);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        c.a(this.a, this.b, this.c, (a) event);
    }
}
