package com.pushwoosh.internal.platform.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

public class b implements a {
    private final WeakReference<Context> a;

    public b(@Nullable Context context) {
        this.a = new WeakReference<>(context);
    }

    @Nullable
    private Context j() {
        return this.a.get();
    }

    @Override // com.pushwoosh.internal.platform.a.a
    @Nullable
    public ApplicationInfo a() {
        try {
            if (j() == null) {
                return null;
            }
            return j().getPackageManager().getApplicationInfo(j().getPackageName(), 128);
        } catch (Exception e) {
            PWLog.exception(e);
            return null;
        }
    }

    @Override // com.pushwoosh.internal.platform.a.a
    public String b() {
        return j() == null ? "" : j().getPackageName();
    }

    @Override // com.pushwoosh.internal.platform.a.a
    public String c() {
        return j() == null ? "" : Settings.Secure.getString(j().getContentResolver(), "android_id");
    }

    @Override // com.pushwoosh.internal.platform.a.a
    @Nullable
    public String d() {
        try {
            if (j() == null) {
                return null;
            }
            return j().getPackageManager().getPackageInfo(b(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            PWLog.exception(e);
            return null;
        }
    }

    @Override // com.pushwoosh.internal.platform.a.a
    public String e() {
        if (j() == null) {
            return null;
        }
        return j().getPackageManager().getInstallerPackageName(b());
    }

    @Override // com.pushwoosh.internal.platform.a.a
    public int f() {
        try {
            if (j() == null) {
                return 0;
            }
            return j().getPackageManager().getPackageInfo(b(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            PWLog.exception(e);
            return 0;
        }
    }

    @Override // com.pushwoosh.internal.platform.a.a
    public List<ApplicationInfo> g() {
        return j() == null ? Collections.emptyList() : j().getPackageManager().getInstalledApplications(128);
    }

    @Override // com.pushwoosh.internal.platform.a.a
    public File h() {
        if (j() == null) {
            return null;
        }
        return j().getExternalCacheDir();
    }

    @Override // com.pushwoosh.internal.platform.a.a
    public CharSequence i() {
        if (j() == null) {
            return null;
        }
        return j().getPackageManager().getApplicationLabel(j().getApplicationInfo());
    }
}
