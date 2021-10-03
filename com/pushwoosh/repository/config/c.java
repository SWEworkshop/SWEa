package com.pushwoosh.repository.config;

import androidx.annotation.NonNull;
import com.pushwoosh.internal.network.PushRequest;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends PushRequest<a> {
    @NonNull
    /* renamed from: a */
    public a parseResponse(@NonNull JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("features");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        String str = "";
        if (optJSONObject != null) {
            JSONArray optJSONArray = optJSONObject.optJSONArray("channels");
            JSONArray optJSONArray2 = optJSONObject.optJSONArray("events");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(new Channel(optJSONArray.getJSONObject(i)));
                }
            }
            if (optJSONArray2 != null) {
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    String optString = optJSONArray2.optString(i2);
                    if (!optString.isEmpty()) {
                        arrayList2.add(new b(optString));
                    }
                }
            }
            str = optJSONObject.getString("public_key");
        }
        return new a(arrayList, arrayList2, str);
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("channels");
        jSONArray.put("events");
        jSONArray.put("public_key");
        jSONObject.put("features", jSONArray);
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "getConfig";
    }
}
