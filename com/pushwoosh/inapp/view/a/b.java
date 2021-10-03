package com.pushwoosh.inapp.view.a;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pushwoosh.GDPRManager;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.exception.PostEventException;
import com.pushwoosh.exception.RegisterForPushNotificationsException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.q;
import com.pushwoosh.tags.Tags;
import com.pushwoosh.tags.TagsBundle;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private final WeakReference<WebView> a;
    private final a b;

    public b(WebView webView, a aVar) {
        this.a = new WeakReference<>(webView);
        this.b = aVar;
    }

    static /* synthetic */ void a(b bVar, String str, String str2, Result result) {
        WebView webView;
        if (str != null && (webView = bVar.a.get()) != null) {
            webView.loadUrl(result.isSuccess() ? String.format("javascript:%s();", str) : String.format("javascript:%s('%s');", str2, ((PostEventException) result.getException()).getMessage()));
        }
    }

    @JavascriptInterface
    public void closeInApp() {
        this.b.a();
    }

    @JavascriptInterface
    public boolean isCommunicationEnabled() {
        return GDPRManager.getInstance().isCommunicationEnabled();
    }

    @JavascriptInterface
    public boolean isDeviceDataRemoved() {
        return GDPRManager.getInstance().isDeviceDataRemoved();
    }

    @JavascriptInterface
    public void log(String str) {
        PWLog.debug("[InApp]PushManagerJSInterface", str);
    }

    @JavascriptInterface
    public void postEvent(String str) {
        String str2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(NotificationCompat.CATEGORY_EVENT);
            JSONObject jSONObject2 = jSONObject.getJSONObject("attributes");
            String str3 = null;
            try {
                str2 = jSONObject.getString(FirebaseAnalytics.Param.SUCCESS);
            } catch (JSONException unused) {
                str2 = null;
            }
            try {
                str3 = jSONObject.getString("error");
            } catch (JSONException unused2) {
            }
            q.d().h().a(string, Tags.fromJson(jSONObject2), c.a(this, str2, str3));
        } catch (JSONException e) {
            PWLog.error("Invalid arguments", e);
        }
    }

    @JavascriptInterface
    public void registerForPushNotifications() {
        if (q.d() != null) {
            q.d().f().a((Callback<String, RegisterForPushNotificationsException>) null);
        }
    }

    @JavascriptInterface
    public void removeAllDeviceData() {
        GDPRManager.getInstance().removeAllDeviceData(null);
    }

    @JavascriptInterface
    public void sendTags(String str) {
        try {
            Pushwoosh.getInstance().sendTags(new TagsBundle.Builder().putAll(new JSONObject(str)).build());
        } catch (JSONException e) {
            PWLog.error("Invalid tags format, expected object with string properties", e);
        }
    }

    @JavascriptInterface
    public void setCommunicationEnabled(boolean z) {
        GDPRManager.getInstance().setCommunicationEnabled(z, null);
    }
}
