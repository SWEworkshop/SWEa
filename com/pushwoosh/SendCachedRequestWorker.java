package com.pushwoosh;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.network.a;
import com.pushwoosh.internal.network.b;
import com.pushwoosh.internal.network.f;
import com.pushwoosh.repository.RepositoryModule;

public class SendCachedRequestWorker extends Worker {
    public SendCachedRequestWorker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    private ListenableWorker.Result a() {
        return getRunAttemptCount() >= 3 ? ListenableWorker.Result.success() : ListenableWorker.Result.retry();
    }

    @Override // androidx.work.Worker
    @NonNull
    public ListenableWorker.Result doWork() {
        long j = getInputData().getLong("data_cached_request_id", -1);
        if (j == -1) {
            return a();
        }
        f requestStorage = RepositoryModule.getRequestStorage();
        a a = requestStorage.a(j);
        if (a == null) {
            return a();
        }
        RequestManager requestManager = NetworkModule.getRequestManager();
        if (requestManager == null) {
            return a();
        }
        Result sendRequestSync = requestManager.sendRequestSync(a);
        if (!sendRequestSync.isSuccess() && (sendRequestSync.getException() instanceof b)) {
            return a();
        }
        requestStorage.a(a.a());
        return ListenableWorker.Result.success();
    }
}
