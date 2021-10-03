package com.pushwoosh;

import androidx.annotation.WorkerThread;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushFcmIntentService extends FirebaseMessagingService {
    @Override // com.google.firebase.messaging.FirebaseMessagingService
    @WorkerThread
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        PushwooshFcmHelper.onMessageReceived(getApplicationContext(), remoteMessage);
    }
}
