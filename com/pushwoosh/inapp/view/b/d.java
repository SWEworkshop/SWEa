package com.pushwoosh.inapp.view.b;

import android.content.Context;
import com.pushwoosh.inapp.e.b.b;
import com.pushwoosh.inapp.view.RemoteUrlActivity;
import com.pushwoosh.internal.platform.utils.GeneralUtils;
import com.pushwoosh.internal.utils.PWLog;

class d implements e {
    private final Context a;

    d(Context context) {
        this.a = context;
    }

    @Override // com.pushwoosh.inapp.view.b.e
    public void a(b bVar) {
        if (bVar == null) {
            PWLog.noise("[InApp]RemoteUrlDefaultViewStrategy", "resource is empty");
        } else if (!GeneralUtils.isNetworkAvailable()) {
            PWLog.error("[InApp]RemoteUrlDefaultViewStrategy", "Remote page error: network unavailable");
        } else {
            RemoteUrlActivity.a(this.a, bVar.b());
        }
    }
}
