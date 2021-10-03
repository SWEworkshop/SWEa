package com.pushwoosh.inapp.e;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.pushwoosh.inapp.e.b.b;
import com.pushwoosh.inapp.f.d;
import org.json.JSONException;
import org.json.JSONObject;

public class j {
    private final String a;
    private final b b;
    private final boolean c;

    @WorkerThread
    j(JSONObject jSONObject) throws JSONException {
        d b2;
        this.a = jSONObject.optString("code");
        this.c = jSONObject.optBoolean("required", false);
        if (this.a == null || (b2 = com.pushwoosh.inapp.b.b()) == null) {
            this.b = null;
        } else {
            this.b = b2.a(this.a);
        }
    }

    @Nullable
    public b a() {
        return this.b;
    }

    public String b() {
        return this.a;
    }

    public boolean c() {
        return this.c;
    }
}
