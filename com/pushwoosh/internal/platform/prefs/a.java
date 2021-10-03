package com.pushwoosh.internal.platform.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.b;
import com.pushwoosh.thirdpart.com.ironz.binaryprefs.c.c;

/* access modifiers changed from: package-private */
public class a implements PrefsProvider {
    private final Context a;
    private final c b = b.a();

    a(Context context) {
        this.a = context;
    }

    @Override // com.pushwoosh.internal.platform.prefs.PrefsProvider
    public SharedPreferences provideDefault() {
        return new b(this.a).a("pushwoosh_default").a(false).a(this.b).a();
    }

    @Override // com.pushwoosh.internal.platform.prefs.PrefsProvider
    public SharedPreferences providePrefs(String str) {
        return new b(this.a).a(str).a(false).a(this.b).a();
    }
}
