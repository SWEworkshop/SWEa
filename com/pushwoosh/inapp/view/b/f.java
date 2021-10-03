package com.pushwoosh.inapp.view.b;

import android.content.Context;
import androidx.annotation.Nullable;
import com.pushwoosh.inapp.view.b.a.b;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;

public class f {
    @Nullable
    private Context a() {
        return AndroidPlatformModule.getApplicationContext();
    }

    @Nullable
    public e a(b bVar) {
        if (a() == null) {
            PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
            return null;
        }
        switch (bVar.e()) {
            case IN_APP:
                return (bVar.b() == null || !bVar.b().f()) ? new a(a(), com.pushwoosh.inapp.b.a()) : new c(a());
            case RICH_MEDIA:
                return bVar.d() ? new g(a(), bVar.c()) : new h(a(), bVar.a());
            case REMOTE_URL:
                return new d(a());
            default:
                return new a(a(), com.pushwoosh.inapp.b.a());
        }
    }

    public void b(b bVar) {
        try {
            e a = a(bVar);
            if (a != null) {
                a.a(bVar.b());
            }
        } catch (Throwable th) {
            PWLog.error(th.getMessage());
        }
    }
}
