package com.pushwoosh.amazon.a.b;

import com.pushwoosh.amazon.a.a;
import com.pushwoosh.internal.specific.DeviceSpecific;
import com.pushwoosh.internal.specific.FcmDeviceSpecificIniter;

public final class b {
    public static DeviceSpecific a() {
        return a.a() ? new a() : FcmDeviceSpecificIniter.create();
    }
}
