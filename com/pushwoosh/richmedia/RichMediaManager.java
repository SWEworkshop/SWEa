package com.pushwoosh.richmedia;

import com.pushwoosh.q;

public class RichMediaManager {
    public static RichMediaStyle getRichMediaStyle() {
        a i = q.d().i();
        if (i != null) {
            return i.a();
        }
        return null;
    }

    public static void present(RichMedia richMedia) {
        a i = q.d().i();
        if (i != null) {
            i.a(richMedia);
        }
    }

    public static void setDelegate(RichMediaPresentingDelegate richMediaPresentingDelegate) {
        a i = q.d().i();
        if (i != null) {
            i.a(richMediaPresentingDelegate);
        }
    }
}
