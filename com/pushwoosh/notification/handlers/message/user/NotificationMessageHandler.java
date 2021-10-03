package com.pushwoosh.notification.handlers.message.user;

import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.q;

public abstract class NotificationMessageHandler implements MessageHandler {
    private final q a = RepositoryModule.getNotificationPreferences();

    /* access modifiers changed from: protected */
    public abstract void handleNotification(PushMessage pushMessage);

    @Override // com.pushwoosh.notification.handlers.message.user.MessageHandler
    public void handlePushMessage(PushMessage pushMessage) {
        if (this.a.k().get()) {
            handleNotification(pushMessage);
        }
    }
}
