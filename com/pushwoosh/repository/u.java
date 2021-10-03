package com.pushwoosh.repository;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

/* access modifiers changed from: package-private */
public final /* synthetic */ class u implements Callback {
    private final RegistrationPrefs a;

    private u(RegistrationPrefs registrationPrefs) {
        this.a = registrationPrefs;
    }

    public static Callback a(RegistrationPrefs registrationPrefs) {
        return new u(registrationPrefs);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        t.a(this.a, result);
    }
}
