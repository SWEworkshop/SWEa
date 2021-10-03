package com.pushwoosh.inbox.d;

import com.pushwoosh.inbox.event.InboxMessagesUpdatedEvent;
import com.pushwoosh.internal.event.EventBus;

/* access modifiers changed from: package-private */
public final /* synthetic */ class f implements Runnable {
    private final InboxMessagesUpdatedEvent a;

    private f(InboxMessagesUpdatedEvent inboxMessagesUpdatedEvent) {
        this.a = inboxMessagesUpdatedEvent;
    }

    public static Runnable a(InboxMessagesUpdatedEvent inboxMessagesUpdatedEvent) {
        return new f(inboxMessagesUpdatedEvent);
    }

    public void run() {
        EventBus.sendEvent(this.a);
    }
}
