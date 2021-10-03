package com.pushwoosh.internal.utils;

import android.os.Bundle;
import com.pushwoosh.internal.utils.NotificationRegistrarHelper;

/* access modifiers changed from: package-private */
public final /* synthetic */ class f implements NotificationRegistrarHelper.a {
    private final Bundle a;

    private f(Bundle bundle) {
        this.a = bundle;
    }

    public static NotificationRegistrarHelper.a a(Bundle bundle) {
        return new f(bundle);
    }

    @Override // com.pushwoosh.internal.utils.NotificationRegistrarHelper.a
    public void a() {
        NotificationRegistrarHelper.a(this.a);
    }
}
