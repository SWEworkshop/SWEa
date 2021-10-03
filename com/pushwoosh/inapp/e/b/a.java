package com.pushwoosh.inapp.e.b;

public enum a {
    FULLSCREEN("fullscreen"),
    DIALOG("centerbox"),
    TOP("topbanner"),
    BOTTOM("bottombanner");
    
    private String e;

    private a(String str) {
        this.e = str;
    }

    public static a a(String str) {
        a[] values = values();
        for (a aVar : values) {
            if (aVar.e.equals(str)) {
                return aVar;
            }
        }
        return DIALOG;
    }

    public String a() {
        return this.e;
    }
}
