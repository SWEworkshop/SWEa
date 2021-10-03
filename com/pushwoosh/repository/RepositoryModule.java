package com.pushwoosh.repository;

import android.content.Context;
import android.os.AsyncTask;
import com.pushwoosh.internal.Plugin;
import com.pushwoosh.internal.network.f;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.platform.prefs.migration.MigrationScheme;
import com.pushwoosh.internal.platform.prefs.migration.b;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.d;
import com.pushwoosh.internal.utils.k;
import com.pushwoosh.j;
import java.util.ArrayList;
import java.util.Collection;

public class RepositoryModule {
    private static m inboxNotificationStorage;
    private static n localNotificationStorage;
    private static o lockScreenMediaStorage;
    private static q notificationPreferences;
    private static r pushBundleStorage;
    private static RegistrationPrefs registrationPreferences;
    private static f requestStorage;

    private static void createLocalNotificationStorage() {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext == null) {
            PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
            return;
        }
        localNotificationStorage = new n(new c(applicationContext));
        new j(applicationContext).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static m getInboxNotificationStorage() {
        return inboxNotificationStorage;
    }

    public static n getLocalNotificationStorage() {
        return localNotificationStorage;
    }

    public static o getLockScreenMediaStorage() {
        return lockScreenMediaStorage;
    }

    public static q getNotificationPreferences() {
        return notificationPreferences;
    }

    public static r getPushBundleStorage() {
        return pushBundleStorage;
    }

    public static RegistrationPrefs getRegistrationPreferences() {
        return registrationPreferences;
    }

    public static f getRequestStorage() {
        return requestStorage;
    }

    public static void init(d dVar, k kVar, d dVar2) {
        migratePrefsIfNeeded(dVar);
        if (notificationPreferences == null) {
            notificationPreferences = new q(dVar);
        }
        if (registrationPreferences == null) {
            registrationPreferences = new RegistrationPrefs(dVar, dVar2);
        }
        if (localNotificationStorage == null) {
            createLocalNotificationStorage();
        }
        if (requestStorage == null) {
            requestStorage = new f(AndroidPlatformModule.getApplicationContext(), kVar);
        }
        if (lockScreenMediaStorage == null) {
            lockScreenMediaStorage = new LockScreenMediaStorageImpl(AndroidPlatformModule.getApplicationContext());
        }
        if (pushBundleStorage == null) {
            pushBundleStorage = new PushBundleStorageImpl(AndroidPlatformModule.getApplicationContext());
        }
        if (inboxNotificationStorage == null) {
            inboxNotificationStorage = new InboxNotificationStorageImpl(AndroidPlatformModule.getApplicationContext());
        }
    }

    private static void migratePrefsIfNeeded(d dVar) {
        PrefsProvider c;
        PWLog.noise("Migrate prefs if needed");
        b prefsMigration = AndroidPlatformModule.getPrefsMigration();
        if (!(prefsMigration == null || (c = com.pushwoosh.internal.platform.prefs.d.c()) == null)) {
            PWLog.noise("Start migration with prevPrefsProvider: " + c.getClass().getName());
            ArrayList arrayList = new ArrayList();
            arrayList.add(RegistrationPrefs.provideMigrationScheme(c));
            arrayList.add(q.a(c));
            for (Plugin plugin : dVar.m()) {
                Collection<? extends MigrationScheme> prefsMigrationSchemes = plugin.getPrefsMigrationSchemes(c);
                if (prefsMigrationSchemes != null) {
                    arrayList.addAll(prefsMigrationSchemes);
                }
            }
            prefsMigration.a(arrayList);
        }
    }

    public static void setLocalNotificationStorage(n nVar) {
        localNotificationStorage = nVar;
    }

    public static void setNotificationPreferences(q qVar) {
        notificationPreferences = qVar;
    }

    public static void setRegistrationPreferences(RegistrationPrefs registrationPrefs) {
        registrationPreferences = registrationPrefs;
    }

    public static void setRequestStorage(f fVar) {
        requestStorage = fVar;
    }
}
