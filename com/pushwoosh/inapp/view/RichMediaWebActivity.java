package com.pushwoosh.inapp.view;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.pushwoosh.inapp.d.a;
import com.pushwoosh.inapp.view.b;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.g;
import com.pushwoosh.q;
import com.pushwoosh.repository.RepositoryModule;

public class RichMediaWebActivity extends WebActivity implements b.a {
    final Handler a = new Handler();
    private boolean c;
    private boolean d;
    @Nullable
    private f e;
    private String f;
    private int g;
    private a h;
    private boolean i;
    private boolean j;
    private boolean k = false;
    private boolean l;

    private static Intent a(Context context) {
        return new Intent(context, RichMediaWebActivity.class);
    }

    public static Intent a(Context context, com.pushwoosh.inapp.e.b.b bVar) {
        return a(a(context), bVar, "", 0);
    }

    public static Intent a(Context context, com.pushwoosh.inapp.e.b.b bVar, String str) {
        Intent a2 = a(a(context), bVar, str, 1);
        a2.addFlags(343932928);
        return a2;
    }

    public static Intent b(Context context, com.pushwoosh.inapp.e.b.b bVar) {
        return a(a(context), bVar, "", 0);
    }

    private void f() {
        long timeOutBackButtonEnable = q.d().m().getTimeOutBackButtonEnable();
        if (timeOutBackButtonEnable == 0) {
            this.c = true;
        } else {
            this.a.postDelayed(h.a(this), timeOutBackButtonEnable);
        }
    }

