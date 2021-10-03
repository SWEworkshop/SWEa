package com.pushwoosh.inbox.internal.a;

import com.pushwoosh.inbox.c.a;
import com.pushwoosh.internal.richmedia.ResourceAction;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class e implements b {
    e() {
    }

    @Override // com.pushwoosh.inbox.internal.a.b
    public void a(JSONObject jSONObject) throws JSONException {
        String b = a.b(jSONObject);
        if (b != null) {
            ResourceAction.performRemoteUrlAction(b);
        }
    }
}
