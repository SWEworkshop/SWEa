package com.pushwoosh.internal.specific;

import com.pushwoosh.internal.registrar.PushRegistrar;

public class DeviceSpecificProvider {
    private static DeviceSpecificProvider a;
    private final DeviceSpecific b;

    public static class Builder {
        private DeviceSpecific a;

        public DeviceSpecificProvider build(boolean z) {
            if (this.a != null) {
                if (DeviceSpecificProvider.a == null || z) {
                    DeviceSpecificProvider unused = DeviceSpecificProvider.a = new DeviceSpecificProvider(this.a);
                }
                return DeviceSpecificProvider.a;
            }
            throw new IllegalArgumentException("You must setup deviceSpecific");
        }

        public Builder setDeviceSpecific(DeviceSpecific deviceSpecific) {
            this.a = deviceSpecific;
            return this;
        }
    }

    private DeviceSpecificProvider(DeviceSpecific deviceSpecific) {
        this.b = deviceSpecific;
    }

    public static DeviceSpecificProvider getInstance() {
        return a;
    }

    public static boolean isInited() {
        return a != null;
    }

    public int deviceType() {
        return this.b.deviceType();
    }

    public boolean isFirebase() {
        return type().equals("Android FCM");
    }

    public String permission(String str) {
        return this.b.permission(str);
    }

    public String projectId() {
        return this.b.projectId();
    }

    public PushRegistrar pushRegistrar() {
        return this.b.pushRegistrar();
    }

    public String type() {
        return this.b.type();
    }
}
