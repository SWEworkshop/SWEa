package com.pushwoosh.notification;

public enum VibrateType {
    DEFAULT_MODE(0),
    NO_VIBRATE(1),
    ALWAYS(2);
    
    private final int a;

    private VibrateType(int i) {
        this.a = i;
    }

    public static VibrateType fromInt(int i) {
        switch (i) {
            case 0:
                return DEFAULT_MODE;
            case 1:
                return NO_VIBRATE;
            case 2:
                return ALWAYS;
            default:
                return DEFAULT_MODE;
        }
    }

    public int getValue() {
        return this.a;
    }
}
