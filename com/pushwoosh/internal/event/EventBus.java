package com.pushwoosh.internal.event;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.pushwoosh.internal.crash.LogSender;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class EventBus {
    private static final Map<Class<? extends Event>, List<EventListener<?>>> a = new ConcurrentHashMap();

    private EventBus() {
    }

    private static <T extends Event> boolean a(@NonNull T t, Handler handler) {
        List<EventListener<?>> list;
        Class<?> cls = t.getClass();
        if (!a.containsKey(cls) || (list = a.get(cls)) == null) {
            return false;
        }
        handler.post(c.a(t, list));
        return true;
    }

    /* access modifiers changed from: private */
    @MainThread
    public static <T extends Event> void b(@NonNull T t, List<EventListener<?>> list) {
        ArrayList<EventListener> arrayList;
        synchronized (list) {
            arrayList = new ArrayList(list);
        }
        for (EventListener eventListener : arrayList) {
            eventListener.onReceive(t);
        }
    }

    public static <T extends Event> boolean sendEvent(@NonNull T t) {
        return a(t, new Handler(Looper.getMainLooper()));
    }

    public static <T extends Event> Subscription<T> subscribe(Class<T> cls, EventListener<T> eventListener) {
        if (eventListener == null) {
            LogSender.submitCustomError(new NullPointerException("listener == null"));
            return null;
        }
        List<EventListener<?>> list = a.get(cls);
        if (list == null) {
            list = new LinkedList<>();
            a.put(cls, list);
        }
        synchronized (list) {
            list.add(eventListener);
        }
        return new Subscription<>(cls, eventListener);
    }

    public static <T extends Event> void unsubscribe(Class<T> cls, EventListener<T> eventListener) {
        List<EventListener<?>> list;
        if (a.containsKey(cls) && (list = a.get(cls)) != null) {
            synchronized (list) {
                list.remove(eventListener);
            }
        }
    }
}
