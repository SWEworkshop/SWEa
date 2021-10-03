package com.pushwoosh;

import android.content.Context;
import android.content.IntentFilter;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;
import com.pushwoosh.internal.specific.FcmDeviceSpecificIniter;
import com.pushwoosh.internal.utils.LockScreenReceiver;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.q;

public class p {
    private static final String a = "p";

    public static void a(Context context) {
        String str;
        String str2;
        if (a()) {
            str = a;
            str2 = "already init";
        } else {
            DeviceSpecificProvider build = new DeviceSpecificProvider.Builder().setDeviceSpecific(FcmDeviceSpecificIniter.create()).build(false);
            AndroidPlatformModule.init(context);
            q a2 = new q.a().a(new a()).a(build.pushRegistrar()).a();
            a2.n();
            AndroidPlatformModule.getApplicationOpenDetector().a(a2.p().e());
            LockScreenReceiver lockScreenReceiver = new LockScreenReceiver();
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.ANSWER");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            context.registerReceiver(lockScreenReceiver, intentFilter);
            str = a;
            str2 = "Pushwoosh init finished";
        }
        PWLog.noise(str, str2);
    }

    private static boolean a() {
        return (!DeviceSpecificProvider.isInited() || q.d() == null || AndroidPlatformModule.getApplicationContext() == null) ? false : true;
    }
}
