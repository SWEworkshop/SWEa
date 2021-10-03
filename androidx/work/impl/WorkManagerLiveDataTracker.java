package androidx.work.impl;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

/* access modifiers changed from: package-private */
public class WorkManagerLiveDataTracker {
    @VisibleForTesting
    final Set<LiveData> mLiveDataSet = Collections.newSetFromMap(new IdentityHashMap());

    WorkManagerLiveDataTracker() {
    }

    public <T> LiveData<T> track(LiveData<T> liveData) {
        return new TrackedLiveData(this, liveData);
    }

    /* access modifiers changed from: package-private */
    public void onActive(LiveData liveData) {
        this.mLiveDataSet.add(liveData);
    }

    /* access modifiers changed from: package-private */
    public void onInactive(LiveData liveData) {
        this.mLiveDataSet.remove(liveData);
    }

    /* access modifiers changed from: package-private */
    public static class TrackedLiveData<T> extends MediatorLiveData<T> {
        private final WorkManagerLiveDataTracker mContainer;

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: androidx.lifecycle.LiveData<T> */
        /* JADX WARN: Multi-variable type inference failed */
        TrackedLiveData(WorkManagerLiveDataTracker workManagerLiveDataTracker, LiveData<T> liveData) {
            this.mContainer = workManagerLiveDataTracker;
            addSource(liveData, new Observer<T>() {
                /* class androidx.work.impl.WorkManagerLiveDataTracker.TrackedLiveData.AnonymousClass1 */

                @Override // androidx.lifecycle.Observer
                public void onChanged(@Nullable T t) {
                    TrackedLiveData.this.setValue(t);
                }
            });
        }

        /* access modifiers changed from: protected */
        @Override // androidx.lifecycle.LiveData, androidx.lifecycle.MediatorLiveData
        public void onActive() {
            super.onActive();
            this.mContainer.onActive(this);
        }

        /* access modifiers changed from: protected */
        @Override // androidx.lifecycle.LiveData, androidx.lifecycle.MediatorLiveData
        public void onInactive() {
            super.onInactive();
            this.mContainer.onInactive(this);
        }
    }
}
