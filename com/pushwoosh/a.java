package com.pushwoosh;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import com.pushwoosh.internal.Plugin;
import com.pushwoosh.internal.PluginProvider;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.d;
import com.pushwoosh.internal.utils.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* access modifiers changed from: package-private */
public class a implements d {
    private String a = null;
    private String b = null;
    private String c = null;
    private String d = null;
    private String e = null;
    private Class<?> f;
    private Class<?> g;
    private Class<?> h;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    @IdRes
    private int l = 0;
    @ColorInt
    private int m = 0;
    private final List<Plugin> n = new ArrayList();
    private PluginProvider o;

    /* JADX DEBUG: Multi-variable search result rejected for r3v12, resolved type: java.util.List<com.pushwoosh.internal.Plugin> */
    /* JADX WARN: Multi-variable type inference failed */
    a() {
        ApplicationInfo a2 = AndroidPlatformModule.getAppInfoProvider().a();
        if (a2 == null || a2.metaData == null) {
            PWLog.warn("Config", "no metadata found");
            return;
        }
        this.a = a(a2.metaData, "com.pushwoosh.appid", "PW_APPID");
        this.b = a(a2.metaData, "com.pushwoosh.senderid", "PW_PROJECT_ID");
        this.c = a(a2.metaData, "com.pushwoosh.vendor_package_name_prefix", null);
        if (!TextUtils.isEmpty(this.b) && !Character.isDigit(this.b.charAt(0))) {
            this.b = this.b.substring(1);
        }
        this.d = a(a2.metaData, "com.pushwoosh.log_level", "PW_LOG_LEVEL");
        this.e = a(a2.metaData, "com.pushwoosh.base_url", "PushwooshUrl");
        this.f = a(a2.metaData, "com.pushwoosh.notification_service_extension");
        this.g = a(a2.metaData, "com.pushwoosh.notification_factory");
        this.h = a(a2.metaData, "com.pushwoosh.summary_notification_factory");
        this.i = a2.metaData.getBoolean("com.pushwoosh.multi_notification_mode", false);
        this.j = a2.metaData.getBoolean("com.pushwoosh.light_screen_notification", false);
        this.k = a2.metaData.getBoolean("com.pushwoosh.send_push_stats_if_alert_disabled", true);
        String string = a2.metaData.getString("com.pushwoosh.notification_icon");
        if (string != null) {
            this.l = AndroidPlatformModule.getResourceProvider().a(e.b(e.a(string)), "drawable");
        }
        this.m = a2.metaData.getInt("com.pushwoosh.notification_icon_color", 0);
        for (String str : a2.metaData.keySet()) {
            if (str.startsWith("com.pushwoosh.plugin.")) {
                try {
                    Class<?> a3 = a(a2.metaData, str);
                    if (a3 != null) {
                        this.n.add(a3.newInstance());
                    }
                } catch (Exception unused) {
                }
            }
        }
        try {
            Class<?> a4 = a(a2.metaData, "com.pushwoosh.internal.plugin_provider");
            if (a4 != null) {
                this.o = (PluginProvider) a4.newInstance();
            }
        } catch (Exception unused2) {
        }
        if (this.o == null) {
            this.o = new com.pushwoosh.internal.a();
        }
    }

    private Class<?> a(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string != null && string.startsWith(".")) {
            string = AndroidPlatformModule.getAppInfoProvider().b() + string;
        }
        if (string == null) {
            return null;
        }
        try {
            Class<?> cls = Class.forName(string);
            cls.getConstructor(new Class[0]);
            return cls;
        } catch (ClassNotFoundException e2) {
            PWLog.exception(e2);
            throw new IllegalStateException("Could not find class for name: " + string);
        } catch (NoSuchMethodException e3) {
            PWLog.exception(e3);
            throw new IllegalStateException("Could not find public default constructor for class: " + string);
        }
    }

    private String a(Bundle bundle, String str, String str2) {
        String string = bundle.getString(str);
        if (string == null && (string = bundle.getString(str2)) != null) {
            PWLog.warn("'" + str2 + "' is deprecated consider using '" + str + "'");
        }
        return string;
    }

    @Override // com.pushwoosh.internal.utils.d
    public String a() {
        return this.a;
    }

    @Override // com.pushwoosh.internal.utils.d
    public String b() {
        return this.b;
    }

    @Override // com.pushwoosh.internal.utils.d
    public String c() {
        return this.c;
    }

    @Override // com.pushwoosh.internal.utils.d
    public String d() {
        return this.d;
    }

    @Override // com.pushwoosh.internal.utils.d
    public String e() {
        return this.e;
    }

    @Override // com.pushwoosh.internal.utils.d
    public Class<?> f() {
        return this.f;
    }

    @Override // com.pushwoosh.internal.utils.d
    public Class<?> g() {
        return this.g;
    }

    @Override // com.pushwoosh.internal.utils.d
    public Class<?> h() {
        return this.h;
    }

    @Override // com.pushwoosh.internal.utils.d
    public boolean i() {
        return this.i;
    }

    @Override // com.pushwoosh.internal.utils.d
    public boolean j() {
        return this.j;
    }

    @Override // com.pushwoosh.internal.utils.d
    @IdRes
    public int k() {
        return this.l;
    }

    @Override // com.pushwoosh.internal.utils.d
    @ColorInt
    public int l() {
        return this.m;
    }

    @Override // com.pushwoosh.internal.utils.d
    @NonNull
    public Collection<Plugin> m() {
        return this.n;
    }

    @Override // com.pushwoosh.internal.utils.d
    public PluginProvider n() {
        return this.o;
    }

    @Override // com.pushwoosh.internal.utils.d
    public boolean o() {
        return this.k;
    }
}
