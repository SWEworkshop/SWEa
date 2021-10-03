package com.pushwoosh.inbox.e;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.pushwoosh.inbox.internal.data.InboxMessageStatus;
import com.pushwoosh.inbox.internal.data.b;
import java.util.Collection;

public interface a {
    @NonNull
    @WorkerThread
    com.pushwoosh.inbox.e.a.a a(@NonNull Collection<b> collection, boolean z);

    @Nullable
    @WorkerThread
    b a(String str);

    @NonNull
    @WorkerThread
    Collection<b> a();

    @NonNull
    @WorkerThread
    Collection<b> a(long j, int i);

    @WorkerThread
    Collection<String> a(@NonNull String str, @NonNull InboxMessageStatus inboxMessageStatus);

    @WorkerThread
    void a(@NonNull Collection<String> collection);

    @WorkerThread
    int b();

    @NonNull
    @WorkerThread
    Collection<b> b(Collection<String> collection);

    @WorkerThread
    int c();

    @WorkerThread
    int d();
}
