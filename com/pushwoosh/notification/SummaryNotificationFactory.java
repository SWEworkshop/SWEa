package com.pushwoosh.notification;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.app.NotificationCompat;
import com.pushwoosh.NotificationOpenReceiver;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.g;
import com.pushwoosh.notification.a.b;
import com.pushwoosh.notification.builder.NotificationBuilderManager;
import com.pushwoosh.notification.builder.e;
import com.pushwoosh.repository.RepositoryModule;

public abstract class SummaryNotificationFactory {
    @Nullable
    private final Context a = AndroidPlatformModule.getApplicationContext();
    private b b = new b(this.a);

    SummaryNotificationFactory() {
    }

    private String a() {
        return this.b.a("Push notifications group");
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static Intent getNotificationIntent() {
        Intent intent = new Intent(AndroidPlatformModule.getApplicationContext(), NotificationOpenReceiver.class);
        intent.setAction("summary-" + System.currentTimeMillis());
        return intent;
    }

    public boolean autoCancelSummaryNotification() {
        return true;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final Context getApplicationContext() {
        return this.a;
    }

    @RequiresApi(24)
    public final Notification onGenerateSummaryNotification(int i) {
        if (getApplicationContext() == null) {
            PWLog.error("onGenerateSummaryNotification Incorrect state of app. Context is null");
            return null;
        }
        int summaryNotificationIconResId = summaryNotificationIconResId();
        if (summaryNotificationIconResId == -1) {
            summaryNotificationIconResId = g.a((String) null);
        }
        int summaryNotificationColor = summaryNotificationColor();
        if (summaryNotificationColor == -1) {
            summaryNotificationColor = RepositoryModule.getNotificationPreferences().h().get();
        }
        e createSummaryNotificationBuilder = NotificationBuilderManager.createSummaryNotificationBuilder(getApplicationContext(), a());
        createSummaryNotificationBuilder.a(summaryNotificationIconResId).c(summaryNotificationColor).b(i).a(autoCancelSummaryNotification()).a("group_undefined").b(true);
        String summaryNotificationMessage = summaryNotificationMessage(i);
        if (!TextUtils.isEmpty(summaryNotificationMessage)) {
            createSummaryNotificationBuilder.a(new NotificationCompat.InboxStyle().setSummaryText(summaryNotificationMessage));
        }
        return createSummaryNotificationBuilder.a();
    }

    @ColorInt
    public abstract int summaryNotificationColor();

    public abstract int summaryNotificationIconResId();

    public abstract String summaryNotificationMessage(int i);
}
