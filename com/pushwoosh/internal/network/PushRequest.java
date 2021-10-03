package com.pushwoosh.internal.network;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.InitHwidEvent;
import com.pushwoosh.internal.event.Subscription;
import com.pushwoosh.internal.platform.utils.a;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;
import com.pushwoosh.repository.RepositoryModule;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PushRequest<S> {
    /* access modifiers changed from: package-private */
    public JSONObject b() throws JSONException, InterruptedException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("application", getApplicationId());
        jSONObject.put("hwid", getHwid());
        jSONObject.put("v", "5.22.6");
        jSONObject.put("device_type", DeviceSpecificProvider.getInstance().deviceType());
        String userId = getUserId();
        if (!TextUtils.isEmpty(userId)) {
            jSONObject.put("userId", userId);
        }
        buildParams(jSONObject);
        return jSONObject;
    }

    /* access modifiers changed from: protected */
    public void buildParams(JSONObject jSONObject) throws JSONException {
    }

    /* access modifiers changed from: protected */
    public String getApplicationId() {
        return RepositoryModule.getRegistrationPreferences().applicationId().get();
    }

    /* access modifiers changed from: protected */
    @NonNull
    public String getHwid() throws InterruptedException {
        String hwid = Pushwoosh.getInstance().getHwid();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Subscription subscribe = EventBus.subscribe(InitHwidEvent.class, d.a(countDownLatch));
        if (hwid.isEmpty()) {
            countDownLatch.await(5, TimeUnit.SECONDS);
        }
        subscribe.unsubscribe();
        String hwid2 = Pushwoosh.getInstance().getHwid();
        return hwid2.isEmpty() ? a.b() : hwid2;
    }

    public abstract String getMethod();

    /* access modifiers changed from: protected */
    public String getUserId() {
        return RepositoryModule.getRegistrationPreferences().userId().get();
    }

    @Nullable
    public S parseResponse(@NonNull JSONObject jSONObject) throws JSONException {
        return null;
    }
}
