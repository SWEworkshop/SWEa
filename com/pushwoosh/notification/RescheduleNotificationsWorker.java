package com.pushwoosh.notification;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.j;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.b;

public class RescheduleNotificationsWorker extends Worker {
    public RescheduleNotificationsWorker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    private static long a() {
        j timeProvider = AndroidPlatformModule.getInstance().getTimeProvider();
        return timeProvider != null ? timeProvider.b() : System.currentTimeMillis();
    }

    static /* synthetic */ void a(long j, b bVar) {
        Bundle b = bVar.b();
        PWLog.debug("RescheduleNotificationsWorker", "Rescheduling local push: " + b.toString());
        LocalNotificationReceiver.rescheduleNotification(bVar, j);
    }

    @Override // androidx.work.Worker
    @NonNull
    public ListenableWorker.Result doWork() {
        RepositoryModule.getLocalNotificationStorage().a(i.a(a()));
        return ListenableWorker.Result.success();
    }
}
