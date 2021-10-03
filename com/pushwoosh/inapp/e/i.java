package com.pushwoosh.inapp.e;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.pushwoosh.internal.network.PushRequest;
import com.pushwoosh.tags.Tags;
import com.pushwoosh.tags.TagsBundle;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class i extends PushRequest<j> {
    private String a;
    private TagsBundle b;
    private String c;

    i(String str, String str2, @Nullable TagsBundle tagsBundle) {
        this.b = tagsBundle == null ? Tags.empty() : tagsBundle;
        this.a = str2;
        this.c = str;
    }

    /* renamed from: a */
    public j parseResponse(@NonNull JSONObject jSONObject) throws JSONException {
        return new j(jSONObject);
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        if (!TextUtils.isEmpty(this.a)) {
            this.b = new TagsBundle.Builder().putAll(this.b.toJson()).putString("msgHash", this.a).build();
        }
        jSONObject.put("attributes", this.b.toJson());
        jSONObject.put(NotificationCompat.CATEGORY_EVENT, this.c);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        jSONObject.put("timestampUTC", currentTimeMillis);
        jSONObject.put("timestampCurrent", ((long) (Calendar.getInstance().getTimeZone().getRawOffset() / 1000)) + currentTimeMillis);
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "postEvent";
    }
}
