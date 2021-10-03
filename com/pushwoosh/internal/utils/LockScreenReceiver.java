package com.pushwoosh.internal.utils;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.pushwoosh.inapp.view.b.a.b;
import com.pushwoosh.q;
import com.pushwoosh.repository.RepositoryModule;
import java.util.List;

public class LockScreenReceiver extends BroadcastReceiver {

    private static class a extends AsyncTask<Void, Void, Void> {
        private a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            List<b> a = RepositoryModule.getLockScreenMediaStorage().a();
            if (a != null && !a.isEmpty()) {
                com.pushwoosh.richmedia.a i = q.d().i();
                if (i == null) {
                    PWLog.error("LockScreenReceiver", "RichMediaController is null");
                    return null;
                }
                for (b bVar : a) {
                    i.a(bVar);
                }
                RepositoryModule.getLockScreenMediaStorage().b();
            }
            return null;
        }
    }

    private void a(Context context) {
        List<Uri> c = RepositoryModule.getLockScreenMediaStorage().c();
        if (!(c == null || c.isEmpty())) {
            for (Uri uri : c) {
                a(uri, context);
            }
            RepositoryModule.getLockScreenMediaStorage().d();
        }
    }

    private void a(Uri uri, Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.addFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            PWLog.error("Can't open remote url: " + uri, e);
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (TextUtils.equals(intent.getAction(), "android.intent.action.SCREEN_ON")) {
                new a().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            } else if (TextUtils.equals(intent.getAction(), "android.intent.action.USER_PRESENT")) {
                a(context);
            }
        }
    }
}
