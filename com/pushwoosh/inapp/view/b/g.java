package com.pushwoosh.inapp.view.b;

import android.content.Context;
import com.pushwoosh.inapp.e.b.b;
import com.pushwoosh.inapp.view.RichMediaWebActivity;
import com.pushwoosh.internal.utils.PWLog;

class g implements e {
    private final Context a;
    private final String b;

    g(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    @Override // com.pushwoosh.inapp.view.b.e
    public void a(b bVar) {
        if (bVar == null) {
            PWLog.noise("[InApp]RichMediaLockScreenViewStrategy", "resource is empty");
            return;
        }
        PWLog.debug("[InApp]RichMediaLockScreenViewStrategy", "presenting richMedia with code: " + bVar.a() + ", url: " + bVar.b());
        Context context = this.a;
        context.startActivity(RichMediaWebActivity.a(context, bVar, this.b));
    }
}
