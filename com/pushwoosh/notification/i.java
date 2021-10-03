package com.pushwoosh.notification;

import com.pushwoosh.repository.b;
import com.pushwoosh.repository.c;

final /* synthetic */ class i implements c.a {
    private final long a;

    private i(long j) {
        this.a = j;
    }

    public static c.a a(long j) {
        return new i(j);
    }

    @Override // com.pushwoosh.repository.c.a
    public void a(b bVar) {
        RescheduleNotificationsWorker.a(this.a, bVar);
    }
}
