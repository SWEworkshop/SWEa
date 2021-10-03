package com.pushwoosh.internal.utils;

import android.os.Handler;
import android.os.Looper;
import java.util.LinkedList;
import java.util.List;

public class a<T> {
    private final List<T> a = new LinkedList();
    private final int b;
    private final AbstractC0017a<T> c;
    private final Handler d;

    /* renamed from: com.pushwoosh.internal.utils.a$a  reason: collision with other inner class name */
    public interface AbstractC0017a<W> {
        void a(List<W> list);
    }

    public a(AbstractC0017a<T> aVar, int i) {
        this.b = i;
        this.c = aVar;
        this.d = new Handler(Looper.getMainLooper());
    }

    static /* synthetic */ void a(a aVar) {
        LinkedList linkedList = new LinkedList();
        synchronized (aVar.a) {
            linkedList.addAll(aVar.a);
            aVar.a.clear();
        }
        aVar.c.a(linkedList);
    }

    public void a(T t) {
        synchronized (this.a) {
            if (this.a.isEmpty()) {
                this.d.postDelayed(b.a(this), (long) this.b);
            }
            this.a.add(t);
        }
    }
}
