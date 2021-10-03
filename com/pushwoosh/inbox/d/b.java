package com.pushwoosh.inbox.d;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inbox.b.b.c;
import com.pushwoosh.inbox.b.b.d;
import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.inbox.exception.InboxMessagesException;
import com.pushwoosh.inbox.internal.data.InboxMessageSource;
import com.pushwoosh.inbox.internal.data.InboxMessageStatus;
import com.pushwoosh.internal.command.CommandApplayer;
import com.pushwoosh.internal.command.CommandParams;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.notification.builder.NotificationBuilderManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.LongCompanionObject;

public class b {
    private final RequestManager a;
    private final com.pushwoosh.inbox.e.a b;
    private final CommandApplayer c;
    private final com.pushwoosh.inbox.internal.b.b d = new com.pushwoosh.inbox.internal.b.b();
    private final com.pushwoosh.inbox.b.a.a e = new com.pushwoosh.inbox.b.a.a(com.pushwoosh.inbox.internal.a.a());
    private t<Integer> f;
    private t<Integer> g;
    private t<Integer> h;
    private v<Integer> i;
    private v<Integer> j;
    private v<Integer> k;
    private final Handler l = new Handler(Looper.getMainLooper());

    /* access modifiers changed from: private */
    public static class a extends AsyncTask<Void, Void, Collection<InboxMessage>> {
        private WeakReference<b> a;
        @Nullable
        private Callback<Collection<InboxMessage>, InboxMessagesException> b;
        @Nullable
        private Result<com.pushwoosh.inbox.d.a.a, NetworkException> c;
        private long d;
        private int e;

        private a(b bVar, @Nullable Callback<Collection<InboxMessage>, InboxMessagesException> callback, @Nullable Result<com.pushwoosh.inbox.d.a.a, NetworkException> result, long j, int i) {
            this.a = new WeakReference<>(bVar);
            this.b = callback;
            this.c = result;
            this.d = j;
            this.e = i;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Collection<InboxMessage> doInBackground(Void... voidArr) {
            b bVar = this.a.get();
            if (bVar == null) {
                return null;
            }
            return bVar.b((b) bVar.b.a(this.d, this.e));
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Collection<InboxMessage> collection) {
            super.onPostExecute(collection);
            Result<com.pushwoosh.inbox.d.a.a, NetworkException> result = this.c;
            InboxMessagesException inboxMessagesException = null;
            if (!(result == null || result.getException() == null)) {
                inboxMessagesException = new InboxMessagesException("Can't load inboxList", this.c.getException());
            }
            Callback<Collection<InboxMessage>, InboxMessagesException> callback = this.b;
            if (callback != null) {
                callback.process(Result.from(collection, inboxMessagesException));
            }
        }
    }

    public b(RequestManager requestManager, com.pushwoosh.inbox.e.a aVar, CommandApplayer commandApplayer) {
        this.a = requestManager;
        this.b = aVar;
        this.c = commandApplayer;
        c();
        d();
    }

    private long a(InboxMessage inboxMessage) {
        return inboxMessage == null ? LongCompanionObject.MAX_VALUE : ((com.pushwoosh.inbox.internal.data.a) inboxMessage).a().b();
    }

    static /* synthetic */ Result a(b bVar, Result result) {
        return Result.from(Integer.valueOf(bVar.b.d()), result.getException() == null ? null : new InboxMessagesException("Can't update total count of the inboxMessages", result.getException()));
    }

    private com.pushwoosh.inbox.e.a.a a(Collection<com.pushwoosh.inbox.internal.data.b> collection, boolean z) {
        com.pushwoosh.inbox.e.a.a a2 = this.b.a(collection, z);
        for (Map.Entry<String, InboxMessageStatus> entry : a2.e().entrySet()) {
            a(entry.getKey(), entry.getValue(), true);
        }
        return a2;
    }

