package com.pushwoosh.inbox.internal.a;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class a implements b {
    private Context a = AndroidPlatformModule.getApplicationContext();

    a() {
    }

    @Override // com.pushwoosh.inbox.internal.a.b
    public void a(JSONObject jSONObject) throws JSONException {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(335544320);
        String a2 = com.pushwoosh.inbox.c.a.a(jSONObject);
        if (a2 != null) {
            intent.setData(Uri.parse(a2));
            try {
                this.a.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                PWLog.error("Can't find activity for deep link: " + a2, e);
            }
        }
    }
}
