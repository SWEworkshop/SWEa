package com.pushwoosh.internal.platform;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.platform.a.a;
import com.pushwoosh.internal.platform.manager.ManagerProvider;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.platform.prefs.d;
import com.pushwoosh.internal.platform.prefs.migration.b;
import com.pushwoosh.internal.utils.j;
import com.pushwoosh.m;
import java.lang.ref.WeakReference;

public final class AndroidPlatformModule {
    private static final AndroidPlatformModule ANDROID_PLATFORM_MODULE = new AndroidPlatformModule();
    public static final String NULL_CONTEXT_MESSAGE = "Incorrect state of app. Context is null";
    private a appInfoProvider;
    private a applicationOpenDetector;
    private WeakReference<Context> context;
    private ManagerProvider managerProvider;
    private m permissionController;
    private b prefsMigration;
    private PrefsProvider prefsProvider;
    private com.pushwoosh.internal.platform.b.b receiverProvider;
    private com.pushwoosh.internal.platform.c.b resourceProvider;
    private j timeProvider;

    private AndroidPlatformModule() {
    }

    public static a getAppInfoProvider() {
        return ANDROID_PLATFORM_MODULE.appInfoProvider;
    }

    @Nullable
    public static Context getApplicationContext() {
        WeakReference<Context> weakReference = ANDROID_PLATFORM_MODULE.context;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public static a getApplicationOpenDetector() {
        return ANDROID_PLATFORM_MODULE.applicationOpenDetector;
    }

    public static AndroidPlatformModule getInstance() {
        return ANDROID_PLATFORM_MODULE;
    }

    public static ManagerProvider getManagerProvider() {
        return ANDROID_PLATFORM_MODULE.managerProvider;
    }

    public static b getPrefsMigration() {
        return ANDROID_PLATFORM_MODULE.prefsMigration;
    }

    public static PrefsProvider getPrefsProvider() {
        return ANDROID_PLATFORM_MODULE.prefsProvider;
    }

    public static com.pushwoosh.internal.platform.b.b getReceiverProvider() {
        return ANDROID_PLATFORM_MODULE.receiverProvider;
    }

    public static com.pushwoosh.internal.platform.c.b getResourceProvider() {
        return ANDROID_PLATFORM_MODULE.resourceProvider;
    }

    public static j getTimeProvide() {
        return ANDROID_PLATFORM_MODULE.timeProvider;
    }

    public static void init(Context context2) {
        init(context2, false);
    }

    public static void init(Context context2, boolean z) {
        if (context2 != null) {
            if (!isInit() || z) {
                ANDROID_PLATFORM_MODULE.initProviders(context2);
            }
        }
    }

    private void initProviders(@NonNull Context context2) {
        this.timeProvider = new j();
        this.context = new WeakReference<>(context2.getApplicationContext());
        this.prefsProvider = d.b();
        this.prefsMigration = d.a();
        this.managerProvider = new com.pushwoosh.internal.platform.manager.a(context2);
        this.appInfoProvider = new com.pushwoosh.internal.platform.a.b(context2);
        this.resourceProvider = new com.pushwoosh.internal.platform.c.a(context2);
        this.receiverProvider = new com.pushwoosh.internal.platform.b.a(context2);
        this.applicationOpenDetector = new a(context2);
        this.permissionController = new m(context2, this.managerProvider.getNotificationManager());
    }

    private static boolean isInit() {
        WeakReference<Context> weakReference = ANDROID_PLATFORM_MODULE.context;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    public m getPermissionController() {
        return this.permissionController;
    }

    public j getTimeProvider() {
        return this.timeProvider;
    }
}
