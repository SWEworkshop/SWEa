package com.pushwoosh;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.pushwoosh.internal.b.a;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;
import com.pushwoosh.internal.utils.NotificationRegistrarHelper;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.b;
import com.pushwoosh.repository.RepositoryModule;
import java.io.IOException;
import java.util.Map;

public class PushwooshFcmHelper {
    private static final String TAG = "FcmHelper";

    public static boolean isPushwooshMessage(RemoteMessage remoteMessage) {
        return b.a(remoteMessage);
    }

    public static boolean onMessageReceived(Context context, RemoteMessage remoteMessage) {
        p.a(context);
        if (!isPushwooshMessage(remoteMessage) || !DeviceSpecificProvider.getInstance().isFirebase()) {
            return false;
        }
        String from = remoteMessage.getFrom();
        Map<String, String> data = remoteMessage.getData();
        PWLog.info(TAG, "Received message: " + data.toString() + " from: " + from);
        NotificationRegistrarHelper.handleMessage(a.a(remoteMessage));
        return true;
    }

    public static void onTokenRefresh(Context context, @Nullable String str) {
        p.a(context);
        try {
            String token = FirebaseInstanceId.getInstance().getToken(Pushwoosh.getInstance().getSenderId(), FirebaseMessaging.INSTANCE_ID_SCOPE);
            PWLog.debug(TAG, "onTokenRefresh");
            if (token == null || !token.equals(RepositoryModule.getRegistrationPreferences().pushToken().get())) {
                NotificationRegistrarHelper.onRegisteredForRemoteNotifications(token);
            }
        } catch (IOException e) {
            String localizedMessage = e.getLocalizedMessage();
            PWLog.error("PushwooshFcmHelper", "FCM registration error:" + localizedMessage);
        }
    }
}
