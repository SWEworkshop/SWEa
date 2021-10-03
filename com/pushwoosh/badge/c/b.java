package com.pushwoosh.badge.c;

import com.pushwoosh.internal.network.PushRequest;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class b extends PushRequest<Void> {
    private int a;

    b(int i) {
        this.a = i;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        jSONObject.put("badge", this.a);
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "setBadge";
    }
}
