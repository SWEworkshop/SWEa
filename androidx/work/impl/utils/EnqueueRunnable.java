package androidx.work.impl.utils;

import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.WorkRequest;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.model.Dependency;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTag;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class EnqueueRunnable implements Runnable {
    private static final String TAG = Logger.tagWithPrefix("EnqueueRunnable");
    private final OperationImpl mOperation = new OperationImpl();
    private final WorkContinuationImpl mWorkContinuation;

    public EnqueueRunnable(@NonNull WorkContinuationImpl workContinuationImpl) {
        this.mWorkContinuation = workContinuationImpl;
    }

    public void run() {
        try {
            if (!this.mWorkContinuation.hasCycles()) {
                if (addToDatabase()) {
                    PackageManagerHelper.setComponentEnabled(this.mWorkContinuation.getWorkManagerImpl().getApplicationContext(), RescheduleReceiver.class, true);
                    scheduleWorkInBackground();
                }
                this.mOperation.setState(Operation.SUCCESS);
                return;
            }
            throw new IllegalStateException(String.format("WorkContinuation has cycles (%s)", this.mWorkContinuation));
        } catch (Throwable th) {
            this.mOperation.setState(new Operation.State.FAILURE(th));
        }
    }

    public Operation getOperation() {
        return this.mOperation;
    }

    @VisibleForTesting
    public boolean addToDatabase() {
        WorkDatabase workDatabase = this.mWorkContinuation.getWorkManagerImpl().getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            boolean processContinuation = processContinuation(this.mWorkContinuation);
            workDatabase.setTransactionSuccessful();
            return processContinuation;
        } finally {
            workDatabase.endTransaction();
        }
    }

    @VisibleForTesting
    public void scheduleWorkInBackground() {
        WorkManagerImpl workManagerImpl = this.mWorkContinuation.getWorkManagerImpl();
        Schedulers.schedule(workManagerImpl.getConfiguration(), workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }

    private static boolean processContinuation(@NonNull WorkContinuationImpl workContinuationImpl) {
        List<WorkContinuationImpl> parents = workContinuationImpl.getParents();
        boolean z = false;
        if (parents != null) {
            boolean z2 = false;
            for (WorkContinuationImpl workContinuationImpl2 : parents) {
                if (!workContinuationImpl2.isEnqueued()) {
                    z2 |= processContinuation(workContinuationImpl2);
                } else {
                    Logger.get().warning(TAG, String.format("Already enqueued work ids (%s).", TextUtils.join(", ", workContinuationImpl2.getIds())), new Throwable[0]);
                }
            }
            z = z2;
        }
        return enqueueContinuation(workContinuationImpl) | z;
    }

    private static boolean enqueueContinuation(@NonNull WorkContinuationImpl workContinuationImpl) {
        boolean enqueueWorkWithPrerequisites = enqueueWorkWithPrerequisites(workContinuationImpl.getWorkManagerImpl(), workContinuationImpl.getWork(), (String[]) WorkContinuationImpl.prerequisitesFor(workContinuationImpl).toArray(new String[0]), workContinuationImpl.getName(), workContinuationImpl.getExistingWorkPolicy());
        workContinuationImpl.markEnqueued();
        return enqueueWorkWithPrerequisites;
    }

    /* JADX WARNING: Removed duplicated region for block: B:62:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0122  */
    private static boolean enqueueWorkWithPrerequisites(WorkManagerImpl workManagerImpl, @NonNull List<? extends WorkRequest> list, String[] strArr, String str, ExistingWorkPolicy existingWorkPolicy) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        Iterator<? extends WorkRequest> it;
        Iterator<? extends WorkRequest> it2;
        String[] strArr2;
        WorkManagerImpl workManagerImpl2;
        String[] strArr3 = strArr;
        long currentTimeMillis = System.currentTimeMillis();
        WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
        boolean z5 = strArr3 != null && strArr3.length > 0;
        if (z5) {
            z3 = true;
            z2 = false;
            z = false;
            for (String str2 : strArr3) {
                WorkSpec workSpec = workDatabase.workSpecDao().getWorkSpec(str2);
                if (workSpec == null) {
                    Logger.get().error(TAG, String.format("Prerequisite %s doesn't exist; not enqueuing", str2), new Throwable[0]);
                    return false;
                }
                WorkInfo.State state = workSpec.state;
                z3 &= state == WorkInfo.State.SUCCEEDED;
                if (state == WorkInfo.State.FAILED) {
                    z2 = true;
                } else if (state == WorkInfo.State.CANCELLED) {
                    z = true;
                }
            }
        } else {
            z3 = true;
            z2 = false;
            z = false;
        }
        boolean z6 = !TextUtils.isEmpty(str);
        if (z6 && !z5) {
            List<WorkSpec.IdAndState> workSpecIdAndStatesForName = workDatabase.workSpecDao().getWorkSpecIdAndStatesForName(str);
            if (!workSpecIdAndStatesForName.isEmpty()) {
                if (existingWorkPolicy == ExistingWorkPolicy.APPEND) {
                    DependencyDao dependencyDao = workDatabase.dependencyDao();
                    ArrayList arrayList = new ArrayList();
                    for (WorkSpec.IdAndState idAndState : workSpecIdAndStatesForName) {
                        if (!dependencyDao.hasDependents(idAndState.id)) {
                            boolean z7 = (idAndState.state == WorkInfo.State.SUCCEEDED) & z3;
                            if (idAndState.state == WorkInfo.State.FAILED) {
                                z2 = true;
                            } else if (idAndState.state == WorkInfo.State.CANCELLED) {
                                z = true;
                            }
                            arrayList.add(idAndState.id);
                            z3 = z7;
                        }
                    }
                    strArr3 = (String[]) arrayList.toArray(strArr3);
                    z5 = strArr3.length > 0;
                    z4 = false;
                } else {
                    if (existingWorkPolicy == ExistingWorkPolicy.KEEP) {
                        for (WorkSpec.IdAndState idAndState2 : workSpecIdAndStatesForName) {
                            if (idAndState2.state == WorkInfo.State.ENQUEUED || idAndState2.state == WorkInfo.State.RUNNING) {
                                return false;
                            }
                            while (r2.hasNext()) {
                            }
                        }
                        workManagerImpl2 = workManagerImpl;
                    } else {
                        workManagerImpl2 = workManagerImpl;
                    }
                    CancelWorkRunnable.forName(str, workManagerImpl2, false).run();
                    WorkSpecDao workSpecDao = workDatabase.workSpecDao();
                    for (WorkSpec.IdAndState idAndState3 : workSpecIdAndStatesForName) {
                        workSpecDao.delete(idAndState3.id);
                    }
                    z4 = true;
                }
                it = list.iterator();
                while (it.hasNext()) {
                    WorkRequest workRequest = (WorkRequest) it.next();
                    WorkSpec workSpec2 = workRequest.getWorkSpec();
                    if (!z5 || z3) {
                        if (!workSpec2.isPeriodic()) {
                            workSpec2.periodStartTime = currentTimeMillis;
                            it2 = it;
                        } else {
                            it2 = it;
                            workSpec2.periodStartTime = 0;
                        }
                    } else if (z2) {
                        workSpec2.state = WorkInfo.State.FAILED;
                        it2 = it;
                    } else if (z) {
                        workSpec2.state = WorkInfo.State.CANCELLED;
                        it2 = it;
                    } else {
                        workSpec2.state = WorkInfo.State.BLOCKED;
                        it2 = it;
                    }
                    if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT <= 25) {
                        tryDelegateConstrainedWorkSpec(workSpec2);
                    }
                    if (workSpec2.state == WorkInfo.State.ENQUEUED) {
                        z4 = true;
                    }
                    workDatabase.workSpecDao().insertWorkSpec(workSpec2);
                    if (z5) {
                        int length = strArr3.length;
                        int i = 0;
                        while (i < length) {
                            workDatabase.dependencyDao().insertDependency(new Dependency(workRequest.getStringId(), strArr3[i]));
                            i++;
                            strArr3 = strArr3;
                        }
                        strArr2 = strArr3;
                    } else {
                        strArr2 = strArr3;
                    }
                    for (String str3 : workRequest.getTags()) {
                        workDatabase.workTagDao().insert(new WorkTag(str3, workRequest.getStringId()));
                    }
                    if (z6) {
                        workDatabase.workNameDao().insert(new WorkName(str, workRequest.getStringId()));
                    }
                    it = it2;
                    strArr3 = strArr2;
                }
                return z4;
            }
        }
        z4 = false;
        it = list.iterator();
        while (it.hasNext()) {
        }
        return z4;
    }

    private static void tryDelegateConstrainedWorkSpec(WorkSpec workSpec) {
        Constraints constraints = workSpec.constraints;
        if (constraints.requiresBatteryNotLow() || constraints.requiresStorageNotLow()) {
            String str = workSpec.workerClassName;
            Data.Builder builder = new Data.Builder();
            builder.putAll(workSpec.input).putString(ConstraintTrackingWorker.ARGUMENT_CLASS_NAME, str);
            workSpec.workerClassName = ConstraintTrackingWorker.class.getName();
            workSpec.input = builder.build();
        }
    }
}
