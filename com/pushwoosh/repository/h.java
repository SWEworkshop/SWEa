package com.pushwoosh.repository;

import androidx.annotation.NonNull;
import com.pushwoosh.tags.TagsBundle;
import org.json.JSONException;
import org.json.JSONObject;

public class h extends g {
    private String a;

    public h(String str) {
        this.a = str;
    }

    @Override // com.pushwoosh.repository.g
    public /* bridge */ /* synthetic */ TagsBundle a(@NonNull JSONObject jSONObject) throws JSONException {
        return super.parseResponse(jSONObject);
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    @NonNull
    public String getHwid() {
        return this.a;
    }

    @Override // com.pushwoosh.repository.g, com.pushwoosh.internal.network.PushRequest
    public /* bridge */ /* synthetic */ String getMethod() {
        return super.getMethod();
    }
}
