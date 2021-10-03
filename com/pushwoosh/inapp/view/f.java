package com.pushwoosh.inapp.view;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.pushwoosh.R;
import com.pushwoosh.inapp.e.b.a;
import com.pushwoosh.q;
import com.pushwoosh.richmedia.RichMediaStyle;
import com.pushwoosh.richmedia.animation.RichMediaAnimation;

@SuppressLint({"ViewConstructor"})
public class f extends FrameLayout {
    protected FrameLayout a;
    protected WebView b;
    int c;
    boolean d;
    private a e = a.FULLSCREEN;
    private View f;
    private boolean g;
    private boolean h = false;
    private Runnable i;
    private RichMediaAnimation j;
    private Handler k;

    protected f(Context context) {
        super(context);
        a(a.FULLSCREEN, q.d().i().a());
    }

    protected f(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(a.FULLSCREEN, q.d().i().a());
    }

    protected f(@NonNull Context context, a aVar, RichMediaStyle richMediaStyle, boolean z) {
        super(context);
        this.d = z;
        a(aVar, richMediaStyle);
    }

    private View a(Context context) {
        int i2 = context.getApplicationInfo().theme;
        if (i2 == 0) {
            i2 = Build.VERSION.SDK_INT >= 21 ? 16974372 : 16973931;
        }
        View inflate = View.inflate(new ContextThemeWrapper(context, i2), R.layout.pw_default_loading_view, null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        inflate.setLayoutParams(layoutParams);
        return inflate;
    }

    private void a(View view, int i2, int i3) {
        ObjectAnimator ofInt = ObjectAnimator.ofInt(view, "backgroundColor", i2, i3);
        ofInt.setEvaluator(new ArgbEvaluator());
        ofInt.setDuration(300L);
        ofInt.start();
    }

    private void a(a aVar, RichMediaStyle richMediaStyle) {
        this.e = aVar;
        this.j = richMediaStyle.getRichMediaAnimation();
        this.c = richMediaStyle.getBackgroundColor();
        if (this.c == 0) {
            this.c = Color.parseColor("#40000000");
        }
        this.a = new FrameLayout(getContext());
        this.a.setLayoutParams(a(aVar, 0));
        this.a.setBackgroundColor(0);
        b();
        this.f = richMediaStyle.getLoadingViewCreator() != null ? richMediaStyle.getLoadingViewCreator().a() : a(getContext());
        this.f.setVisibility(8);
        this.a.addView(this.b);
        this.a.addView(this.f);
        addView(this.a);
    }

    static /* synthetic */ void a(f fVar) {
        fVar.f.setAlpha(0.0f);
        fVar.f.setVisibility(0);
        fVar.f.animate().alpha(1.0f).setDuration(300).start();
    }

    /* access modifiers changed from: protected */
    public WebView a() {
        return new WebView(getContext());
    }

    /* access modifiers changed from: protected */
    @NonNull
    public FrameLayout.LayoutParams a(a aVar, int i2) {
        int i3;
        FrameLayout.LayoutParams layoutParams;
        switch (aVar) {
            case FULLSCREEN:
                return new FrameLayout.LayoutParams(-1, -1);
            case BOTTOM:
                layoutParams = new FrameLayout.LayoutParams(-1, -2);
                i3 = 80;
                break;
            case TOP:
                FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
                layoutParams2.gravity = 48;
                if (this.d) {
                    return layoutParams2;
                }
                layoutParams2.topMargin = i2;
                return layoutParams2;
            case DIALOG:
                layoutParams = new FrameLayout.LayoutParams(-1, -2);
                i3 = 17;
                break;
            default:
                throw new IllegalArgumentException("Unrecognized mode: " + aVar.toString());
        }
        layoutParams.gravity = i3;
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public void a(Animation.AnimationListener animationListener) {
        a(this.a, this.c, Color.alpha(0));
        RichMediaAnimation richMediaAnimation = this.j;
        if (richMediaAnimation != null) {
            richMediaAnimation.closeAnimation(this.b, this.a, animationListener);
        }
    }

    /* access modifiers changed from: protected */
    public void a(com.pushwoosh.inapp.d.a aVar) {
        String b2 = aVar.b();
        String a2 = aVar.a();
        if (!a2.endsWith("/")) {
            a2 = a2 + "/";
        }
        a(a2, b2.replace("<head>", "<head>\n<script type=\"text/javascript\">(function () {if (window.pushwoosh) return;window._pwCallbackHelper = {    __callbacks: {},    __cbCounter: 0,    invokeCallback: function(cbID) {        var args = Array.prototype.slice.call(arguments);        args.shift();        var cb = this.__callbacks[cbID];        this.__callbacks[cbID] = undefined;        return cb.apply(null, args);    },    registerCallback: function(func) {        var cbID = \"__cb\" + (+new Date) + this.__cbCounter;        this.__cbCounter++;        this.__callbacks[cbID] = func;        return cbID;    }};window.pushwoosh = {    _hwid: \"%s\",    _version: \"%s\",    postEvent: function(event, attributes, successCallback, errorCallback) {        if (!attributes) {            attributes = {};        }        if (!successCallback) {            successCallback = function() {};        }        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(successCallback);        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.postEvent(event, JSON.stringify(attributes), successCbId, errorCbId);    },    sendTags: function(tags) {        pushwooshImpl.sendTags(JSON.stringify(tags));    },    getTags: function(successCallback, errorCallback) {        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(function(tagsString) {            console.log(\"tags: \" + tagsString);            successCallback(JSON.parse(tagsString));        });        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.getTags(successCbId, errorCbId);    },    isCommunicationEnabled: function() {        return pushwooshImpl.isCommunicationEnabled();    },    setCommunicationEnabled: function(enabled) {        pushwooshImpl.setCommunicationEnabled(enabled);    },    removeAllDeviceData: function() {        pushwooshImpl.removeAllDeviceData();    },    log: function(str) {        pushwooshImpl.log(str);    },    closeInApp: function() {        pushwooshImpl.closeInApp();    },    getHwid: function() {        return this._hwid;    },    getVersion: function() {        return this._version;    },    getCustomData: function() {         var customData = pushwooshImpl.getCustomData();         if (customData) {             return JSON.parse(customData);         } else {             return null;         }    },    registerForPushNotifications: function() {        pushwooshImpl.registerForPushNotifications();    },    openAppSettings: function() {        pushwooshImpl.openAppSettings();    },    getChannels: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(channels) {             callback(JSON.parse(channels));        });        pushwooshImpl.getChannels(clb);    },    unregisterForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(callback);        pushwooshImpl.unregisterForPushNotifications(clb);    },    isRegisteredForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(state) {           if (state == 'true') {callback(true);} else if (state == 'false') {callback(false);}        });        pushwooshImpl.isRegisteredForPushNotifications(clb);    }};}());</script>"), "text/html", Key.STRING_CHARSET_NAME, null);
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        this.b.loadUrl(str);
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, String str3, String str4, String str5) {
        this.b.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"SetJavaScriptEnabled"})
    public void b() {
        this.b = a();
        this.b.getSettings().setJavaScriptEnabled(true);
        this.b.setLayoutParams(a(this.e, getStatusBarHeight()));
        this.b.setBackgroundColor(0);
        this.b.setLongClickable(false);
        this.b.setHapticFeedbackEnabled(false);
    }

    /* access modifiers changed from: protected */
    public void c() {
        if (!this.h) {
            this.h = true;
            this.k = new Handler();
            Handler handler = this.k;
            Runnable a2 = g.a(this);
            this.i = a2;
            handler.postDelayed(a2, 500);
            this.b.setVisibility(4);
        }
    }

    /* access modifiers changed from: protected */
    public void d() {
        if (this.h) {
            this.h = false;
            Handler handler = this.k;
            if (handler != null) {
                Runnable runnable = this.i;
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                    this.i = null;
                }
                this.k = null;
            }
            this.f.animate().alpha(0.0f).setDuration(300).start();
            this.b.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void e() {
        if (!this.g) {
            a(this.a, Color.alpha(0), this.c);
            this.g = true;
            RichMediaAnimation richMediaAnimation = this.j;
            if (richMediaAnimation != null) {
                richMediaAnimation.openAnimation(this.b, this.a);
                return;
            }
            return;
        }
        this.a.setBackgroundColor(this.c);
    }

    /* access modifiers changed from: protected */
    public void f() {
        this.b.setWebViewClient(null);
        this.b = null;
    }

    public int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void setWebViewClient(i iVar) {
        iVar.a(this.b);
    }
}
