package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

final class zzfz implements Callable<byte[]> {
    private final /* synthetic */ zzai zzdm;
    private final /* synthetic */ String zzdn;
    private final /* synthetic */ zzfk zzph;

    zzfz(zzfk zzfk, zzai zzai, String str) {
        this.zzph = zzfk;
        this.zzdm = zzai;
        this.zzdn = str;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final /* synthetic */ byte[] call() throws Exception {
        zzfk.zza(this.zzph).zzjq();
        return zzfk.zza(this.zzph).zzji().zzb(this.zzdm, this.zzdn);
    }
}