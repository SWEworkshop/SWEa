package com.pushwoosh.internal.utils;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.work.BackoffPolicy;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import com.pushwoosh.HandleMessageWorker;
import com.pushwoosh.PushwooshWorkManagerHelper;
import com.pushwoosh.q;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.r;
import java.util.concurrent.TimeUnit;

public final class NotificationRegistrarHelper {

    /* access modifiers changed from: private */
    public interface a {
        void a();
    }

    /* access modifiers changed from: private */
    public static class b extends AsyncTask<Void, Void, Boolean> {
        final Bundle a;
        final a b;

        public b(Bundle bundle, a aVar) {
            this.a = bundle;
            this.b = aVar;
        }

        private void a(long j) {
            PushwooshWorkManagerHelper.enqueueOneTimeUniqueWork((OneTimeWorkRequest) ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(HandleMessageWorker.class).setInputData(new Data.Builder().putLong("data_push_bundle_id", j).build())).setConstraints(PushwooshWorkManagerHelper.getNetworkAvailableConstraints())).setBackoffCriteria(BackoffPolicy.LINEAR, 5, TimeUnit.SECONDS)).build(), "HandleMessageWorker", ExistingWorkPolicy.APPEND);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Boolean doInBackground(Void... voidArr) {
            boolean z;
            r pushBundleStorage = RepositoryModule.getPushBundleStorage();
            if (pushBundleStorage != null) {
                try {
                    a(pushBundleStorage.a(this.a));
                } catch (Exception unused) {
                    z = false;
                }
            }
            z = true;
            return Boolean.valueOf(z);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Boolean bool) {
            a aVar;
            super.onPostExecute(bool);
            if (!bool.booleanValue() && (aVar = this.b) != null) {
                aVar.a();
            }
        }
    }

    private NotificationRegistrarHelper() {
    }

    static /* synthetic */ void a(Bundle bundle) {
        try {
            q.d().l().handleMessage(bundle);
        } catch (Exception e) {
            PWLog.exception(e);
        }
    }

    public static void clearToken() {
        RepositoryModule.getRegistrationPreferences().pushToken().set("");
    }

    public static void handleMessage(Bundle bundle) {
        new b(bundle, f.a(bundle)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static boolean isRegisteredForRemoteNotifications() {
        return RepositoryModule.getRegistrationPreferences().isRegisteredForPush().get();
    }

    public static void onFailedToRegisterForRemoteNotifications(String str) {
        q.d().f().d(str);
    }

    public static void onFailedToUnregisterFromRemoteNotifications(String str) {
        q.d().f().f(str);
    }

    public static void onRegisteredForRemoteNotifications(String str) {
        if (isRegisteredForRemoteNotifications()) {
            q.d().f().c(str);
        }
    }

    public static void onUnregisteredFromRemoteNotifications(String str) {
        q.d().f().e(str);
    }
}
