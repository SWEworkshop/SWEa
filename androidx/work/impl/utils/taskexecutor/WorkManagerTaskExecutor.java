package androidx.work.impl.utils.taskexecutor;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WorkManagerTaskExecutor implements TaskExecutor {
    private final ExecutorService mBackgroundExecutor = Executors.newSingleThreadExecutor(this.mBackgroundThreadFactory);
    private final ThreadFactory mBackgroundThreadFactory = new ThreadFactory() {
        /* class androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor.AnonymousClass2 */
        private int mThreadsCreated = 0;

        public Thread newThread(@NonNull Runnable runnable) {
            Thread newThread = Executors.defaultThreadFactory().newThread(runnable);
            newThread.setName("WorkManager-WorkManagerTaskExecutor-thread-" + this.mThreadsCreated);
            this.mThreadsCreated = this.mThreadsCreated + 1;
            WorkManagerTaskExecutor.this.mCurrentBackgroundExecutorThread = newThread;
            return newThread;
        }
    };
    volatile Thread mCurrentBackgroundExecutorThread;
    private final Executor mMainThreadExecutor = new Executor() {
        /* class androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor.AnonymousClass1 */

        public void execute(@NonNull Runnable runnable) {
            WorkManagerTaskExecutor.this.postToMainThread(runnable);
        }
    };
    private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());

    @Override // androidx.work.impl.utils.taskexecutor.TaskExecutor
    public void postToMainThread(Runnable runnable) {
        this.mMainThreadHandler.post(runnable);
    }

    @Override // androidx.work.impl.utils.taskexecutor.TaskExecutor
    public Executor getMainThreadExecutor() {
        return this.mMainThreadExecutor;
    }

    @Override // androidx.work.impl.utils.taskexecutor.TaskExecutor
    public void executeOnBackgroundThread(Runnable runnable) {
        this.mBackgroundExecutor.execute(runnable);
    }

    @Override // androidx.work.impl.utils.taskexecutor.TaskExecutor
    public Executor getBackgroundExecutor() {
        return this.mBackgroundExecutor;
    }

    @Override // androidx.work.impl.utils.taskexecutor.TaskExecutor
    @NonNull
    public Thread getBackgroundExecutorThread() {
        return this.mCurrentBackgroundExecutorThread;
    }
}
