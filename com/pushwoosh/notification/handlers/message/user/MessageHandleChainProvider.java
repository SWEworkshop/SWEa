package com.pushwoosh.notification.handlers.message.user;

import androidx.annotation.NonNull;
import com.pushwoosh.internal.chain.Chain;
import com.pushwoosh.notification.handlers.message.user.a;

public class MessageHandleChainProvider {
    private static final MessageHandleChainProvider a = new MessageHandleChainProvider();
    private Chain<MessageHandler> b;

    private MessageHandleChainProvider() {
    }

    @NonNull
    private static Chain<MessageHandler> a() {
        return new a.C0018a().a(new b()).a(new d()).a(new c()).a();
    }

    @NonNull
    public static Chain<MessageHandler> getHandleProcessor() {
        return a.b;
    }

    public static void init() {
        a.b = a();
    }
}
