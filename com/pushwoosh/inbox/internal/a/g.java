package com.pushwoosh.inbox.internal.a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.pushwoosh.inbox.c.a;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class g implements b {
    g() {
    }

    @Override // com.pushwoosh.inbox.internal.a.b
    public void a(JSONObject jSONObject) throws JSONException {
        String a = a.a(jSONObject);
        if (a != null) {
            Context applicationContext = AndroidPlatformModule.getApplicationContext();
            if (applicationContext == null) {
                PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
                return;
            }
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(a));
            intent.addFlags(268435456);
            applicationContext.startActivity(intent);
        }
    }
}
