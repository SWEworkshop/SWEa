package androidx.work;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class WorkInfo {
    @NonNull
    private UUID mId;
    @NonNull
    private Data mOutputData;
    @NonNull
    private State mState;
    @NonNull
    private Set<String> mTags;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WorkInfo(@NonNull UUID uuid, @NonNull State state, @NonNull Data data, @NonNull List<String> list) {
        this.mId = uuid;
        this.mState = state;
        this.mOutputData = data;
        this.mTags = new HashSet(list);
    }

    @NonNull
    public UUID getId() {
        return this.mId;
    }

    @NonNull
    public State getState() {
        return this.mState;
    }

    @NonNull
    public Data getOutputData() {
        return this.mOutputData;
    }

    @NonNull
    public Set<String> getTags() {
        return this.mTags;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WorkInfo workInfo = (WorkInfo) obj;
        UUID uuid = this.mId;
        if (uuid == null ? workInfo.mId != null : !uuid.equals(workInfo.mId)) {
            return false;
        }
        if (this.mState != workInfo.mState) {
            return false;
        }
        Data data = this.mOutputData;
        if (data == null ? workInfo.mOutputData != null : !data.equals(workInfo.mOutputData)) {
            return false;
        }
        Set<String> set = this.mTags;
        if (set != null) {
            return set.equals(workInfo.mTags);
        }
        if (workInfo.mTags == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        UUID uuid = this.mId;
        int i = 0;
        int hashCode = (uuid != null ? uuid.hashCode() : 0) * 31;
        State state = this.mState;
        int hashCode2 = (hashCode + (state != null ? state.hashCode() : 0)) * 31;
        Data data = this.mOutputData;
        int hashCode3 = (hashCode2 + (data != null ? data.hashCode() : 0)) * 31;
        Set<String> set = this.mTags;
        if (set != null) {
            i = set.hashCode();
        }
        return hashCode3 + i;
    }

    public String toString() {
        return "WorkInfo{mId='" + this.mId + '\'' + ", mState=" + this.mState + ", mOutputData=" + this.mOutputData + ", mTags=" + this.mTags + '}';
    }

    public enum State {
        ENQUEUED,
        RUNNING,
        SUCCEEDED,
        FAILED,
        BLOCKED,
        CANCELLED;

        public boolean isFinished() {
            return this == SUCCEEDED || this == FAILED || this == CANCELLED;
        }
    }
}
