package com.pushwoosh.repository;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.inapp.a.k;
import com.pushwoosh.internal.network.PushRequest;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.utils.GeneralUtils;
import com.pushwoosh.m;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class a extends PushRequest<Void> {
    a() {
    }

    @Nullable
    /* renamed from: a */
    public Void parseResponse(@NonNull JSONObject jSONObject) throws JSONException {
        k.a(jSONObject.optJSONObject("required_inapps"));
        return (Void) super.parseResponse(jSONObject);
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        int i;
        String str;
        jSONObject.put("device_name", com.pushwoosh.internal.platform.utils.a.e() ? "Tablet" : "Phone");
        jSONObject.put("language", RepositoryModule.getRegistrationPreferences().language().get());
        jSONObject.put("timezone", TimeUnit.SECONDS.convert((long) TimeZone.getDefault().getOffset(new Date().getTime()), TimeUnit.MILLISECONDS));
        jSONObject.put("android_package", AndroidPlatformModule.getAppInfoProvider().b());
        if (GeneralUtils.isStoreApp()) {
            str = "jailbroken";
            i = 0;
        } else {
            str = "jailbroken";
            i = 1;
        }
        jSONObject.put(str, i);
        jSONObject.put("device_model", com.pushwoosh.internal.platform.utils.a.o());
        jSONObject.put("os_version", Build.VERSION.RELEASE);
        String d = AndroidPlatformModule.getAppInfoProvider().d();
        if (d != null) {
            jSONObject.put("app_version", d);
        }
        m permissionController = AndroidPlatformModule.getInstance().getPermissionController();
        if (permissionController != null) {
            jSONObject.put("notificationTypes", permissionController.a());
        }
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "applicationOpen";
    }
}
