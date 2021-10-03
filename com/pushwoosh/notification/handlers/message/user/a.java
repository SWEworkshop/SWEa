package com.pushwoosh.notification.handlers.message.user;

import androidx.annotation.NonNull;
import com.pushwoosh.internal.chain.Chain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* access modifiers changed from: package-private */
public class a implements Chain<MessageHandler> {
    private final Collection<MessageHandler> a;

    /* renamed from: com.pushwoosh.notification.handlers.message.user.a$a  reason: collision with other inner class name */
    public static final class C0018a {
        private final Collection<MessageHandler> a = new ArrayList();

        /* access modifiers changed from: package-private */
        public C0018a a(MessageHandler messageHandler) {
            this.a.add(messageHandler);
            return this;
        }

        public a a() {
            return new a(this.a);
        }
    }

    private a(@NonNull Collection<MessageHandler> collection) {
        this.a = collection;
    }

    /* renamed from: a */
    public void addItem(MessageHandler messageHandler) {
        this.a.add(messageHandler);
    }

    /* renamed from: b */
    public void removeItem(MessageHandler messageHandler) {
        this.a.remove(messageHandler);
    }

    @Override // com.pushwoosh.internal.chain.Chain
    public Iterator<MessageHandler> getIterator() {
        return this.a.iterator();
    }
}
