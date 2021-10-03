package com.pushwoosh.internal.network;

import android.os.AsyncTask;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RegistrationPrefs;

public class NetworkModule {
    private static RequestManager a;

    public static void execute(final Runnable runnable) {
        new AsyncTask<Void, Void, Void>() {
            /* class com.pushwoosh.internal.network.NetworkModule.AnonymousClass1 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                try {
                    runnable.run();
                    return null;
                } catch (Exception e) {
                    PWLog.error(e.getMessage());
                    return null;
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @Nullable
    public static RequestManager getRequestManager() {
        return a;
    }

    public static void init(RegistrationPrefs registrationPrefs) {
        if (a == null) {
            a = new e(registrationPrefs);
        }
    }

    public static void setRequestManager(RequestManager requestManager) {
        a = requestManager;
    }
}
