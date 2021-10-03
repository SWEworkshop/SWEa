package com.pushwoosh.notification;

public enum SoundType {
    DEFAULT_MODE(0),
    NO_SOUND(1),
    ALWAYS(2);
    
    private final int a;

    private SoundType(int i) {
        this.a = i;
    }

    public static SoundType fromInt(int i) {
        switch (i) {
            case 0:
                return DEFAULT_MODE;
            case 1:
                return NO_SOUND;
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
