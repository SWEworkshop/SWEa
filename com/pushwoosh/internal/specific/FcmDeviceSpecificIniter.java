package com.pushwoosh.internal.specific;

public final class FcmDeviceSpecificIniter {
    private FcmDeviceSpecificIniter() {
    }

    public static DeviceSpecific create() {
        return new a();
    }
}
