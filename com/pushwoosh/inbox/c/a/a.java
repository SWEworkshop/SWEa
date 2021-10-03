package com.pushwoosh.inbox.c.a;

import android.os.Bundle;
import androidx.annotation.WorkerThread;
import com.pushwoosh.inbox.internal.b;
import com.pushwoosh.inbox.internal.data.b;
import com.pushwoosh.notification.handlers.message.system.MessageSystemHandler;

public class a implements MessageSystemHandler {
    @Override // com.pushwoosh.notification.handlers.message.system.MessageSystemHandler
    @WorkerThread
    public boolean preHandleMessage(Bundle bundle) {
        if (com.pushwoosh.inbox.c.a.a(bundle) == null) {
            return false;
        }
        b.a().a(new b.a().a(bundle).a());
        return false;
    }
}
