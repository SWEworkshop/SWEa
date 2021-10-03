package com.pushwoosh.notification.handlers.message.system;

import android.content.Intent;
import android.os.Bundle;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.utils.a;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;

/* access modifiers changed from: package-private */
public class b implements MessageSystemHandler {
    b() {
    }

    private void a(Bundle bundle) {
        String b = AndroidPlatformModule.getAppInfoProvider().b();
        Intent intent = new Intent();
        intent.setAction(b + ".action.SILENT_PUSH_RECEIVE");
        intent.putExtras(bundle);
        intent.putExtra("pw_data_json_string", com.pushwoosh.notification.b.E(bundle).toString());
        AndroidPlatformModule.getReceiverProvider().a(intent, DeviceSpecificProvider.getInstance().permission(b));
    }

    @Override // com.pushwoosh.notification.handlers.message.system.MessageSystemHandler
    public boolean preHandleMessage(Bundle bundle) {
        if (com.pushwoosh.notification.b.a(bundle)) {
            a(bundle);
        }
        com.pushwoosh.notification.b.a(bundle, a.p());
        return false;
    }
}
