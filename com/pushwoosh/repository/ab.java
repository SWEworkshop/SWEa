package com.pushwoosh.repository;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Pair;
import com.pushwoosh.internal.network.PushRequest;
import com.pushwoosh.internal.network.g;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.utils.GeneralUtils;
import com.pushwoosh.internal.platform.utils.a;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.q;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ab extends PushRequest<Void> implements g {
    private final List<String> a;

    public ab(List<String> list) {
        this.a = list;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:55:? */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v22, types: [boolean] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        if (this.a != null) {
            List<String> n = a.n();
            JSONArray jSONArray = new JSONArray();
            if (n != null) {
                for (String str : this.a) {
                    if (n.contains(str)) {
                        jSONArray.put(GeneralUtils.md5(str));
                    }
                }
            }
            jSONObject2.put("packs", jSONArray);
        }
        try {
            TelephonyManager telephonyManager = AndroidPlatformModule.getManagerProvider().getTelephonyManager();
            if (telephonyManager != null) {
                String simOperator = telephonyManager.getSimOperator();
                if (!TextUtils.isEmpty(simOperator)) {
                    jSONObject2.put("sim_operator_id", simOperator);
                }
                String simOperatorName = telephonyManager.getSimOperatorName();
                if (!TextUtils.isEmpty(simOperatorName)) {
                    jSONObject2.put("sim_operator_name", simOperatorName);
                }
                String networkOperator = telephonyManager.getNetworkOperator();
                if (!TextUtils.isEmpty(networkOperator)) {
                    jSONObject2.put("network_operator_id", networkOperator);
                }
                jSONObject2.put("phone_type", telephonyManager.getPhoneType());
                String simCountryIso = telephonyManager.getSimCountryIso();
                if (!TextUtils.isEmpty(simCountryIso)) {
                    jSONObject2.put("sim_country", simCountryIso);
                }
                jSONObject2.put("network_type", telephonyManager.getNetworkType());
            }
        } catch (Exception unused) {
        }
        Pair<String, String> pair = null;
        try {
            ConnectivityManager connectivityManager = AndroidPlatformModule.getManagerProvider().getConnectivityManager();
            NetworkInfo activeNetworkInfo = connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                jSONObject2.put("connection_type", activeNetworkInfo.getType());
                String typeName = activeNetworkInfo.getTypeName();
                if (!TextUtils.isEmpty(typeName)) {
                    jSONObject2.put("connection_type_name", typeName);
                }
                jSONObject2.put("connection_sub_type", activeNetworkInfo.getSubtype());
                String subtypeName = activeNetworkInfo.getSubtypeName();
                if (!TextUtils.isEmpty(subtypeName)) {
                    jSONObject2.put("connection_sub_type_name", subtypeName);
                }
            }
        } catch (Exception unused2) {
        }
        jSONObject2.put("free_internal_space", a.g());
        jSONObject2.put("total_internal_space", a.h());
        jSONObject2.put("free_external_space", a.i());
        jSONObject2.put("total_external_space", a.j());
        jSONObject2.put("battery_level", (double) a.f());
        Object m = a.m();
        if (m != null) {
            jSONObject2.put("installer", m);
        }
        jSONObject2.put("screen_width", a.k());
        jSONObject2.put("screen_height", a.l());
        if (!TextUtils.isEmpty(Build.MODEL)) {
            jSONObject2.put("device_model", Build.MODEL);
        }
        if (!TextUtils.isEmpty(Build.MANUFACTURER)) {
            jSONObject2.put("manufacturer", Build.MANUFACTURER);
        }
        ?? r2 = -1;
        try {
            r2 = com.pushwoosh.internal.utils.g.b();
        } catch (Exception unused3) {
        }
        jSONObject2.put("notification_enabled", r2 == true ? 1 : 0);
        jSONObject2.put("gms_version", a.q());
        jSONObject2.put("device_rooted", a.s());
        jSONObject2.put("firmware", a.r());
        com.pushwoosh.internal.a.g r = q.d().r();
        try {
            String g = q.d().g().g();
            if (!TextUtils.isEmpty(g)) {
                pair = r.a(jSONObject2.toString(), g);
                if (pair != null) {
                    jSONObject.put("key", pair.first);
                    jSONObject.put("data", pair.second);
                    return;
                }
                return;
            }
            PWLog.error("Public key is empty");
            throw new com.pushwoosh.exception.a("Public key is empty");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "setAttributes";
    }
}
