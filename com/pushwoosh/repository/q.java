package com.pushwoosh.repository;

import android.content.SharedPreferences;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.platform.prefs.migration.MigrationScheme;
import com.pushwoosh.internal.preference.PreferenceArrayListValue;
import com.pushwoosh.internal.preference.PreferenceBooleanValue;
import com.pushwoosh.internal.preference.PreferenceClassValue;
import com.pushwoosh.internal.preference.PreferenceIntValue;
import com.pushwoosh.internal.preference.PreferenceJsonObjectValue;
import com.pushwoosh.internal.preference.PreferenceSoundTypeValue;
import com.pushwoosh.internal.preference.PreferenceStringValue;
import com.pushwoosh.internal.preference.PreferenceVibrateTypeValue;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.d;
import com.pushwoosh.notification.SoundType;
import com.pushwoosh.notification.VibrateType;

public class q {
    private final PreferenceBooleanValue a;
    private final PreferenceIntValue b;
    private final PreferenceBooleanValue c;
    private final PreferenceBooleanValue d;
    private final PreferenceIntValue e;
    private final PreferenceIntValue f;
    private final PreferenceIntValue g;
    private final PreferenceStringValue h;
    private final PreferenceBooleanValue i;
    private final PreferenceSoundTypeValue j;
    private final PreferenceVibrateTypeValue k;
    private final PreferenceStringValue l;
    private final PreferenceArrayListValue<String> m;
    private final PreferenceJsonObjectValue n;
    private final PreferenceClassValue o;
    private final PreferenceClassValue p;
    private final PreferenceBooleanValue q;
    private final PreferenceStringValue r;

    q(d dVar) {
        PWLog.noise("NotificationPrefs()...");
        SharedPreferences providePrefs = AndroidPlatformModule.getPrefsProvider().providePrefs("com.pushwoosh.pushnotifications");
        this.a = new PreferenceBooleanValue(providePrefs, "dm_multimode", dVar.i());
        this.b = new PreferenceIntValue(providePrefs, "dm_messageid", 1001);
        this.c = new PreferenceBooleanValue(providePrefs, "dm_lightson", dVar.j());
        this.d = new PreferenceBooleanValue(providePrefs, "dm_ledon", false);
        this.e = new PreferenceIntValue(providePrefs, "dm_led_color", -1);
        this.o = new PreferenceClassValue(providePrefs, "pw_notification_factory", dVar.g());
        this.p = new PreferenceClassValue(providePrefs, "pw_notification_factory", dVar.h());
        this.f = new PreferenceIntValue(providePrefs, "pw_notification_background_color", dVar.l());
        this.g = new PreferenceIntValue(providePrefs, "pw_richmedia_delay", dVar.n().richMediaStartDelay());
        this.h = new PreferenceStringValue(providePrefs, "pw_notification_stat_hash", null);
        this.i = new PreferenceBooleanValue(providePrefs, "pw_notifications_enabled", true);
        this.j = new PreferenceSoundTypeValue(providePrefs, "dm_soundtype", SoundType.DEFAULT_MODE);
        this.k = new PreferenceVibrateTypeValue(providePrefs, "dm_vibratetype", VibrateType.DEFAULT_MODE);
        this.l = new PreferenceStringValue(providePrefs, "channel_name", "Push notification");
        this.m = new PreferenceArrayListValue<>(providePrefs, "pushHistoryArray", 16, String.class);
        this.n = new PreferenceJsonObjectValue(providePrefs, "cached_tags_string");
        this.q = new PreferenceBooleanValue(providePrefs, "pw_tags_migration_done", false);
        this.r = new PreferenceStringValue(providePrefs, "pw_custom_data", null);
        PWLog.noise("NotificationPrefs() done");
    }

    static MigrationScheme a(PrefsProvider prefsProvider) {
        MigrationScheme migrationScheme = new MigrationScheme("com.pushwoosh.pushnotifications");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.BOOLEAN, "dm_multimode");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.INT, "dm_soundtype");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.INT, "dm_vibratetype");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, "channel_name");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.INT, "dm_messageid");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.BOOLEAN, "dm_lightson");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.BOOLEAN, "dm_ledon");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.INT, "dm_led_color");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, "pw_notification_factory");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, "pushHistoryArray");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, "cached_tags_string");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.INT, "pw_notification_background_color");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, "pw_notification_stat_hash");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.INT, "pw_richmedia_delay");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.BOOLEAN, "pw_notifications_enabled");
        return migrationScheme;
    }

    public PreferenceBooleanValue a() {
        return this.a;
    }

    public PreferenceIntValue b() {
        return this.b;
    }

    public PreferenceBooleanValue c() {
        return this.c;
    }

    public PreferenceBooleanValue d() {
        return this.d;
    }

    public PreferenceIntValue e() {
        return this.e;
    }

    public PreferenceClassValue f() {
        return this.o;
    }

    public PreferenceClassValue g() {
        return this.p;
    }

    public PreferenceIntValue h() {
        return this.f;
    }

    public PreferenceIntValue i() {
        return this.g;
    }

    public PreferenceStringValue j() {
        return this.h;
    }

    public PreferenceBooleanValue k() {
        return this.i;
    }

    public PreferenceSoundTypeValue l() {
        return this.j;
    }

    public PreferenceVibrateTypeValue m() {
        return this.k;
    }

    public PreferenceStringValue n() {
        return this.l;
    }

    public PreferenceArrayListValue<String> o() {
        return this.m;
    }

    public PreferenceJsonObjectValue p() {
        return this.n;
    }

    public PreferenceBooleanValue q() {
        return this.q;
    }

    public PreferenceStringValue r() {
        return this.r;
    }
}
