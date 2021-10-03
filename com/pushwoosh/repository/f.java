package com.pushwoosh.repository;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

/* access modifiers changed from: package-private */
public final /* synthetic */ class f implements Callback {
    private final String a;
    private final RegistrationPrefs b;

    private f(String str, RegistrationPrefs registrationPrefs) {
        this.a = str;
        this.b = registrationPrefs;
    }

    public static Callback a(String str, RegistrationPrefs registrationPrefs) {
        return new f(str, registrationPrefs);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        d.a(this.a, this.b, result);
    }
}
