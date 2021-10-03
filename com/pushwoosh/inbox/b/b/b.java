package com.pushwoosh.inbox.b.b;

import androidx.annotation.NonNull;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends a<c> {
    /* renamed from: a */
    public c parseResponse(@NonNull JSONObject jSONObject) throws JSONException {
        return new c(jSONObject);
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "getInboxMessages";
    }
}
