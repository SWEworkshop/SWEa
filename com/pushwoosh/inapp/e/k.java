package com.pushwoosh.inapp.e;

import com.pushwoosh.internal.network.PushRequest;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class k extends PushRequest<Map<String, Object>> {
    private String a;

    k(String str) {
        this.a = str;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        jSONObject.put("userId", this.a);
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "registerUser";
    }
}
