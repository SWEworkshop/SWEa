package com.pushwoosh;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.a.c;

public class m {
    public final String a = m.class.getSimpleName();
    private Context b;
    private NotificationManager c;

    public m(Context context, NotificationManager notificationManager) {
        this.b = context;
        this.c = notificationManager;
    }

    @RequiresApi(api = 26)
    private int b() {
        NotificationManager notificationManager = this.c;
        if (notificationManager != null) {
            return c.a(notificationManager);
        }
        PWLog.error(this.a, "notificationManager is null");
        return 6;
    }

    private boolean c() {
        return NotificationManagerCompat.from(this.b).areNotificationsEnabled();
    }

    public int a() {
        if (Build.VERSION.SDK_INT < 26) {
            return c() ? 6 : 0;
        }
        if (c()) {
            return b();
        }
        return 0;
    }
}
