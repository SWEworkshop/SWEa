package com.pushwoosh.inbox.e.b;

import android.database.Cursor;
import com.pushwoosh.inbox.a.a;

/* access modifiers changed from: package-private */
public final /* synthetic */ class d implements a {
    private final b a;

    private d(b bVar) {
        this.a = bVar;
    }

    public static a a(b bVar) {
        return new d(bVar);
    }

    @Override // com.pushwoosh.inbox.a.a
    public Object a(Object obj) {
        return b.c(this.a, (Cursor) obj);
    }
}
