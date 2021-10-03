package androidx.work.impl.constraints.controllers;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.work.Logger;
import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;

public class NetworkNotRoamingController extends ConstraintController<NetworkState> {
    private static final String TAG = Logger.tagWithPrefix("NetworkNotRoamingCtrlr");

    public NetworkNotRoamingController(Context context) {
        super(Trackers.getInstance(context).getNetworkStateTracker());
    }

    /* access modifiers changed from: package-private */
    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean hasConstraint(@NonNull WorkSpec workSpec) {
        return workSpec.constraints.getRequiredNetworkType() == NetworkType.NOT_ROAMING;
    }

    /* access modifiers changed from: package-private */
    public boolean isConstrained(@NonNull NetworkState networkState) {
        if (Build.VERSION.SDK_INT < 24) {
            Logger.get().debug(TAG, "Not-roaming network constraint is not supported before API 24, only checking for connected state.", new Throwable[0]);
            return !networkState.isConnected();
        } else if (!networkState.isConnected() || !networkState.isNotRoaming()) {
            return true;
        } else {
            return false;
        }
    }
}
