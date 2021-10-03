package com.pushwoosh.inapp.e;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.pushwoosh.exception.MergeUserException;
import com.pushwoosh.exception.PostEventException;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.a.k;
import com.pushwoosh.inapp.c.b;
import com.pushwoosh.inapp.event.a;
import com.pushwoosh.inapp.f.d;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.Subscription;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.q;
import com.pushwoosh.repository.RegistrationPrefs;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.tags.TagsBundle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public class c {
    @Nullable
    private RequestManager a;
    private final d b;
    private final com.pushwoosh.inapp.e.a.c c;
    private final b d;
    private final b e;
    private final AtomicBoolean f = new AtomicBoolean(false);
    private final RegistrationPrefs g;

    public c(@Nullable RequestManager requestManager, d dVar, com.pushwoosh.inapp.e.a.c cVar, b bVar, com.pushwoosh.inapp.f.c cVar2, RegistrationPrefs registrationPrefs) {
        this.a = requestManager;
        this.b = dVar;
        this.c = cVar;
        this.e = bVar;
        this.g = registrationPrefs;
        this.d = new b(dVar, cVar2);
        EventBus.subscribe(com.pushwoosh.inapp.view.d.class, d.a(requestManager));
    }

    static /* synthetic */ void a(@Nullable Callback callback, Result result) {
        Result fromException;
        if (callback != null) {
            if (result.isSuccess()) {
                fromException = Result.fromData(null);
            } else if (result.getException() != null) {
                fromException = Result.fromException(new MergeUserException(((NetworkException) result.getException()).getMessage()));
            } else {
                return;
            }
            callback.process(fromException);
        }
    }

    static /* synthetic */ void a(com.pushwoosh.inapp.e.b.b bVar, a.EnumC0007a[] aVarArr, CountDownLatch countDownLatch, a aVar) {
        if (aVar == null) {
            return;
        }
        if ((aVar.b().equals(a.EnumC0007a.DEPLOY_FAILED) || aVar.b().equals(a.EnumC0007a.DEPLOYED)) && aVar.a().equals(bVar.a())) {
            aVarArr[0] = aVar.b();
            countDownLatch.countDown();
        }
    }

    private void a(List<com.pushwoosh.inapp.e.b.b> list) {
        boolean z;
        Iterator<com.pushwoosh.inapp.e.b.b> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            String k = it.next().k();
            if (k != null && !k.isEmpty()) {
                z = true;
                break;
            }
        }
        this.g.gdprEnable().set(z);
    }

    @WorkerThread
    private com.pushwoosh.inapp.e.a.a b(List<com.pushwoosh.inapp.e.b.b> list) {
        ArrayList arrayList = new ArrayList();
        for (com.pushwoosh.inapp.e.b.b bVar : list) {
            if (!this.d.a(bVar)) {
                arrayList.add(bVar);
            }
        }
        return arrayList.isEmpty() ? com.pushwoosh.inapp.e.a.a.a() : this.c.a(arrayList);
    }

    static /* synthetic */ void b(@Nullable Callback callback, Result result) {
        if (callback != null) {
            j jVar = (j) result.getData();
            if (jVar != null) {
                callback.process(Result.fromData((jVar.a() != null || !jVar.c()) ? jVar.a() : new com.pushwoosh.inapp.e.b.b(jVar.b(), jVar.c())));
                return;
            }
            NetworkException networkException = (NetworkException) result.getException();
            if (networkException != null) {
                callback.process(Result.fromException(new PostEventException(networkException.getMessage())));
                PWLog.warn("[InApp]InAppRepository", networkException.getMessage(), networkException);
            }
        }
    }

    private boolean b(com.pushwoosh.inapp.e.b.b bVar) {
        if (!this.d.a(bVar)) {
            return this.c.a(bVar) ? c(bVar) : !this.c.a(Collections.singletonList(bVar)).b().isEmpty();
        }
        return true;
    }

    private boolean c(com.pushwoosh.inapp.e.b.b bVar) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        a.EnumC0007a[] aVarArr = {a.EnumC0007a.DEPLOY_FAILED};
        Subscription subscribe = EventBus.subscribe(a.class, e.a(bVar, aVarArr, countDownLatch));
        try {
            countDownLatch.await();
            subscribe.unsubscribe();
            return aVarArr[0].equals(a.EnumC0007a.DEPLOYED);
        } catch (InterruptedException e2) {
            PWLog.error("Deploy interrupted", e2);
            return false;
        }
    }

    private boolean d() {
        if (this.a != null) {
            return true;
        }
        this.a = NetworkModule.getRequestManager();
        return this.a != null;
    }

    private boolean e() throws Exception {
        PWLog.noise("Wait until getInApps finished");
        int i = 0;
        while (!this.f.get() && i < 25) {
            Thread.sleep(200);
            i++;
        }
        if (this.f.get()) {
            return true;
        }
        throw new TimeoutException("InApp wait timeout");
    }

    @WorkerThread
    public Result<Void, NetworkException> a() {
        PushwooshException networkException;
        Result<Void, NetworkException> result;
        try {
            a aVar = new a();
            if (d()) {
                if (this.a != null) {
                    Result sendRequestSync = this.a.sendRequestSync(aVar);
                    List<com.pushwoosh.inapp.e.b.b> list = (List) sendRequestSync.getData();
                    if (!sendRequestSync.isSuccess()) {
                        networkException = sendRequestSync.getException();
                        result = Result.fromException(networkException);
                        return result;
                    }
                    if (list != null && !list.isEmpty()) {
                        ArrayList<String> arrayList = new ArrayList();
                        arrayList.addAll(this.b.a(list));
                        k.a(list);
                        for (String str : arrayList) {
                            this.c.a(str);
                        }
                        a(list);
                        b(list);
                    }
                    result = Result.fromData(null);
                    return result;
                }
            }
            networkException = new NetworkException("Request Manager is null");
            result = Result.fromException(networkException);
            return result;
        } finally {
            this.f.set(true);
        }
    }

    @WorkerThread
    public Result<com.pushwoosh.inapp.d.a, com.pushwoosh.inapp.b.a> a(com.pushwoosh.inapp.e.b.b bVar) {
        PWLog.noise("mapToHtmlData for resource " + bVar.a() + " inApp is required: " + bVar.f() + " inAppLoaded: " + this.f.get());
        if (bVar.i()) {
            try {
                if (this.f.get() || (bVar.f() && e())) {
                    com.pushwoosh.inapp.e.b.b a2 = this.b.a(bVar.a());
                    if (a2 != null) {
                        bVar = a2;
                    } else {
                        return Result.fromException(new com.pushwoosh.inapp.b.a(String.format("Rich media with code %s does not exist.", bVar.a())));
                    }
                }
            } catch (Exception e2) {
                return Result.fromException(new com.pushwoosh.inapp.b.a(String.format("Can't download or update richMedia: %s", bVar.a()), e2));
            }
        }
        if (this.d.a(bVar) || b(bVar)) {
            try {
                return Result.fromData(this.e.a(bVar));
            } catch (IOException e3) {
                return Result.fromException(new com.pushwoosh.inapp.b.a(String.format("Can't mapping resource %s to htmlData", bVar.a()), e3));
            }
        } else {
            return Result.fromException(new com.pushwoosh.inapp.b.a("Can't download or update richMedia: " + bVar.a()));
        }
    }

    public void a(String str) {
        RequestManager requestManager;
        k kVar = new k(str);
        if (d() && (requestManager = this.a) != null) {
            requestManager.sendRequest(kVar, new com.pushwoosh.function.a(kVar, RepositoryModule.getRequestStorage()));
        }
    }

    public void a(String str, TagsBundle tagsBundle, @Nullable Callback<com.pushwoosh.inapp.e.b.b, PostEventException> callback) {
        RequestManager requestManager;
        i iVar = new i(str, q.d().g().a(), tagsBundle);
        if (d() && (requestManager = this.a) != null) {
            requestManager.sendRequest(iVar, f.a(callback));
        } else if (callback != null) {
            callback.process(Result.fromException(new PostEventException("Request Manager is null")));
        }
    }

    public void a(String str, String str2, boolean z, @Nullable Callback<Void, MergeUserException> callback) {
        RequestManager requestManager;
        h hVar = new h(str, str2, z);
        if (d() && (requestManager = this.a) != null) {
            requestManager.sendRequest(hVar, g.a(callback));
        } else if (callback != null) {
            callback.process(Result.fromException(new MergeUserException("Request Manager is null")));
        }
    }

    @WorkerThread
    public Result<com.pushwoosh.inapp.e.b.b, com.pushwoosh.inapp.b.a> b(String str) {
        try {
            com.pushwoosh.inapp.e.b.b a2 = com.pushwoosh.inapp.e.b.b.a(str);
            if (b(a2)) {
                return Result.fromData(a2);
            }
            return Result.fromException(new com.pushwoosh.inapp.b.a("Can't download or update richMedia: " + a2.a()));
        } catch (com.pushwoosh.inapp.b.a e2) {
            return Result.fromException(e2);
        }
    }

    public com.pushwoosh.inapp.e.b.b b() {
        return this.b.a();
    }

    public com.pushwoosh.inapp.e.b.b c() {
        return this.b.b();
    }
}
