package com.pushwoosh.inbox.internal.a;

import com.pushwoosh.inbox.c.a;
import com.pushwoosh.internal.richmedia.ResourceAction;
import com.pushwoosh.internal.utils.PWLog;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class f implements b {
    f() {
    }

    @Override // com.pushwoosh.inbox.internal.a.b
    public void a(JSONObject jSONObject) throws JSONException {
        JSONObject c = a.c(jSONObject);
        if (c == null) {
            PWLog.noise("Incorrect richMedia action");
        } else {
            ResourceAction.performRichMediaAction(c.toString());
        }
    }
}
