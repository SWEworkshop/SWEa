package com.pushwoosh.inapp.a;

import com.pushwoosh.inapp.e.b.b;
import org.json.JSONObject;

public class i {
    private String a;
    private long b;

    public static i a(b bVar) {
        i iVar = new i();
        iVar.a = bVar.a();
        iVar.b = bVar.d();
        return iVar;
    }

    public static i a(JSONObject jSONObject) {
        i iVar = new i();
        iVar.a = jSONObject.optString("code");
        iVar.b = jSONObject.optLong("updated");
        return iVar;
    }

    public String a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }
}
