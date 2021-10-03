package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzcv implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final zzcs zzabq;

    zzcv(zzcs zzcs) {
        this.zzabq = zzcs;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.zzabq.zza(sharedPreferences, str);
    }
}
