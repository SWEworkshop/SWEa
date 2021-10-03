package com.pushwoosh.inbox;

import com.pushwoosh.inbox.c.a.a;
import com.pushwoosh.inbox.internal.b;
import com.pushwoosh.internal.Plugin;
import com.pushwoosh.internal.event.AppIdChangedEvent;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.platform.prefs.migration.MigrationScheme;
import com.pushwoosh.notification.handlers.message.system.MessageSystemHandleChainProvider;
import com.pushwoosh.notification.handlers.notification.NotificationOpenHandlerChainProvider;
import java.util.Collection;
import java.util.Collections;

public class PushwooshInboxPlugin implements Plugin {
    @Override // com.pushwoosh.internal.Plugin
    public Collection<? extends MigrationScheme> getPrefsMigrationSchemes(PrefsProvider prefsProvider) {
        return Collections.emptyList();
    }

    @Override // com.pushwoosh.internal.Plugin
    public void init() {
        b.a(new com.pushwoosh.inbox.e.b.b(AndroidPlatformModule.getApplicationContext()), NetworkModule.getRequestManager(), AndroidPlatformModule.getPrefsProvider());
        MessageSystemHandleChainProvider.getMessageSystemChain().addItem(new a());
        NotificationOpenHandlerChainProvider.getNotificationOpenHandlerChain().addItem(new com.pushwoosh.inbox.c.a.b());
        EventBus.subscribe(AppIdChangedEvent.class, b.a());
    }
}
