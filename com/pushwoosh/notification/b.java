package com.pushwoosh.notification;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.messaging.RemoteMessage;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.utils.GeneralUtils;
import com.pushwoosh.internal.utils.JsonUtils;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.g;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.s;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class b {
    private static final int a = 0;
    private static final int b = 1;

    static {
        int i = Build.VERSION.SDK_INT;
        int i2 = Build.VERSION.SDK_INT;
    }

    @Nullable
    public static String A(Bundle bundle) {
        return bundle.getString("value");
    }

    @Nullable
    public static String B(Bundle bundle) {
        return bundle.getString("rm");
    }

    @Nullable
    public static String C(Bundle bundle) {
        return bundle.getString("l");
    }

    @Nullable
    public static String D(Bundle bundle) {
        return bundle.getString("pw_channel");
    }

    @NonNull
    public static JSONObject E(Bundle bundle) {
        return JsonUtils.bundleToJsonWithUserData(bundle);
    }

    @Nullable
    public static String F(Bundle bundle) {
        return bundle.getString("h");
    }

    @Nullable
    public static String G(Bundle bundle) {
        return bundle.getString("r");
    }

    private static int a(Bundle bundle, String str, int i) {
        String string = bundle.getString(str);
        if (TextUtils.isEmpty(string)) {
            return (string == null || !string.isEmpty()) ? bundle.getInt(str, i) : i;
        }
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException unused) {
            PWLog.error("ERROR! Incorrect format for key [ " + str + " ]: " + string);
            return i;
        }
    }

    public static void a(Bundle bundle, String str) {
        bundle.putString("pw_msg_tag", str);
    }

    public static void a(@NonNull Bundle bundle, boolean z) {
        bundle.putBoolean("foreground", z);
        bundle.putBoolean("onStart", !z);
    }

    public static boolean a(Bundle bundle) {
        return g(bundle, "silent") || g(bundle, "pw_silent");
    }

    private static boolean a(Bundle bundle, int i) {
        return a(bundle, "pw_msg", 0) == i;
    }

    public static boolean a(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        return data != null && data.containsKey("pw_msg");
    }

    public static void b(Bundle bundle, String str) {
        bundle.putString("title", str);
    }

    public static void b(@NonNull Bundle bundle, boolean z) {
        bundle.putString("pw_msg", "1");
        bundle.putBoolean(ImagesContract.LOCAL, z);
    }

    public static boolean b(Bundle bundle) {
        return a(bundle, 1);
    }

    public static void c(Bundle bundle, String str) {
        bundle.putString("l", str);
    }

    public static boolean c(Bundle bundle) {
        return a(bundle, 2);
    }

    @Nullable
    public static String d(Bundle bundle) {
        return bundle.getString("pw_command");
    }

    public static void d(Bundle bundle, String str) {
        bundle.putString("b", str);
    }

    @Nullable
    public static String e(Bundle bundle) {
        return bundle.getString("p");
    }

    public static void e(Bundle bundle, String str) {
        bundle.putString("i", str);
    }

    @Nullable
    public static String f(Bundle bundle) {
        return bundle.getString("md");
    }

    public static void f(Bundle bundle, String str) {
        bundle.putString("ci", str);
    }

    public static boolean g(Bundle bundle) {
        return bundle.getBoolean(ImagesContract.LOCAL, false);
    }

    private static boolean g(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (TextUtils.equals(string, "true")) {
            return true;
        }
        try {
            return Integer.parseInt(string) > 0;
        } catch (Exception unused) {
            return false;
        }
    }

    @NonNull
    public static Integer h(Bundle bundle) {
        String string = bundle.getString("ibc");
        return Integer.valueOf(string != null ? Color.parseColor(string) : RepositoryModule.getNotificationPreferences().h().get());
    }

    @Nullable
    public static Integer i(Bundle bundle) {
        String string = bundle.getString("led");
        if (string != null) {
            return Integer.valueOf(GeneralUtils.parseColor(string));
        }
        return null;
    }

    public static boolean j(Bundle bundle) {
        String string = bundle.getString("vib");
        return !TextUtils.isEmpty(string) && string.equals("1");
    }

    @Nullable
    public static String k(Bundle bundle) {
        return bundle.getString(s.a);
    }

    @Nullable
    public static String l(Bundle bundle) {
        return bundle.getString("title");
    }

    public static String m(Bundle bundle) {
        String str = (String) bundle.get("header");
        if (str != null) {
            return str;
        }
        CharSequence i = AndroidPlatformModule.getAppInfoProvider().i();
        if (i == null) {
            i = "";
        }
        return i.toString();
    }

    public static int n(Bundle bundle) {
        int a2 = a(bundle, "pri", a);
        if (Math.abs(a2) <= 2) {
            return a2;
        }
        PWLog.warn("Unsupported priority: " + a2 + ", setting to default: 0");
        return a;
    }

    public static int o(Bundle bundle) {
        int a2 = a(bundle, "visibility", b);
        if (Math.abs(a2) <= 1) {
            return a2;
        }
        PWLog.warn("Unsupported visibility: " + a2 + ", setting to default: 1");
        return b;
    }

    public static int p(Bundle bundle) {
        return a(bundle, "pw_badges", -1);
    }

    @NonNull
    public static Collection<Action> q(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        try {
            String string = bundle.getString("pw_actions");
            if (string != null) {
                JSONArray jSONArray = new JSONArray(string);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(new Action(jSONArray.getJSONObject(i)));
                }
            }
        } catch (JSONException e) {
            PWLog.exception(e);
        }
        return arrayList;
    }

    @Nullable
    public static String r(Bundle bundle) {
        return bundle.getString("b");
    }

    @Nullable
    public static String s(Bundle bundle) {
        return bundle.getString("ci");
    }

    public static int t(Bundle bundle) {
        return g.a(bundle.getString("i"));
    }

    public static int u(Bundle bundle) {
        return a(bundle, "led_on_ms", 100);
    }

    public static int v(Bundle bundle) {
        return a(bundle, "led_off_ms", 1000);
    }

    @Nullable
    public static String w(Bundle bundle) {
        return bundle.getString("pw_msg_tag");
    }

    public static boolean x(Bundle bundle) {
        return g(bundle, "pw_lockscreen");
    }

    @Nullable
    public static String y(Bundle bundle) {
        return bundle.getString("u");
    }

    @Nullable
    public static String z(Bundle bundle) {
        return bundle.getString("packs");
    }
}
