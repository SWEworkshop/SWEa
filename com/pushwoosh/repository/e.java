package com.pushwoosh.repository;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

/* access modifiers changed from: package-private */
public final /* synthetic */ class e implements Callback {
    private final RegistrationPrefs a;
    private final String b;

    private e(RegistrationPrefs registrationPrefs, String str) {
        this.a = registrationPrefs;
        this.b = str;
    }

    public static Callback a(RegistrationPrefs registrationPrefs, String str) {
        return new e(registrationPrefs, str);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        d.a(this.a, this.b, result);
    }
}
