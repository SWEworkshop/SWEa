package com.pushwoosh.inbox.d;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inbox.d.a.a;
import com.pushwoosh.inbox.exception.InboxMessagesException;
import com.pushwoosh.inbox.internal.b.c;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.utils.PWLog;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/* access modifiers changed from: package-private */
public class t<T> {
    private final Collection<Callback<T, InboxMessagesException>> a = new ConcurrentLinkedQueue();
    private final Handler b = new Handler(Looper.getMainLooper());
    private final c<Result<a, NetworkException>, Result<T, InboxMessagesException>> c;

    t(c<Result<a, NetworkException>, Result<T, InboxMessagesException>> cVar) {
        this.c = cVar;
    }

    /* access modifiers changed from: private */
    public void b(Result<T, InboxMessagesException> result) {
        for (Callback<T, InboxMessagesException> callback : this.a) {
            if (callback != null) {
                try {
                    callback.process(result);
                    this.a.remove(callback);
                } catch (Exception e) {
                    PWLog.error("Error occurred while processing Callback", e.getMessage());
                }
            }
        }
        this.a.clear();
    }

    /* access modifiers changed from: package-private */
    public void a(@Nullable Callback<T, InboxMessagesException> callback) {
        if (callback != null) {
            this.a.add(callback);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Result<a, NetworkException> result) {
        this.b.post(u.a(this, result));
    }
}
