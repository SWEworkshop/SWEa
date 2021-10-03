package com.pushwoosh.inbox.b.b;

import com.pushwoosh.inbox.internal.data.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    private Collection<String> a;
    private int b;
    private List<b> c;

    c(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.isNull("new_inbox")) {
            this.b = jSONObject.getInt("new_inbox");
        }
        if (!jSONObject.isNull("deleted")) {
            JSONArray jSONArray = jSONObject.getJSONArray("deleted");
            this.a = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                this.a.add(jSONArray.getString(i));
            }
        } else {
            this.a = Collections.emptyList();
        }
        if (!jSONObject.isNull("messages")) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("messages");
            this.c = new ArrayList(jSONArray2.length());
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                try {
                    this.c.add(new b.a().a(jSONArray2.getJSONObject(i2)).a());
                } catch (b.C0011b unused) {
                }
            }
            return;
        }
        this.c = Collections.emptyList();
    }

    public Collection<String> a() {
        return this.a;
    }

    public List<b> b() {
        return this.c;
    }
}