    private void g() {
        if (this.e != null) {
            if ((this.g & 1) != 0) {
                getWindow().addFlags(524288);
                if (RepositoryModule.getNotificationPreferences().c().get()) {
                    g.c();
                }
            }
            Uri b = g.b(this.f);
            if (b != null && !this.l) {
                this.l = true;
                try {
                    RingtoneManager.getRingtone(this, b).play();
                } catch (Exception e2) {
                    PWLog.error("Failed parse ringtone with songUri: " + b, e2);
                }
            }
            if (!this.j) {
                if (this.b.l() && !this.i) {
                    this.i = true;
                    EventBus.sendEvent(new d(this.b));
                }
                this.e.e();
                this.j = true;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void h() {
        if (!this.k) {
            this.k = true;
            EventBus.sendEvent(new com.pushwoosh.inapp.event.b(this.b));
        }
    }

    @Override // com.pushwoosh.inapp.view.b.a
    public void a() {
        f fVar = this.e;
        if (fVar != null) {
            fVar.c();
        }
    }

    @Override // com.pushwoosh.inapp.view.b.a
    public void a(com.pushwoosh.inapp.b.a aVar) {
        PWLog.error("[InApp]RichMediaWebAct", "Failed loading html data", aVar);
        if (this.b.l()) {
            EventBus.sendEvent(new e(this.b));
        }
        c();
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.WebActivity
    public void a(com.pushwoosh.inapp.e.b.b bVar, String str, int i2) {
        this.f = str;
        this.g = i2;
        this.l = false;
        b bVar2 = (b) getFragmentManager().findFragmentByTag("[InApp]RichMediaWebActpushwoosh.inAppFragment");
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        if (bVar2 != null) {
            beginTransaction.remove(bVar2);
        }
        this.i = false;
        beginTransaction.add(b.a(bVar), "[InApp]RichMediaWebActpushwoosh.inAppFragment").commitAllowingStateLoss();
        this.i = false;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.WebActivity
    public void a(@Nullable f fVar) {
        this.e = fVar;
        setContentView(fVar);
        b bVar = (b) getFragmentManager().findFragmentByTag("[InApp]RichMediaWebActpushwoosh.inAppFragment");
        if (bVar != null) {
            bVar.b();
        }
    }

    @Override // com.pushwoosh.inapp.view.b.a
    public boolean a(a aVar) {
        if (this.e == null || aVar.equals(this.h)) {
            return false;
        }
        this.h = aVar;
        String b = aVar.b();
        String a2 = aVar.a();
        if (!a2.endsWith("/")) {
            a2 = a2 + "/";
        }
        this.e.a(a2, b.replace("<head>", "<head>\n<script type=\"text/javascript\">(function () {if (window.pushwoosh) return;window._pwCallbackHelper = {    __callbacks: {},    __cbCounter: 0,    invokeCallback: function(cbID) {        var args = Array.prototype.slice.call(arguments);        args.shift();        var cb = this.__callbacks[cbID];        this.__callbacks[cbID] = undefined;        return cb.apply(null, args);    },    registerCallback: function(func) {        var cbID = \"__cb\" + (+new Date) + this.__cbCounter;        this.__cbCounter++;        this.__callbacks[cbID] = func;        return cbID;    }};window.pushwoosh = {    _hwid: \"%s\",    _version: \"%s\",    postEvent: function(event, attributes, successCallback, errorCallback) {        if (!attributes) {            attributes = {};        }        if (!successCallback) {            successCallback = function() {};        }        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(successCallback);        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.postEvent(event, JSON.stringify(attributes), successCbId, errorCbId);    },    sendTags: function(tags) {        pushwooshImpl.sendTags(JSON.stringify(tags));    },    getTags: function(successCallback, errorCallback) {        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(function(tagsString) {            console.log(\"tags: \" + tagsString);            successCallback(JSON.parse(tagsString));        });        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.getTags(successCbId, errorCbId);    },    isCommunicationEnabled: function() {        return pushwooshImpl.isCommunicationEnabled();    },    setCommunicationEnabled: function(enabled) {        pushwooshImpl.setCommunicationEnabled(enabled);    },    removeAllDeviceData: function() {        pushwooshImpl.removeAllDeviceData();    },    log: function(str) {        pushwooshImpl.log(str);    },    closeInApp: function() {        pushwooshImpl.closeInApp();    },    getHwid: function() {        return this._hwid;    },    getVersion: function() {        return this._version;    },    getCustomData: function() {         var customData = pushwooshImpl.getCustomData();         if (customData) {             return JSON.parse(customData);         } else {             return null;         }    },    registerForPushNotifications: function() {        pushwooshImpl.registerForPushNotifications();    },    openAppSettings: function() {        pushwooshImpl.openAppSettings();    },    getChannels: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(channels) {             callback(JSON.parse(channels));        });        pushwooshImpl.getChannels(clb);    },    unregisterForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(callback);        pushwooshImpl.unregisterForPushNotifications(clb);    },    isRegisteredForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(state) {           if (state == 'true') {callback(true);} else if (state == 'false') {callback(false);}        });        pushwooshImpl.isRegisteredForPushNotifications(clb);    }};}());</script>"), "text/html", Key.STRING_CHARSET_NAME, null);
        return true;
    }

    @Override // com.pushwoosh.inapp.view.b.a
    public void b() {
        f fVar = this.e;
        if (fVar != null) {
            fVar.d();
        }
    }

    @Override // com.pushwoosh.inapp.view.c, com.pushwoosh.inapp.view.WebActivity
    public void c() {
        b bVar = (b) getFragmentManager().findFragmentByTag("[InApp]RichMediaWebActpushwoosh.inAppFragment");
        if (bVar != null) {
            getFragmentManager().beginTransaction().remove(bVar).commitAllowingStateLoss();
        }
        if (!this.d) {
            this.d = true;
            this.e.a(new Animation.AnimationListener() {
                /* class com.pushwoosh.inapp.view.RichMediaWebActivity.AnonymousClass1 */

                public void onAnimationEnd(Animation animation) {
                    RichMediaWebActivity.super.c();
                    RichMediaWebActivity.this.overridePendingTransition(0, 0);
                    RichMediaWebActivity.this.e.setVisibility(8);
                    RichMediaWebActivity.this.h();
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });
        }
    }

    @Override // com.pushwoosh.inapp.view.c, com.pushwoosh.inapp.view.WebActivity
    public void d() {
        super.d();
        g();
    }

    public void onBackPressed() {
        if (this.c) {
            c();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.WebActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            if (bundle.getBoolean("IS_CLOSED")) {
                finish();
                return;
            }
            this.j = bundle.getBoolean("IS_ANIMATED");
            this.l = bundle.getBoolean("KEY_IS_SOUND_PLAYED");
            this.f = bundle.getString("extraSound", "");
            this.g = bundle.getInt("extraMode", this.g);
        }
        f();
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.WebActivity
    public void onDestroy() {
        super.onDestroy();
        h();
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.WebActivity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("IS_ANIMATED", this.j);
        bundle.putBoolean("IS_CLOSED", this.d);
        bundle.putBoolean("KEY_IS_SOUND_PLAYED", this.l);
        bundle.putString("extraSound", this.f);
        bundle.putInt("extraMode", this.g);
    }
}
