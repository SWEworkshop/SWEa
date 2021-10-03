package com.pushwoosh.inapp.e;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.pushwoosh.inapp.f.c;
import com.pushwoosh.inapp.f.d;
import java.io.File;

/* access modifiers changed from: package-private */
public class b {
    private final d a;
    private final c b;

    b(d dVar, c cVar) {
        this.a = dVar;
        this.b = cVar;
    }

    @WorkerThread
    public boolean a(@NonNull com.pushwoosh.inapp.e.b.b bVar) {
        com.pushwoosh.inapp.e.b.b a2 = this.a.a(bVar.a());
        File c = this.b.c(bVar.a());
        return a2 != null && a2.d() == bVar.d() && c != null && c.exists();
    }
}
