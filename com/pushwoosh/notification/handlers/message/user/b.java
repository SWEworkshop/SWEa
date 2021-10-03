package com.pushwoosh.notification.handlers.message.user;

import android.os.AsyncTask;
import com.pushwoosh.inapp.view.b.a.b;
import com.pushwoosh.internal.utils.LockScreenUtils;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.q;
import com.pushwoosh.repository.RepositoryModule;

/* access modifiers changed from: package-private */
public class b extends NotificationMessageHandler {

    private static class a extends AsyncTask<Void, Void, Void> {
        private final com.pushwoosh.inapp.view.b.a.b a;

        public a(com.pushwoosh.inapp.view.b.a.b bVar) {
            this.a = bVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            com.pushwoosh.richmedia.a i = q.d().i();
            if (i == null) {
                return null;
            }
            i.a(this.a);
            return null;
        }
    }

    b() {
    }

    private void a(String str) {
        com.pushwoosh.inapp.b.c().b(str);
        q.d().g().h();
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.notification.handlers.message.user.NotificationMessageHandler
    public void handleNotification(PushMessage pushMessage) {
        if (pushMessage.isLockScreen()) {
            String B = com.pushwoosh.notification.b.B(pushMessage.toBundle());
            String sound = pushMessage.getSound();
            if (LockScreenUtils.isScreenLocked()) {
                com.pushwoosh.inapp.view.b.a.b a2 = new b.a().a(B).c(sound).a(true).a();
                if (a2 != null) {
                    new a(a2).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    return;
                }
                return;
            }
            RepositoryModule.getLockScreenMediaStorage().a(B, sound);
        }
    }

    @Override // com.pushwoosh.notification.handlers.message.user.MessageHandler, com.pushwoosh.notification.handlers.message.user.NotificationMessageHandler
    public void handlePushMessage(PushMessage pushMessage) {
        String B = com.pushwoosh.notification.b.B(pushMessage.toBundle());
        if (B != null) {
            a(B);
        }
        super.handlePushMessage(pushMessage);
    }
}
