package com.pushwoosh.notification.handlers.message.user;

import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.notification.handlers.a;
import com.pushwoosh.q;

/* access modifiers changed from: package-private */
public class c implements a, MessageHandler {
    c() {
    }

    @Override // com.pushwoosh.notification.handlers.message.user.MessageHandler
    public void handlePushMessage(PushMessage pushMessage) {
        if (!pushMessage.isLocal()) {
            q.d().g().b(pushMessage.getPushHash());
        }
    }
}
