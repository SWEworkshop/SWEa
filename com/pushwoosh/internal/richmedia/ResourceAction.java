package com.pushwoosh.internal.richmedia;

import com.pushwoosh.inapp.view.b.a.b;
import com.pushwoosh.q;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.richmedia.a;

public final class ResourceAction {
    private ResourceAction() {
    }

    public static void performRemoteUrlAction(String str) {
        b a = new b.a().b(str).a();
        a i = q.d().i();
        if (i != null) {
            i.a(a);
        }
    }

    public static void performRichMediaAction(String str) {
        b a = new b.a().a(str).a((long) RepositoryModule.getNotificationPreferences().i().get()).a();
        a i = q.d().i();
        if (i != null) {
            i.a(a);
        }
    }
}
