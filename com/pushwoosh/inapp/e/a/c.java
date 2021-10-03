package com.pushwoosh.inapp.e.a;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.util.Pair;
import com.pushwoosh.inapp.e.b.b;
import com.pushwoosh.inapp.event.a;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.e;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class c {
    private final Object a = new Object();
    private final com.pushwoosh.inapp.f.c b;
    private final b c = new b();
    private final Set<b> d = new ConcurrentSkipListSet();

    public c(com.pushwoosh.inapp.f.c cVar) {
        this.b = cVar;
    }

    private boolean a(b bVar, File file) {
        if (this.c.a(new Pair<>(file, bVar))) {
            return true;
        }
        file.delete();
        return false;
    }

    @Nullable
    private File b(b bVar, File file) {
        file.deleteOnExit();
        PWLog.noise("[InApp]InAppDownloader", "Start deploy:" + bVar.a());
        return e.a(file, this.b.a(bVar.a()));
    }

    private void b(String str) {
        File a2 = this.b.a(str);
        if (a2 != null && a2.exists()) {
            e.a(a2);
        }
    }

    private boolean b(b bVar) {
        String str;
        StringBuilder sb;
        String str2;
        b(bVar.a());
        File c2 = c(bVar);
        if (c2 == null) {
            str = "[InApp]InAppDownloader";
            sb = new StringBuilder();
            str2 = "Failed to download ";
        } else if (!a(bVar, c2)) {
            str = "[InApp]InAppDownloader";
            sb = new StringBuilder();
            str2 = "File is not valid ";
        } else if (b(bVar, c2) != null) {
            return true;
        } else {
            str = "[InApp]InAppDownloader";
            sb = new StringBuilder();
            str2 = "Failed to deploy ";
        }
        sb.append(str2);
        sb.append(bVar.b());
        PWLog.error(str, sb.toString());
        return false;
    }

    @Nullable
    private File c(b bVar) {
        PWLog.noise("[InApp]InAppDownloader", "Start download: " + bVar.a());
        EventBus.sendEvent(new a(a.EnumC0007a.DOWNLOADING_ZIP, bVar));
        File a2 = this.b.a();
        if (a2 == null) {
            return null;
        }
        File a3 = e.a(bVar.b(), new File(a2, bVar.a() + ".zip"));
        if (a3 == null) {
            return null;
        }
        EventBus.sendEvent(new a(a.EnumC0007a.DOWNLOADED_ZIP, bVar));
        return a3;
    }

    @WorkerThread
    public a a(List<b> list) {
        a aVar;
        a aVar2;
        if (list == null || list.isEmpty()) {
            return a.a();
        }
        ArrayList<b> arrayList = new ArrayList(list);
        Collections.sort(arrayList);
        this.d.addAll(arrayList);
        synchronized (this.a) {
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            ArrayList arrayList3 = new ArrayList();
            for (b bVar : arrayList) {
                if (b(bVar)) {
                    arrayList2.add(bVar);
                    PWLog.info("[InApp]InAppDownloader", bVar.a() + " deployed");
                    aVar2 = new a(a.EnumC0007a.DEPLOYED, bVar);
                } else {
                    arrayList3.add(bVar);
                    aVar2 = new a(a.EnumC0007a.DEPLOY_FAILED, bVar);
                }
                EventBus.sendEvent(aVar2);
                this.d.remove(bVar);
            }
            aVar = new a(arrayList2, arrayList3);
        }
        return aVar;
    }

    public void a(String str) {
        synchronized (this.a) {
            b(str);
        }
    }

    public boolean a(b bVar) {
        return this.d.contains(bVar);
    }
}
