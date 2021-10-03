package com.pushwoosh.inbox.e.b;

import android.database.Cursor;
import com.pushwoosh.inbox.a.a;

/* access modifiers changed from: package-private */
public final /* synthetic */ class f implements a {
    private final b a;

    private f(b bVar) {
        this.a = bVar;
    }

    public static a a(b bVar) {
        return new f(bVar);
    }

    @Override // com.pushwoosh.inbox.a.a
    public Object a(Object obj) {
        return b.b(this.a, (Cursor) obj);
    }
}
