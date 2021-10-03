package androidx.work.impl.constraints.controllers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;

public class StorageNotLowController extends ConstraintController<Boolean> {
    public StorageNotLowController(Context context) {
        super(Trackers.getInstance(context).getStorageNotLowTracker());
    }

    /* access modifiers changed from: package-private */
    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean hasConstraint(@NonNull WorkSpec workSpec) {
        return workSpec.constraints.requiresStorageNotLow();
    }

    /* access modifiers changed from: package-private */
    public boolean isConstrained(@NonNull Boolean bool) {
        return !bool.booleanValue();
    }
}
