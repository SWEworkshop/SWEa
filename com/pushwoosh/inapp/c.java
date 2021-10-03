package com.pushwoosh.inapp;

import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.exception.MergeUserException;
import com.pushwoosh.exception.PostEventException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.view.b.a.b;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.q;
import com.pushwoosh.repository.RegistrationPrefs;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.tags.TagsBundle;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class c {
    private final RegistrationPrefs a = RepositoryModule.getRegistrationPreferences();
    private final Map<String, Object> b = new HashMap();
    private final Map<String, String> c = new HashMap();
    private final com.pushwoosh.inapp.e.c d = b.c();
    private g e;

    /* access modifiers changed from: private */
    public interface a {
        void a();
    }

    private static class b extends d {
        public b(c cVar, a aVar) {
            super(cVar, aVar);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public com.pushwoosh.inapp.e.b.b doInBackground(Void... voidArr) {
            if (this.a.get() != null) {
                return ((c) this.a.get()).d.b();
            }
            return null;
        }
    }

    /* renamed from: com.pushwoosh.inapp.c$c  reason: collision with other inner class name */
    private static class AsyncTaskC0006c extends d {
        public AsyncTaskC0006c(c cVar, a aVar) {
            super(cVar, aVar);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public com.pushwoosh.inapp.e.b.b doInBackground(Void... voidArr) {
            if (this.a.get() != null) {
                return ((c) this.a.get()).d.c();
            }
            return null;
        }
    }

    private static abstract class d extends AsyncTask<Void, Void, com.pushwoosh.inapp.e.b.b> {
        protected final WeakReference<c> a;
        private final a b;

        public d(c cVar, a aVar) {
            this.a = new WeakReference<>(cVar);
            this.b = aVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(com.pushwoosh.inapp.e.b.b bVar) {
            super.onPostExecute(bVar);
            if (bVar == null || this.a.get() == null) {
                this.b.a();
            } else {
                this.a.get().a((c) bVar);
            }
        }
    }

    public c(g gVar) {
        this.e = gVar;
    }

    static /* synthetic */ void a(@Nullable c cVar, Callback callback, Result result) {
        if (result.isSuccess()) {
            com.pushwoosh.inapp.e.b.b bVar = (com.pushwoosh.inapp.e.b.b) result.getData();
            if (callback != null) {
                callback.process(Result.fromData(null));
            }
            if (bVar != null) {
                if (cVar.a.communicationEnable().get()) {
                    cVar.a(bVar);
                } else {
                    PWLog.error("[InApp]PushwooshInApp", "cant show inApp because all communication disable");
                }
            }
        } else {
            if (callback != null) {
                callback.process(Result.fromException(result.getException()));
            }
            PWLog.warn("[InApp]PushwooshInApp", result.getException() == null ? "" : ((PostEventException) result.getException()).getMessage(), result.getException());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    public void a(com.pushwoosh.inapp.e.b.b bVar) {
        if (bVar == null) {
            PWLog.error("[InApp]PushwooshInApp", "resource is null, can not finds resource");
            return;
        }
        com.pushwoosh.inapp.view.b.a.b a2 = new b.a().a(bVar).a();
        com.pushwoosh.richmedia.a i = q.d().i();
        if (i != null) {
            i.a(a2);
        }
    }

    public void a() {
        this.e.a();
    }

    public void a(@NonNull Object obj, @NonNull String str) {
        this.b.put(str, obj);
    }

    public void a(@NonNull String str) {
        if (!TextUtils.equals(str, this.a.userId().get())) {
            this.a.userId().set(str);
            this.d.a(str);
        }
    }

    public void a(@NonNull String str, @Nullable TagsBundle tagsBundle, @Nullable Callback<Void, PostEventException> callback) {
        this.d.a(str, tagsBundle, d.a(this, callback));
    }

    public void a(@NonNull String str, @NonNull String str2) {
        this.c.put(str2, str);
    }

    public void a(@NonNull String str, @NonNull String str2, boolean z, @Nullable Callback<Void, MergeUserException> callback) {
        this.d.a(str, str2, z, callback);
    }

    public Map<String, Object> b() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.b);
        for (Map.Entry<String, String> entry : this.c.entrySet()) {
            String key = entry.getKey();
            try {
                Object newInstance = Class.forName(entry.getValue()).newInstance();
                if (newInstance != null) {
                    hashMap.put(key, newInstance);
                }
            } catch (Exception e2) {
                PWLog.warn("[InApp]PushwooshInApp", "Failed to instantiate javascript interface for " + key, e2);
            }
        }
        return hashMap;
    }

    public void b(@NonNull String str) {
        this.b.remove(str);
    }

    public void c() {
        new b(this, e.a(this)).execute(new Void[0]);
    }

    public void d() {
        new AsyncTaskC0006c(this, f.a(this)).execute(new Void[0]);
    }
}
