package com.pushwoosh.inapp.view.a;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import com.pushwoosh.GDPRManager;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.exception.GetTagsException;
import com.pushwoosh.exception.PostEventException;
import com.pushwoosh.exception.UnregisterForPushNotificationException;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.q;
import com.pushwoosh.repository.config.Channel;
import com.pushwoosh.tags.Tags;
import com.pushwoosh.tags.TagsBundle;
import java.lang.ref.WeakReference;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    public static final String PUSHWOOSH_JS = "(function () {if (window.pushwoosh) return;window._pwCallbackHelper = {    __callbacks: {},    __cbCounter: 0,    invokeCallback: function(cbID) {        var args = Array.prototype.slice.call(arguments);        args.shift();        var cb = this.__callbacks[cbID];        this.__callbacks[cbID] = undefined;        return cb.apply(null, args);    },    registerCallback: function(func) {        var cbID = \"__cb\" + (+new Date) + this.__cbCounter;        this.__cbCounter++;        this.__callbacks[cbID] = func;        return cbID;    }};window.pushwoosh = {    _hwid: \"%s\",    _version: \"%s\",    postEvent: function(event, attributes, successCallback, errorCallback) {        if (!attributes) {            attributes = {};        }        if (!successCallback) {            successCallback = function() {};        }        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(successCallback);        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.postEvent(event, JSON.stringify(attributes), successCbId, errorCbId);    },    sendTags: function(tags) {        pushwooshImpl.sendTags(JSON.stringify(tags));    },    getTags: function(successCallback, errorCallback) {        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(function(tagsString) {            console.log(\"tags: \" + tagsString);            successCallback(JSON.parse(tagsString));        });        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.getTags(successCbId, errorCbId);    },    isCommunicationEnabled: function() {        return pushwooshImpl.isCommunicationEnabled();    },    setCommunicationEnabled: function(enabled) {        pushwooshImpl.setCommunicationEnabled(enabled);    },    removeAllDeviceData: function() {        pushwooshImpl.removeAllDeviceData();    },    log: function(str) {        pushwooshImpl.log(str);    },    closeInApp: function() {        pushwooshImpl.closeInApp();    },    getHwid: function() {        return this._hwid;    },    getVersion: function() {        return this._version;    },    getCustomData: function() {         var customData = pushwooshImpl.getCustomData();         if (customData) {             return JSON.parse(customData);         } else {             return null;         }    },    registerForPushNotifications: function() {        pushwooshImpl.registerForPushNotifications();    },    openAppSettings: function() {        pushwooshImpl.openAppSettings();    },    getChannels: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(channels) {             callback(JSON.parse(channels));        });        pushwooshImpl.getChannels(clb);    },    unregisterForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(callback);        pushwooshImpl.unregisterForPushNotifications(clb);    },    isRegisteredForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(state) {           if (state == 'true') {callback(true);} else if (state == 'false') {callback(false);}        });        pushwooshImpl.isRegisteredForPushNotifications(clb);    }};}());";
    private final a a;
    private final WeakReference<WebView> b;
    private final View c;
    private final String d;
    private final Handler e = new Handler(Looper.getMainLooper());

    public d(a aVar, WebView webView, @Nullable View view, @Nullable String str) {
        this.a = aVar;
        this.b = new WeakReference<>(webView);
        this.c = view;
        this.d = str;
    }

    static /* synthetic */ void a(d dVar, String str, Result result) {
        if (result.getException() == null) {
            dVar.b(str);
        } else {
            dVar.a(str, ((UnregisterForPushNotificationException) result.getException()).getMessage());
        }
    }

    static /* synthetic */ void a(d dVar, String str, String str2, Result result) {
        if (result.isSuccess()) {
            TagsBundle tagsBundle = (TagsBundle) result.getData();
            dVar.a(str, (tagsBundle == null ? new JSONObject() : tagsBundle.toJson()).toString());
            return;
        }
        dVar.a(str2, ((GetTagsException) result.getException()).getMessage());
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        WebView webView = this.b.get();
        if (webView != null) {
            webView.loadUrl(str);
        }
    }

    private void a(String str, String str2) {
        this.e.post(g.a(this, String.format("javascript:_pwCallbackHelper.invokeCallback(\"%s\", \"%s\");", str, str2.replace("\"", "\\\""))));
    }

    static /* synthetic */ void b(d dVar, String str, String str2, Result result) {
        if (result.isSuccess()) {
            dVar.b(str);
        } else {
            dVar.a(str2, ((PostEventException) result.getException()).getLocalizedMessage());
        }
    }

    private void b(String str) {
        this.e.post(f.a(this, String.format("javascript:_pwCallbackHelper.invokeCallback(\"%s\");", str)));
    }

    @JavascriptInterface
    public void closeInApp() {
        this.a.a();
    }

    @JavascriptInterface
    public void getChannels(String str) {
        List<Channel> e2 = q.d().g().e();
        JSONArray jSONArray = new JSONArray();
        for (Channel channel : e2) {
            jSONArray.put(channel.jsonValue());
        }
        a(str, jSONArray.toString());
    }

    @JavascriptInterface
    public String getCustomData() {
        return this.d;
    }

    @JavascriptInterface
    public void getTags(String str, String str2) {
        Pushwoosh.getInstance().getTags(h.a(this, str, str2));
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
    public void isRegisteredForPushNotifications(String str) {
        boolean z;
        try {
            z = q.d().q().isRegisteredForPush().get();
        } catch (Exception unused) {
            z = false;
        }
        a(str, z ? "true" : "false");
    }

    @JavascriptInterface
    public void log(String str) {
        PWLog.debug("[InApp]PushwooshJSInterface", str);
    }

    public void onPageFinished(WebView webView) {
        webView.loadUrl(String.format("javascript:(function () {if (window.pushwoosh) return;window._pwCallbackHelper = {    __callbacks: {},    __cbCounter: 0,    invokeCallback: function(cbID) {        var args = Array.prototype.slice.call(arguments);        args.shift();        var cb = this.__callbacks[cbID];        this.__callbacks[cbID] = undefined;        return cb.apply(null, args);    },    registerCallback: function(func) {        var cbID = \"__cb\" + (+new Date) + this.__cbCounter;        this.__cbCounter++;        this.__callbacks[cbID] = func;        return cbID;    }};window.pushwoosh = {    _hwid: \"%s\",    _version: \"%s\",    postEvent: function(event, attributes, successCallback, errorCallback) {        if (!attributes) {            attributes = {};        }        if (!successCallback) {            successCallback = function() {};        }        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(successCallback);        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.postEvent(event, JSON.stringify(attributes), successCbId, errorCbId);    },    sendTags: function(tags) {        pushwooshImpl.sendTags(JSON.stringify(tags));    },    getTags: function(successCallback, errorCallback) {        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(function(tagsString) {            console.log(\"tags: \" + tagsString);            successCallback(JSON.parse(tagsString));        });        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.getTags(successCbId, errorCbId);    },    isCommunicationEnabled: function() {        return pushwooshImpl.isCommunicationEnabled();    },    setCommunicationEnabled: function(enabled) {        pushwooshImpl.setCommunicationEnabled(enabled);    },    removeAllDeviceData: function() {        pushwooshImpl.removeAllDeviceData();    },    log: function(str) {        pushwooshImpl.log(str);    },    closeInApp: function() {        pushwooshImpl.closeInApp();    },    getHwid: function() {        return this._hwid;    },    getVersion: function() {        return this._version;    },    getCustomData: function() {         var customData = pushwooshImpl.getCustomData();         if (customData) {             return JSON.parse(customData);         } else {             return null;         }    },    registerForPushNotifications: function() {        pushwooshImpl.registerForPushNotifications();    },    openAppSettings: function() {        pushwooshImpl.openAppSettings();    },    getChannels: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(channels) {             callback(JSON.parse(channels));        });        pushwooshImpl.getChannels(clb);    },    unregisterForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(callback);        pushwooshImpl.unregisterForPushNotifications(clb);    },    isRegisteredForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(state) {           if (state == 'true') {callback(true);} else if (state == 'false') {callback(false);}        });        pushwooshImpl.isRegisteredForPushNotifications(clb);    }};}());", Pushwoosh.getInstance().getHwid(), "5.22.6"));
    }

    public void onPageStarted(WebView webView) {
        webView.loadUrl(String.format("javascript:(function () {if (window.pushwoosh) return;window._pwCallbackHelper = {    __callbacks: {},    __cbCounter: 0,    invokeCallback: function(cbID) {        var args = Array.prototype.slice.call(arguments);        args.shift();        var cb = this.__callbacks[cbID];        this.__callbacks[cbID] = undefined;        return cb.apply(null, args);    },    registerCallback: function(func) {        var cbID = \"__cb\" + (+new Date) + this.__cbCounter;        this.__cbCounter++;        this.__callbacks[cbID] = func;        return cbID;    }};window.pushwoosh = {    _hwid: \"%s\",    _version: \"%s\",    postEvent: function(event, attributes, successCallback, errorCallback) {        if (!attributes) {            attributes = {};        }        if (!successCallback) {            successCallback = function() {};        }        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(successCallback);        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.postEvent(event, JSON.stringify(attributes), successCbId, errorCbId);    },    sendTags: function(tags) {        pushwooshImpl.sendTags(JSON.stringify(tags));    },    getTags: function(successCallback, errorCallback) {        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(function(tagsString) {            console.log(\"tags: \" + tagsString);            successCallback(JSON.parse(tagsString));        });        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.getTags(successCbId, errorCbId);    },    isCommunicationEnabled: function() {        return pushwooshImpl.isCommunicationEnabled();    },    setCommunicationEnabled: function(enabled) {        pushwooshImpl.setCommunicationEnabled(enabled);    },    removeAllDeviceData: function() {        pushwooshImpl.removeAllDeviceData();    },    log: function(str) {        pushwooshImpl.log(str);    },    closeInApp: function() {        pushwooshImpl.closeInApp();    },    getHwid: function() {        return this._hwid;    },    getVersion: function() {        return this._version;    },    getCustomData: function() {         var customData = pushwooshImpl.getCustomData();         if (customData) {             return JSON.parse(customData);         } else {             return null;         }    },    registerForPushNotifications: function() {        pushwooshImpl.registerForPushNotifications();    },    openAppSettings: function() {        pushwooshImpl.openAppSettings();    },    getChannels: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(channels) {             callback(JSON.parse(channels));        });        pushwooshImpl.getChannels(clb);    },    unregisterForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(callback);        pushwooshImpl.unregisterForPushNotifications(clb);    },    isRegisteredForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(state) {           if (state == 'true') {callback(true);} else if (state == 'false') {callback(false);}        });        pushwooshImpl.isRegisteredForPushNotifications(clb);    }};}());", Pushwoosh.getInstance().getHwid(), "5.22.6"));
    }

    @JavascriptInterface
    public void openAppSettings() {
        if (AndroidPlatformModule.getInstance() != null) {
            Context applicationContext = AndroidPlatformModule.getApplicationContext();
            Intent intent = new Intent();
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", applicationContext.getPackageName());
            intent.putExtra("app_uid", applicationContext.getApplicationInfo().uid);
            intent.addFlags(268435456);
            intent.putExtra("android.provider.extra.APP_PACKAGE", applicationContext.getPackageName());
            applicationContext.startActivity(intent);
        }
    }

    @JavascriptInterface
    public void postEvent(String str, String str2, String str3, String str4) {
        try {
            q.d().h().a(str, Tags.fromJson(new JSONObject(str2)), e.a(this, str3, str4));
        } catch (Exception e2) {
            PWLog.error("postEvent method was failed", e2);
            a(str4, e2.getLocalizedMessage());
        }
    }

    @JavascriptInterface
    public void registerForPushNotifications() {
        Pushwoosh.getInstance().registerForPushNotifications();
    }

    @JavascriptInterface
    public void removeAllDeviceData() {
        GDPRManager.getInstance().removeAllDeviceData(null);
    }

    @JavascriptInterface
    public void sendTags(String str) {
        try {
            Pushwoosh.getInstance().sendTags(new TagsBundle.Builder().putAll(new JSONObject(str)).build());
        } catch (JSONException e2) {
            PWLog.error("Invalid tags format, expected object with string properties", e2);
        }
    }

    @JavascriptInterface
    public void setCommunicationEnabled(boolean z) {
        GDPRManager.getInstance().setCommunicationEnabled(z, null);
    }

    @JavascriptInterface
    public void unregisterForPushNotifications(String str) {
        Pushwoosh.getInstance().unregisterForPushNotifications(i.a(this, str));
    }
}
