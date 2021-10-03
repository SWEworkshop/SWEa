package com.pushwoosh.repository;

import com.pushwoosh.internal.network.PushRequest;

/* access modifiers changed from: package-private */
public class ae extends PushRequest<Void> {
    private final String a = RepositoryModule.getRegistrationPreferences().applicationId().get();
    private final String b = RepositoryModule.getRegistrationPreferences().userId().get();

    ae() {
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public String getApplicationId() {
        return this.a;
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "unregisterDevice";
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getUserId() {
        return this.b;
    }
}
