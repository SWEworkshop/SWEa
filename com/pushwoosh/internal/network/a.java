package com.pushwoosh.internal.network;

import org.json.JSONObject;

public class a extends PushRequest<Void> {
    private String a;
    private String b;
    private JSONObject c;

    a(String str, String str2, JSONObject jSONObject) {
        this.a = str;
        this.b = str2;
        this.c = jSONObject;
    }

    public String a() {
        return this.a;
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public JSONObject b() {
        return this.c;
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return this.b;
    }
}
