package com.pushwoosh.repository;

import com.pushwoosh.internal.network.PushRequest;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class p extends PushRequest<Void> {
    private String a;

    public p(String str) {
        this.a = str;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        String str = this.a;
        if (str != null) {
            jSONObject.put("hash", str);
        }
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "messageDeliveryEvent";
    }
}
