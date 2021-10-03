package com.pushwoosh.inbox;

import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

final /* synthetic */ class a implements Callback {
    private final com.pushwoosh.inbox.internal.b.a a;

    private a(com.pushwoosh.inbox.internal.b.a aVar) {
        this.a = aVar;
    }

    public static Callback a(com.pushwoosh.inbox.internal.b.a aVar) {
        return new a(aVar);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        PushwooshInbox.lambda$performAction$0(this.a, result);
    }
}
