package com.pushwoosh;

import com.pushwoosh.function.Callback;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.notification.event.RegistrationSuccessEvent;

final /* synthetic */ class o implements EventListener {
    private final Pushwoosh a;
    private final Callback b;

    private o(Pushwoosh pushwoosh, Callback callback) {
        this.a = pushwoosh;
        this.b = callback;
    }

    public static EventListener a(Pushwoosh pushwoosh, Callback callback) {
        return new o(pushwoosh, callback);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        Pushwoosh.a(this.a, this.b, (RegistrationSuccessEvent) event);
    }
}
