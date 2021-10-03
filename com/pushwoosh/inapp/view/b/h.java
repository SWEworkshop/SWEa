package com.pushwoosh.inapp.view.b;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.pushwoosh.inapp.e.b.b;
import com.pushwoosh.inapp.view.RichMediaWebActivity;
import com.pushwoosh.internal.utils.PWLog;

/* access modifiers changed from: package-private */
public class h implements e {
    private final Context a;
    private final long b;
    private Handler c = new Handler(Looper.getMainLooper());

    h(Context context, long j) {
        this.a = context;
        this.b = j;
    }

    @Override // com.pushwoosh.inapp.view.b.e
    public void a(b bVar) {
        if (bVar == null) {
            PWLog.noise("[InApp]RichMediaViewStrategy", "resource is empty");
            return;
        }
        PWLog.debug("[InApp]RichMediaViewStrategy", "presenting richMedia with code: " + bVar.a() + ", url: " + bVar.b());
        this.c.postDelayed(i.a(this, RichMediaWebActivity.b(this.a, bVar)), this.b);
    }
}
