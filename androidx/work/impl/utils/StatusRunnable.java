package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.work.WorkInfo;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.UUID;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class StatusRunnable<T> implements Runnable {
    private final SettableFuture<T> mFuture = SettableFuture.create();

    /* access modifiers changed from: package-private */
    @WorkerThread
    public abstract T runInternal();

    public void run() {
        try {
            this.mFuture.set(runInternal());
        } catch (Throwable th) {
            this.mFuture.setException(th);
        }
    }

    public ListenableFuture<T> getFuture() {
        return this.mFuture;
    }

    public static StatusRunnable<List<WorkInfo>> forStringIds(@NonNull final WorkManagerImpl workManagerImpl, @NonNull final List<String> list) {
        return new StatusRunnable<List<WorkInfo>>() {
            /* class androidx.work.impl.utils.StatusRunnable.AnonymousClass1 */

            @Override // androidx.work.impl.utils.StatusRunnable
            public List<WorkInfo> runInternal() {
                return WorkSpec.WORK_INFO_MAPPER.apply(workManagerImpl.getWorkDatabase().workSpecDao().getWorkStatusPojoForIds(list));
            }
        };
    }

    public static StatusRunnable<WorkInfo> forUUID(@NonNull final WorkManagerImpl workManagerImpl, @NonNull final UUID uuid) {
        return new StatusRunnable<WorkInfo>() {
            /* class androidx.work.impl.utils.StatusRunnable.AnonymousClass2 */

            /* access modifiers changed from: package-private */
            @Override // androidx.work.impl.utils.StatusRunnable
            public WorkInfo runInternal() {
                WorkSpec.WorkInfoPojo workStatusPojoForId = workManagerImpl.getWorkDatabase().workSpecDao().getWorkStatusPojoForId(uuid.toString());
                if (workStatusPojoForId != null) {
                    return workStatusPojoForId.toWorkInfo();
                }
                return null;
            }
        };
    }

    public static StatusRunnable<List<WorkInfo>> forTag(@NonNull final WorkManagerImpl workManagerImpl, @NonNull final String str) {
        return new StatusRunnable<List<WorkInfo>>() {
            /* class androidx.work.impl.utils.StatusRunnable.AnonymousClass3 */

            /* access modifiers changed from: package-private */
            @Override // androidx.work.impl.utils.StatusRunnable
            public List<WorkInfo> runInternal() {
                return WorkSpec.WORK_INFO_MAPPER.apply(workManagerImpl.getWorkDatabase().workSpecDao().getWorkStatusPojoForTag(str));
            }
        };
    }

    public static StatusRunnable<List<WorkInfo>> forUniqueWork(@NonNull final WorkManagerImpl workManagerImpl, @NonNull final String str) {
        return new StatusRunnable<List<WorkInfo>>() {
            /* class androidx.work.impl.utils.StatusRunnable.AnonymousClass4 */

            /* access modifiers changed from: package-private */
            @Override // androidx.work.impl.utils.StatusRunnable
            public List<WorkInfo> runInternal() {
                return WorkSpec.WORK_INFO_MAPPER.apply(workManagerImpl.getWorkDatabase().workSpecDao().getWorkStatusPojoForName(str));
            }
        };
    }
}
