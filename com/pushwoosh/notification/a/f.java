package com.pushwoosh.notification.a;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.net.Uri;
import com.pushwoosh.internal.utils.g;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.notification.VibrateType;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.q;

/* access modifiers changed from: package-private */
@TargetApi(26)
public class f implements d {
    private final NotificationManager a;

    private static class a {
        private int a = -1;
        private Uri b;
        private boolean c;
        private boolean d;
        private int e = 3;

        public a() {
            q notificationPreferences = RepositoryModule.getNotificationPreferences();
            a(notificationPreferences.e().get()).a(!notificationPreferences.m().get().equals(VibrateType.NO_VIBRATE)).b(notificationPreferences.c().get());
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x0030  */
        public NotificationChannel a(String str, String str2, String str3) {
            long[] jArr;
            NotificationChannel notificationChannel = new NotificationChannel(str, str2, this.e);
            if (this.c) {
                if (g.a()) {
                    notificationChannel.enableVibration(true);
                    jArr = a.a();
                }
                notificationChannel.enableLights(this.d);
                notificationChannel.setLightColor(this.a);
                if (this.b != null) {
                    notificationChannel.setSound(this.b, new AudioAttributes.Builder().setUsage(5).setContentType(4).build());
                }
                notificationChannel.setDescription(str3);
                return notificationChannel;
            }
            notificationChannel.enableVibration(false);
            jArr = null;
            notificationChannel.setVibrationPattern(jArr);
            notificationChannel.enableLights(this.d);
            notificationChannel.setLightColor(this.a);
            if (this.b != null) {
            }
            notificationChannel.setDescription(str3);
            return notificationChannel;
        }

        /* access modifiers changed from: package-private */
        public a a(int i) {
            this.a = i;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(Uri uri) {
            this.b = uri;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(boolean z) {
            this.c = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a b(int i) {
            this.e = i;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a b(boolean z) {
            this.d = z;
            return this;
        }
    }

    f(NotificationManager notificationManager) {
        this.a = notificationManager;
    }

    @Override // com.pushwoosh.notification.a.d
    public void a(Notification notification, int i, int i2, int i3) {
    }

    @Override // com.pushwoosh.notification.a.d
    public void a(Notification notification, Uri uri, boolean z) {
    }

    @Override // com.pushwoosh.notification.a.d
    public void a(Notification notification, VibrateType vibrateType, boolean z) {
    }

    @Override // com.pushwoosh.notification.a.d
    public void a(String str) {
        NotificationChannel a2 = new a().a(str, str, "");
        a2.setSound(null, null);
        this.a.createNotificationChannel(a2);
    }

    @Override // com.pushwoosh.notification.a.d
    public void a(String str, String str2, String str3, PushMessage pushMessage) {
        Uri b;
        a aVar = new a();
        if (pushMessage.getLed() != null) {
            aVar.a(pushMessage.getLed().intValue());
            aVar.b(true);
        }
        if (!(pushMessage.getSound() == null || (b = g.b(pushMessage.getSound())) == null)) {
            aVar.a(b);
        }
        aVar.b(a.b(pushMessage));
        aVar.a(pushMessage.getVibration());
        this.a.createNotificationChannel(aVar.a(str, str2, str3));
    }
}
