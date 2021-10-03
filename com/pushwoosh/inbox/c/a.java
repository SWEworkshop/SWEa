package com.pushwoosh.inbox.c;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.pushwoosh.inbox.data.InboxMessageType;
import com.pushwoosh.internal.utils.JsonUtils;
import com.pushwoosh.internal.utils.PWLog;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    public static InboxMessageType a(String str) {
        if (TextUtils.isEmpty(str)) {
            return InboxMessageType.PLAIN;
        }
        try {
            return d(new JSONObject(str));
        } catch (JSONException unused) {
            PWLog.noise("Failed to parse inbox type from actionPayload");
            return InboxMessageType.PLAIN;
        }
    }

    @Nullable
    public static String a(Bundle bundle) {
        return bundle.getString("pw_inbox", null);
    }

    @Nullable
    public static String a(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("l")) {
            return null;
        }
        return jSONObject.getString("l");
    }

    @Nullable
    public static String b(Bundle bundle) {
        return bundle.getString("header", null);
    }

    @Nullable
    public static String b(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("r")) {
            return null;
        }
        return jSONObject.getString("r");
    }

    public static long c(Bundle bundle) {
        return bundle.getLong("google.sent_time", System.currentTimeMillis());
    }

    @Nullable
    public static JSONObject c(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("rm")) {
            return null;
        }
        return new JSONObject(jSONObject.getString("rm"));
    }

    private static InboxMessageType d(JSONObject jSONObject) throws JSONException {
        if (c(jSONObject) != null) {
            return InboxMessageType.RICH_MEDIA;
        }
        String a = a(jSONObject);
        return a != null ? a.startsWith("http") ? InboxMessageType.URL : InboxMessageType.DEEP_LINK : b(jSONObject) != null ? InboxMessageType.REMOTE_URL : InboxMessageType.PLAIN;
    }

    @Nullable
    public static String d(Bundle bundle) {
        return bundle.getString("title");
    }

    @Nullable
    public static String e(Bundle bundle) {
        return bundle.getString("inbox_params");
    }

    public static InboxMessageType f(Bundle bundle) {
        try {
            return d(JsonUtils.bundleToJsonWithUserData(bundle));
        } catch (JSONException unused) {
            PWLog.noise("Failed to parse inbox type form bundle");
            return InboxMessageType.PLAIN;
        }
    }

    public static String g(Bundle bundle) {
        return bundle.getString("p", null);
    }
}
