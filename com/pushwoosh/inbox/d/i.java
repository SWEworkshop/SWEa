package com.pushwoosh.inbox.d;

import com.pushwoosh.function.Callback;
import com.pushwoosh.inbox.data.InboxMessage;

/* access modifiers changed from: package-private */
public final /* synthetic */ class i implements Runnable {
    private final InboxMessage a;
    private final Callback b;

    private i(InboxMessage inboxMessage, Callback callback) {
        this.a = inboxMessage;
        this.b = callback;
    }

    public static Runnable a(InboxMessage inboxMessage, Callback callback) {
        return new i(inboxMessage, callback);
    }

    public void run() {
        b.a(this.a, this.b);
    }
}
