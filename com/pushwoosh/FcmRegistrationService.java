package com.pushwoosh;

import com.google.firebase.iid.FirebaseInstanceIdService;

public class FcmRegistrationService extends FirebaseInstanceIdService {
    public void onTokenRefresh() {
        PushwooshFcmHelper.onTokenRefresh(getApplicationContext(), null);
    }
}
