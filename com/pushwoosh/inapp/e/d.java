package com.pushwoosh.inapp.e;

import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.network.RequestManager;

/* access modifiers changed from: package-private */
public final /* synthetic */ class d implements EventListener {
    private final RequestManager a;

    private d(RequestManager requestManager) {
        this.a = requestManager;
    }

    public static EventListener a(RequestManager requestManager) {
        return new d(requestManager);
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public void onReceive(Event event) {
        this.a.sendRequest(new l(((com.pushwoosh.inapp.view.d) event).a().a()));
    }
}
