package com.pushwoosh.notification;

import android.os.Bundle;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class PushMessage {
    private final Bundle a;
    private final String b;
    private final String c;
    private final String d;
    private final boolean e;
    private final boolean f;
    private final Integer g;
    private final Integer h;
    private final String i;
    private final boolean j;
    private final String k;
    private final String l;
    private final String m;
    private final int n;
    private final int o;
    private final int p;
    private final int q;
    private final int r;
    private final int s;
    private final List<Action> t = new ArrayList();
    private final String u;
    private final boolean v;
    private final String w;

    public PushMessage(@NonNull Bundle bundle) {
        this.a = bundle;
        this.d = b.e(bundle);
        this.e = b.a(bundle);
        this.f = b.g(bundle);
        this.g = b.h(bundle);
        this.h = b.i(bundle);
        this.i = b.k(bundle);
        this.j = b.j(bundle);
        this.c = b.l(bundle);
        this.b = b.m(bundle);
        this.k = this.c;
        this.o = b.n(bundle);
        this.q = b.o(bundle);
        this.p = b.p(bundle);
        this.w = b.y(bundle);
        this.m = b.r(bundle);
        this.l = b.s(bundle);
        this.n = b.t(bundle);
        this.r = b.u(bundle);
        this.s = b.v(bundle);
        this.u = b.w(bundle);
        this.v = b.x(bundle);
        this.t.addAll(b.q(bundle));
    }

    public List<Action> getActions() {
        return this.t;
    }

    public int getBadges() {
        return this.p;
    }

    public String getBigPictureUrl() {
        return this.m;
    }

    public String getCustomData() {
        return this.w;
    }

    public String getHeader() {
        return this.b;
    }

    public Integer getIconBackgroundColor() {
        return this.g;
    }

    public String getLargeIconUrl() {
        return this.l;
    }

    public Integer getLed() {
        return this.h;
    }

    public int getLedOffMS() {
        return this.s;
    }

    public int getLedOnMS() {
        return this.r;
    }

    public String getMessage() {
        return this.c;
    }

    public int getPriority() {
        return this.o;
    }

    public String getPushHash() {
        return this.d;
    }

    public int getSmallIcon() {
        return this.n;
    }

    public String getSound() {
        return this.i;
    }

    public String getTag() {
        return this.u;
    }

    public String getTicker() {
        return this.k;
    }

    public boolean getVibration() {
        return this.j;
    }

    public int getVisibility() {
        return this.q;
    }

    public boolean isLocal() {
        return this.f;
    }

    public boolean isLockScreen() {
        return this.v;
    }

    public boolean isSilent() {
        return this.e;
    }

    public Bundle toBundle() {
        return this.a;
    }

    public JSONObject toJson() {
        return b.E(this.a);
    }
}
