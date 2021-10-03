package com.pushwoosh.notification.handlers.message.user;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pushwoosh.NotificationOpenReceiver;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.g;
import com.pushwoosh.notification.LocalNotificationReceiver;
import com.pushwoosh.notification.NotificationFactory;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.notification.PushwooshNotificationFactory;
import com.pushwoosh.notification.SummaryNotificationUtils;
import com.pushwoosh.notification.builder.NotificationBuilderManager;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.n;
import com.pushwoosh.repository.q;
import java.util.List;

/* access modifiers changed from: package-private */
public class d extends NotificationMessageHandler {
    private static final String a = "d";
    private static final Object b = new Object();
    private final NotificationFactory c = b();
    private final q d = RepositoryModule.getNotificationPreferences();

    d() {
    }

    private int a(String str) {
        int i;
        if (!TextUtils.isEmpty(str)) {
            return 0;
        }
        synchronized (b) {
            i = this.d.b().get();
            if (this.d.a().get()) {
                i++;
                this.d.b().set(i);
            }
        }
        return i;
    }

    @NonNull
    private Intent a(long j) {
        Intent intent = new Intent(AndroidPlatformModule.getApplicationContext(), NotificationOpenReceiver.class);
        intent.putExtra("row_id", j);
        intent.putExtra("is_delete_intent", true);
        intent.setAction(Long.toString(System.currentTimeMillis()));
        return intent;
    }

    private void a() {
        if (RepositoryModule.getNotificationPreferences().c().get()) {
            g.c();
        }
    }

    private void a(Notification notification, Intent intent, @Nullable Intent intent2, PushMessage pushMessage) {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext == null) {
            PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
            return;
        }
        String tag = pushMessage.getTag();
        int a2 = a(tag);
        notification.contentIntent = PendingIntent.getBroadcast(applicationContext, a2, intent, 268435456);
        if (intent2 != null) {
            notification.deleteIntent = PendingIntent.getBroadcast(applicationContext, a2, intent2, 268435456);
        }
        a(intent, tag, a2);
        NotificationManager notificationManager = AndroidPlatformModule.getManagerProvider().getNotificationManager();
        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT < 23) {
                a(pushMessage, a2, tag);
            }
            notificationManager.notify(tag, a2, notification);
            a();
            b(pushMessage);
            EventBus.sendEvent(new NotificationCreatedEvent(a2, tag, pushMessage));
        }
    }

    private void a(Intent intent, String str, int i) {
        n localNotificationStorage = RepositoryModule.getLocalNotificationStorage();
        localNotificationStorage.a(i, str);
        if (intent.hasExtra(LocalNotificationReceiver.EXTRA_NOTIFICATION_ID)) {
            localNotificationStorage.a(intent.getIntExtra(LocalNotificationReceiver.EXTRA_NOTIFICATION_ID, 0), i, str);
        }
    }

    private void a(PushMessage pushMessage, int i, String str) {
        try {
            String string = pushMessage.toBundle().getString("pw_inbox");
            if (!TextUtils.isEmpty(string)) {
                RepositoryModule.getInboxNotificationStorage().a(string, i, str);
            }
        } catch (Exception e) {
            PWLog.error(a, e);
        }
    }

    private NotificationFactory b() {
        try {
            Class<?> cls = RepositoryModule.getNotificationPreferences().f().get();
            if (cls != null) {
                return (NotificationFactory) cls.newInstance();
            }
        } catch (Exception e) {
            PWLog.exception(e);
        }
        return new PushwooshNotificationFactory();
    }

    private void b(PushMessage pushMessage) {
        this.d.o().add(pushMessage.toJson().toString());
    }

    /* access modifiers changed from: protected */
    public void a(PushMessage pushMessage) {
        Notification onGenerateNotification = this.c.onGenerateNotification(pushMessage);
        if (onGenerateNotification != null) {
            if (Build.VERSION.SDK_INT >= 24) {
                onGenerateNotification = NotificationBuilderManager.setNotificationGroup(onGenerateNotification, "group_undefined");
                List<StatusBarNotification> activeNotifications = NotificationBuilderManager.getActiveNotifications();
                if (activeNotifications.size() >= 1) {
                    NotificationBuilderManager.setGroupToActiveNotifications(activeNotifications, "group_undefined");
                    Notification summaryNotification = SummaryNotificationUtils.getSummaryNotification(NotificationBuilderManager.isReplacingMessage(pushMessage, activeNotifications) ? activeNotifications.size() : activeNotifications.size() + 1);
                    if (summaryNotification != null) {
                        SummaryNotificationUtils.fireSummaryNotification(summaryNotification);
                    } else {
                        return;
                    }
                }
            }
            Intent notificationIntent = this.c.getNotificationIntent(pushMessage);
            notificationIntent.putExtra(FirebaseAnalytics.Param.GROUP_ID, 20191017);
            Intent intent = null;
            try {
                intent = a(RepositoryModule.getPushBundleStorage().b(pushMessage.toBundle()));
            } catch (Exception unused) {
            }
            a(onGenerateNotification, notificationIntent, intent, pushMessage);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.notification.handlers.message.user.NotificationMessageHandler
    public void handleNotification(PushMessage pushMessage) {
        if (pushMessage.isSilent()) {
            return;
        }
        if (this.d.a().get()) {
            a(pushMessage);
            return;
        }
        Notification onGenerateNotification = this.c.onGenerateNotification(pushMessage);
        if (onGenerateNotification != null) {
            a(onGenerateNotification, this.c.getNotificationIntent(pushMessage), null, pushMessage);
        }
    }
}
