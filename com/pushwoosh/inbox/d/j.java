package com.pushwoosh.inbox.d;

import android.util.Pair;
import com.pushwoosh.inbox.event.a;
import com.pushwoosh.internal.event.EventBus;
import java.util.Collection;

/* access modifiers changed from: package-private */
public final /* synthetic */ class j implements Runnable {
    private final b a;
    private final Pair b;

    private j(b bVar, Pair pair) {
        this.a = bVar;
        this.b = pair;
    }

    public static Runnable a(b bVar, Pair pair) {
        return new j(bVar, pair);
    }

    public void run() {
        Pair pair;
        EventBus.sendEvent(new a().b(this.a.b((Collection) pair.first)).c((Collection) this.b.second).a());
    }
}