    private Collection<InboxMessage> a(Collection<String> collection) {
        return b(this.b.b(collection));
    }

    private void a(Result<com.pushwoosh.inbox.d.a.a, NetworkException> result) {
        this.f.a(result);
        this.g.a(result);
        this.h.a(result);
    }

    private void a(c cVar) {
        if (cVar.a() != null) {
            this.b.a(cVar.a());
        }
    }

    @WorkerThread
    private void a(com.pushwoosh.inbox.d.a.a aVar) {
        if (aVar == null) {
            return;
        }
        if (aVar != com.pushwoosh.inbox.d.a.a.a || !aVar.d()) {
            Pair<Collection<com.pushwoosh.inbox.internal.data.b>, Collection<String>> c2 = c(aVar.b());
            ((Collection) c2.second).addAll(aVar.c());
            this.l.post(f.a(new com.pushwoosh.inbox.event.a().a(a(aVar.a())).b(b((Collection) c2.first)).c((Collection) c2.second).a()));
        }
    }

    static /* synthetic */ void a(b bVar, Map map, @Nullable boolean z, Callback callback) {
        boolean z2 = false;
        for (Map.Entry entry : map.entrySet()) {
            boolean z3 = !bVar.b.a((String) entry.getKey(), (InboxMessageStatus) entry.getValue()).isEmpty();
            if (z3) {
                bVar.l.post(h.a(bVar));
                z2 = true;
            }
            if (z3) {
                bVar.a((String) entry.getKey(), (InboxMessageStatus) entry.getValue(), z);
            }
        }
        Pair<Collection<com.pushwoosh.inbox.internal.data.b>, Collection<String>> c2 = bVar.c(map.keySet());
        for (com.pushwoosh.inbox.internal.data.b bVar2 : (Collection) c2.first) {
            if (callback != null) {
                bVar.l.post(i.a(bVar.d.a(bVar2), callback));
            }
        }
        if (z2) {
            bVar.l.post(j.a(bVar, c2));
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    static /* synthetic */ void a(b bVar, @Nullable boolean z, Callback callback) {
        Result<com.pushwoosh.inbox.d.a.a, NetworkException> result;
        com.pushwoosh.inbox.d.a.a aVar;
        c cVar;
        if (bVar.e.check() || z) {
            bVar.e.b();
            try {
                Collection<com.pushwoosh.inbox.internal.data.b> a2 = bVar.b.a();
                Result sendRequestSync = bVar.a.sendRequestSync(new com.pushwoosh.inbox.b.b.b());
                if (sendRequestSync.isSuccess() && (cVar = (c) sendRequestSync.getData()) != null) {
                    bVar.a(cVar);
                    com.pushwoosh.inbox.e.a.a a3 = bVar.a((Collection<com.pushwoosh.inbox.internal.data.b>) cVar.b(), true);
                    a3.c().addAll(cVar.a());
                    for (com.pushwoosh.inbox.internal.data.b bVar2 : a2) {
                        bVar.a(Collections.singletonMap(bVar2.a(), bVar2.l()), false, (Callback<InboxMessage, InboxMessagesException>) null);
                    }
                    aVar = new com.pushwoosh.inbox.d.a.a(a3.d(), a3.f(), cVar.a());
                    bVar.a(aVar);
                } else if (sendRequestSync.getException() != null) {
                    result = Result.fromException(sendRequestSync.getException());
                    bVar.e.c();
                    bVar.l.post(g.a(bVar));
                    bVar.a(result);
                    if (callback == null) {
                        callback.process(result);
                        return;
                    }
                    return;
                } else {
                    aVar = com.pushwoosh.inbox.d.a.a.a;
                }
                result = Result.fromData(aVar);
                bVar.e.c();
                bVar.l.post(g.a(bVar));
                bVar.a(result);
                if (callback == null) {
                }
            } catch (Throwable th) {
                bVar.e.c();
                throw th;
            }
        } else {
            bVar.a(Result.fromData(com.pushwoosh.inbox.d.a.a.a));
            if (callback != null) {
                callback.process(Result.fromData(null));
            }
        }
    }

    static /* synthetic */ void a(InboxMessage inboxMessage, @Nullable Callback callback) {
        callback.process(inboxMessage == null ? Result.fromException(new InboxMessagesException("Unknown inbox")) : Result.fromData(inboxMessage));
    }

    private void a(String str, InboxMessageStatus inboxMessageStatus, boolean z) {
        com.pushwoosh.inbox.internal.data.b a2 = this.b.a(str);
        if (a2 != null) {
            if (Build.VERSION.SDK_INT >= 23) {
                NotificationBuilderManager.removeInboxNotificationFromStatusBar(a2.a());
            } else {
                NotificationBuilderManager.removeInboxNotification(a2.a());
            }
        }
        if (a2 != null && a2.m() == InboxMessageSource.SERVICE) {
            this.a.sendRequest(new d(a2.b(), inboxMessageStatus, a2.e()));
        }
        if (z && inboxMessageStatus == InboxMessageStatus.OPEN) {
            a(a2.e(), a2.q());
        }
    }

    private void a(String str, String str2) {
        this.c.applyCommand(e.a(), new CommandParams(new Pair(str, str2)));
    }

    private void a(boolean z, @Nullable Callback<com.pushwoosh.inbox.d.a.a, NetworkException> callback) {
        if (!this.e.a()) {
            NetworkModule.execute(d.a(this, z, callback));
        } else if (callback != null) {
            callback.process(Result.fromData(null));
        }
    }

    static /* synthetic */ Result b(b bVar, Result result) {
        return Result.from(Integer.valueOf(bVar.b.b()), result.getException() == null ? null : new InboxMessagesException("Can't update count of the unread inboxMessages", result.getException()));
    }

    static /* synthetic */ String b() {
        return "pushStat";
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @NonNull
    private Collection<InboxMessage> b(@Nullable Collection<com.pushwoosh.inbox.internal.data.b> collection) {
        if (collection == null || collection.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (com.pushwoosh.inbox.internal.data.b bVar : collection) {
            arrayList.add(this.d.a(bVar));
        }
        return arrayList;
    }

    private Pair<Collection<com.pushwoosh.inbox.internal.data.b>, Collection<String>> c(@Nullable Collection<String> collection) {
        if (collection == null || collection.isEmpty()) {
            return new Pair<>(new ArrayList(), new ArrayList());
        }
        Collection<com.pushwoosh.inbox.internal.data.b> b2 = this.b.b(collection);
        Iterator<com.pushwoosh.inbox.internal.data.b> it = b2.iterator();
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            com.pushwoosh.inbox.internal.data.b next = it.next();
            if (next.p()) {
                arrayList.add(next.a());
                it.remove();
            }
        }
        return new Pair<>(b2, arrayList);
    }

    static /* synthetic */ Result c(b bVar, Result result) {
        return Result.from(Integer.valueOf(bVar.b.c()), result.getException() == null ? null : new InboxMessagesException("Can't update count of the inboxMessages with no action performed", result.getException()));
    }

    private void c() {
        this.f = new t<>(c.a(this));
        this.g = new t<>(k.a(this));
        this.h = new t<>(l.a(this));
    }

    private void d() {
        com.pushwoosh.inbox.e.a aVar = this.b;
        aVar.getClass();
        this.i = new v<>(m.a(aVar));
        com.pushwoosh.inbox.e.a aVar2 = this.b;
        aVar2.getClass();
        this.j = new v<>(n.a(aVar2));
        com.pushwoosh.inbox.e.a aVar3 = this.b;
        aVar3.getClass();
        this.k = new v<>(o.a(aVar3));
    }

    /* access modifiers changed from: private */
    public void e() {
        this.k.a();
        this.i.a();
        this.j.a();
    }

    public Collection<InboxMessage> a(@Nullable InboxMessage inboxMessage, int i2) throws InboxMessagesException {
        if (inboxMessage == null || (inboxMessage instanceof com.pushwoosh.inbox.internal.data.a)) {
            return b(this.b.a(a(inboxMessage), i2));
        }
        throw new InboxMessagesException("Provided InboxMessage is not instance of InboxMessageImpl");
    }

    public void a() {
        a((Collection<com.pushwoosh.inbox.internal.data.b>) Collections.emptyList(), true);
    }

    public void a(@Nullable Callback<Integer, InboxMessagesException> callback) {
        this.g.a(callback);
        a(false, (Callback<com.pushwoosh.inbox.d.a.a, NetworkException>) null);
    }

    public void a(@Nullable Callback<Collection<InboxMessage>, InboxMessagesException> callback, @Nullable InboxMessage inboxMessage, int i2) throws InboxMessagesException {
        if (inboxMessage == null || (inboxMessage instanceof com.pushwoosh.inbox.internal.data.a)) {
            new a(callback, null, a(inboxMessage), i2).execute(new Void[0]);
            return;
        }
        throw new InboxMessagesException("Provided InboxMessage is not instance of InboxMessageImpl");
    }

    @WorkerThread
    public void a(com.pushwoosh.inbox.internal.data.b bVar) {
        if (bVar.g() != null) {
            com.pushwoosh.inbox.e.a.a a2 = a((Collection<com.pushwoosh.inbox.internal.data.b>) Collections.singleton(bVar), false);
            a(new com.pushwoosh.inbox.d.a.a(a2.d(), a2.f(), a2.c()));
            this.l.post(p.a(this));
            return;
        }
        a(true, (Callback<com.pushwoosh.inbox.d.a.a, NetworkException>) null);
    }

    public void a(String str, InboxMessageStatus inboxMessageStatus, Callback<InboxMessage, InboxMessagesException> callback) {
        a(Collections.singletonMap(str, inboxMessageStatus), false, callback);
    }

    public void a(Map<String, InboxMessageStatus> map, boolean z, @Nullable Callback<InboxMessage, InboxMessagesException> callback) {
        NetworkModule.execute(q.a(this, map, z, callback));
    }

    public void b(@Nullable Callback<Integer, InboxMessagesException> callback) {
        this.j.a(callback);
    }

    public void b(@Nullable Callback<Collection<InboxMessage>, InboxMessagesException> callback, @Nullable InboxMessage inboxMessage, int i2) {
        if (inboxMessage == null || (inboxMessage instanceof com.pushwoosh.inbox.internal.data.a)) {
            a(false, r.a(this, callback, a(inboxMessage), i2));
        } else if (callback != null) {
            callback.process(Result.fromException(new InboxMessagesException("Provided InboxMessage is not instance of InboxMessageImpl")));
        }
    }

    public void c(@Nullable Callback<Integer, InboxMessagesException> callback) {
        this.j.b(callback);
    }

    public void d(@Nullable Callback<Integer, InboxMessagesException> callback) {
        this.f.a(callback);
        a(false, (Callback<com.pushwoosh.inbox.d.a.a, NetworkException>) null);
    }

    public void e(@Nullable Callback<Integer, InboxMessagesException> callback) {
        this.i.a(callback);
    }

    public void f(@Nullable Callback<Integer, InboxMessagesException> callback) {
        this.i.b(callback);
    }

    public void g(Callback<Integer, InboxMessagesException> callback) {
        this.h.a(callback);
        a(false, (Callback<com.pushwoosh.inbox.d.a.a, NetworkException>) null);
    }

    public void h(Callback<Integer, InboxMessagesException> callback) {
        this.k.a(callback);
    }

    public void i(Callback<Integer, InboxMessagesException> callback) {
        this.k.b(callback);
    }
}
