package com.pushwoosh.inbox.e.b;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.inbox.internal.data.InboxMessageStatus;
import com.pushwoosh.inbox.internal.data.b;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class a implements com.pushwoosh.inbox.e.a {
    private final b a;

    public a(b bVar) {
        this.a = bVar;
    }

    @Override // com.pushwoosh.inbox.e.a
    @NonNull
    public com.pushwoosh.inbox.e.a.a a(@NonNull Collection<b> collection, boolean z) {
        return (!collection.isEmpty() || z) ? this.a.a(collection, z) : com.pushwoosh.inbox.e.a.a.b();
    }

    @Override // com.pushwoosh.inbox.e.a
    @Nullable
    public b a(String str) {
        return this.a.a(str);
    }

    @Override // com.pushwoosh.inbox.e.a
    @NonNull
    public Collection<b> a() {
        Collection<b> a2 = this.a.a();
        return a2 == null ? Collections.emptyList() : a2;
    }

    @Override // com.pushwoosh.inbox.e.a
    @NonNull
    public Collection<b> a(long j, int i) {
        Collection<b> a2 = this.a.a(InboxMessageStatus.getActualCodes(), j, i);
        return a2 == null ? Collections.emptyList() : a2;
    }

    @Override // com.pushwoosh.inbox.e.a
    public Collection<String> a(@NonNull String str, @NonNull InboxMessageStatus inboxMessageStatus) {
        return this.a.a(Collections.singleton(str), inboxMessageStatus);
    }

    @Override // com.pushwoosh.inbox.e.a
    public void a(@NonNull Collection<String> collection) {
        if (!collection.isEmpty()) {
            this.a.a(collection);
        }
    }

    @Override // com.pushwoosh.inbox.e.a
    public int b() {
        Integer b = this.a.b(InboxMessageStatus.READ.getLowerStatus());
        if (b == null) {
            return 0;
        }
        return b.intValue();
    }

    @Override // com.pushwoosh.inbox.e.a
    @NonNull
    public Collection<b> b(Collection<String> collection) {
        List<b> c = this.a.c(collection);
        return c == null ? Collections.emptyList() : c;
    }

    @Override // com.pushwoosh.inbox.e.a
    public int c() {
        Integer b = this.a.b(InboxMessageStatus.OPEN.getLowerStatus());
        if (b == null) {
            return 0;
        }
        return b.intValue();
    }

    @Override // com.pushwoosh.inbox.e.a
    public int d() {
        Integer b = this.a.b(InboxMessageStatus.DELETED_BY_USER.getLowerStatus());
        if (b == null) {
            return 0;
        }
        return b.intValue();
    }
}
