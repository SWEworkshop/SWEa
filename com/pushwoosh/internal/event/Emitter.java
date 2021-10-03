package com.pushwoosh.internal.event;

import com.pushwoosh.internal.event.Event;
import java.util.LinkedList;
import java.util.List;

public abstract class Emitter<T extends Event> {
    protected EventListener<T> listener;

    public static <T extends Event> Emitter<T> forEvent(final Class<T> cls) {
        return new Emitter<T>() {
            /* class com.pushwoosh.internal.event.Emitter.AnonymousClass2 */

            @Override // com.pushwoosh.internal.event.Emitter
            public void bind(EventListener<T> eventListener) {
                Emitter.super.bind(eventListener);
                EventBus.subscribe(cls, eventListener);
            }

            @Override // com.pushwoosh.internal.event.Emitter
            public void unbind() {
                EventBus.unsubscribe(cls, this.listener);
                Emitter.super.unbind();
            }
        };
    }

    public static <T extends Event> Emitter<T> when(Emitter<T> emitter, final Emitter<?> emitter2) {
        return new Emitter<T>(emitter) {
            /* class com.pushwoosh.internal.event.Emitter.AnonymousClass1 */
            boolean a = false;
            List<T> b = new LinkedList();
            final /* synthetic */ Emitter c;

            {
                this.c = r1;
            }

            /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.pushwoosh.internal.event.Emitter$1 */
            /* JADX WARN: Multi-variable type inference failed */
            static /* synthetic */ void a(AnonymousClass1 r1, Event event) {
                r1.a = true;
                for (T t : r1.b) {
                    r1.emit(t);
                }
            }

            static /* synthetic */ void b(AnonymousClass1 r1, Event event) {
                if (r1.a) {
                    r1.emit(event);
                } else {
                    r1.b.add(event);
                }
            }

            @Override // com.pushwoosh.internal.event.Emitter
            public void bind(EventListener<T> eventListener) {
                Emitter.super.bind(eventListener);
                this.c.bind(a.a(this));
                emitter2.bind(b.a(this));
            }
        };
    }

    public void bind(EventListener<T> eventListener) {
        this.listener = eventListener;
    }

    /* access modifiers changed from: protected */
    public void emit(T t) {
        EventListener<T> eventListener = this.listener;
        if (eventListener != null) {
            eventListener.onReceive(t);
        }
    }

    public void unbind() {
        this.listener = null;
    }
}
