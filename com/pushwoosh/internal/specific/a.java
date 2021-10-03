package com.pushwoosh.internal.specific;

import com.pushwoosh.internal.platform.utils.GeneralUtils;
import com.pushwoosh.internal.registrar.PushRegistrar;

/* access modifiers changed from: package-private */
public final class a implements DeviceSpecific {
    private final PushRegistrar a = new com.pushwoosh.internal.registrar.a();

    a() {
    }

    @Override // com.pushwoosh.internal.specific.DeviceSpecific
    public int deviceType() {
        return 3;
    }

    @Override // com.pushwoosh.internal.specific.DeviceSpecific
    public String permission(String str) {
        return str + ".permission.C2D_MESSAGE";
    }

    @Override // com.pushwoosh.internal.specific.DeviceSpecific
    public String projectId() {
        return GeneralUtils.getSenderId();
    }

    @Override // com.pushwoosh.internal.specific.DeviceSpecific
    public PushRegistrar pushRegistrar() {
        return this.a;
    }

    @Override // com.pushwoosh.internal.specific.DeviceSpecific
    public String type() {
        return "Android FCM";
    }
}
