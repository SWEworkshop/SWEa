package com.pushwoosh.notification.handlers.message.system;

import androidx.annotation.NonNull;
import com.pushwoosh.internal.chain.Chain;
import com.pushwoosh.notification.handlers.message.system.e;

public class MessageSystemHandleChainProvider {
    private static final MessageSystemHandleChainProvider a = new MessageSystemHandleChainProvider();
    private Chain<MessageSystemHandler> b;

    private MessageSystemHandleChainProvider() {
    }

    @NonNull
    private static Chain<MessageSystemHandler> a() {
        return new e.a().a(new f()).a(new a()).a(new d()).a(new b()).a();
    }

    @NonNull
    public static Chain<MessageSystemHandler> getMessageSystemChain() {
        return a.b;
    }

    public static void init() {
        a.b = a();
    }
}
