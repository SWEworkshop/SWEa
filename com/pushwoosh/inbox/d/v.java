package com.pushwoosh.inbox.d;

import android.os.AsyncTask;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inbox.exception.InboxMessagesException;
import com.pushwoosh.internal.utils.PWLog;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/* access modifiers changed from: package-private */
public class v<T> {
    private final Collection<Callback<T, InboxMessagesException>> a = new ConcurrentLinkedQueue();
    private final s<T> b;

    v(s<T> sVar) {
        this.b = sVar;
    }

    static /* synthetic */ void a(v vVar, Result result) {
        for (Callback<T, InboxMessagesException> callback : vVar.a) {
            if (callback != null) {
                try {
                    callback.process(result);
                } catch (Exception e) {
                    PWLog.error("Error occurred while processing Callback", e.getMessage());
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        new a(this.b, w.a(this)).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    /* access modifiers changed from: package-private */
    public void a(Callback<T, InboxMessagesException> callback) {
        if (callback != null) {
            this.a.add(callback);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(Callback<T, InboxMessagesException> callback) {
        if (callback != null) {
            this.a.remove(callback);
        }
    }
}
