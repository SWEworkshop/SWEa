package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkSpecDao;
import java.util.UUID;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class CancelWorkRunnable implements Runnable {
    private final OperationImpl mOperation = new OperationImpl();

    /* access modifiers changed from: package-private */
    public abstract void runInternal();

    public Operation getOperation() {
        return this.mOperation;
    }

    public void run() {
        try {
            runInternal();
            this.mOperation.setState(Operation.SUCCESS);
        } catch (Throwable th) {
            this.mOperation.setState(new Operation.State.FAILURE(th));
        }
    }

    /* access modifiers changed from: package-private */
    public void cancel(WorkManagerImpl workManagerImpl, String str) {
        recursivelyCancelWorkAndDependents(workManagerImpl.getWorkDatabase(), str);
        workManagerImpl.getProcessor().stopAndCancelWork(str);
        for (Scheduler scheduler : workManagerImpl.getSchedulers()) {
            scheduler.cancel(str);
        }
    }

    /* access modifiers changed from: package-private */
    public void reschedulePendingWorkers(WorkManagerImpl workManagerImpl) {
        Schedulers.schedule(workManagerImpl.getConfiguration(), workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }

    private void recursivelyCancelWorkAndDependents(WorkDatabase workDatabase, String str) {
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        for (String str2 : workDatabase.dependencyDao().getDependentWorkIds(str)) {
            recursivelyCancelWorkAndDependents(workDatabase, str2);
        }
        WorkInfo.State state = workSpecDao.getState(str);
        if (!(state == WorkInfo.State.SUCCEEDED || state == WorkInfo.State.FAILED)) {
            workSpecDao.setState(WorkInfo.State.CANCELLED, str);
        }
    }

    public static CancelWorkRunnable forId(@NonNull final UUID uuid, @NonNull final WorkManagerImpl workManagerImpl) {
        return new CancelWorkRunnable() {
            /* class androidx.work.impl.utils.CancelWorkRunnable.AnonymousClass1 */

            /* access modifiers changed from: package-private */
            @Override // androidx.work.impl.utils.CancelWorkRunnable
            @WorkerThread
            public void runInternal() {
                cancel(workManagerImpl, uuid.toString());
                reschedulePendingWorkers(workManagerImpl);
            }
        };
    }

    public static CancelWorkRunnable forTag(@NonNull final String str, @NonNull final WorkManagerImpl workManagerImpl) {
        return new CancelWorkRunnable() {
            /* class androidx.work.impl.utils.CancelWorkRunnable.AnonymousClass2 */

            /* JADX INFO: finally extract failed */
            /* access modifiers changed from: package-private */
            @Override // androidx.work.impl.utils.CancelWorkRunnable
            @WorkerThread
            public void runInternal() {
                WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
                workDatabase.beginTransaction();
                try {
                    for (String str : workDatabase.workSpecDao().getUnfinishedWorkWithTag(str)) {
                        cancel(workManagerImpl, str);
                    }
                    workDatabase.setTransactionSuccessful();
                    workDatabase.endTransaction();
                    reschedulePendingWorkers(workManagerImpl);
                } catch (Throwable th) {
                    workDatabase.endTransaction();
                    throw th;
                }
            }
        };
    }

    public static CancelWorkRunnable forName(@NonNull final String str, @NonNull final WorkManagerImpl workManagerImpl, final boolean z) {
        return new CancelWorkRunnable() {
            /* class androidx.work.impl.utils.CancelWorkRunnable.AnonymousClass3 */

            /* JADX INFO: finally extract failed */
            /* access modifiers changed from: package-private */
            @Override // androidx.work.impl.utils.CancelWorkRunnable
            @WorkerThread
            public void runInternal() {
                WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
                workDatabase.beginTransaction();
                try {
                    for (String str : workDatabase.workSpecDao().getUnfinishedWorkWithName(str)) {
                        cancel(workManagerImpl, str);
                    }
                    workDatabase.setTransactionSuccessful();
                    workDatabase.endTransaction();
                    if (z) {
                        reschedulePendingWorkers(workManagerImpl);
                    }
                } catch (Throwable th) {
                    workDatabase.endTransaction();
                    throw th;
                }
            }
        };
    }

    public static CancelWorkRunnable forAll(@NonNull final WorkManagerImpl workManagerImpl) {
        return new CancelWorkRunnable() {
            /* class androidx.work.impl.utils.CancelWorkRunnable.AnonymousClass4 */

            /* access modifiers changed from: package-private */
            @Override // androidx.work.impl.utils.CancelWorkRunnable
            @WorkerThread
            public void runInternal() {
                WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
                workDatabase.beginTransaction();
                try {
                    for (String str : workDatabase.workSpecDao().getAllUnfinishedWork()) {
                        cancel(workManagerImpl, str);
                    }
                    workDatabase.setTransactionSuccessful();
                    new Preferences(workManagerImpl.getApplicationContext()).setLastCancelAllTimeMillis(System.currentTimeMillis());
                } finally {
                    workDatabase.endTransaction();
                }
            }
        };
    }
}
