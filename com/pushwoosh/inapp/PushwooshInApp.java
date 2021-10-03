package com.pushwoosh.inapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.exception.MergeUserException;
import com.pushwoosh.exception.PostEventException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.inapp.a.k;
import com.pushwoosh.q;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.tags.TagsBundle;

public class PushwooshInApp {
    private static PushwooshInApp a = new PushwooshInApp();
    private final c b;

    private PushwooshInApp() {
        c cVar;
        if (q.d() != null) {
            cVar = q.d().h();
        } else {
            q.a();
            cVar = null;
        }
        this.b = cVar;
    }

    @NonNull
    public static PushwooshInApp getInstance() {
        return a;
    }

    public void addJavascriptInterface(@NonNull Object obj, @NonNull String str) {
        c cVar = this.b;
        if (cVar != null) {
            cVar.a(obj, str);
        }
    }

    @Nullable
    public String getUserId() {
        if (this.b != null) {
            return RepositoryModule.getRegistrationPreferences().userId().get();
        }
        return null;
    }

    public void mergeUserId(@NonNull String str, @NonNull String str2, boolean z, @Nullable Callback<Void, MergeUserException> callback) {
        c cVar = this.b;
        if (cVar != null) {
            cVar.a(str, str2, z, callback);
        }
    }

    public void postEvent(@NonNull String str) {
        c cVar = this.b;
        if (cVar != null) {
            cVar.a(str, (TagsBundle) null, (Callback<Void, PostEventException>) null);
        }
    }

    public void postEvent(@NonNull String str, TagsBundle tagsBundle) {
        c cVar = this.b;
        if (cVar != null) {
            cVar.a(str, tagsBundle, (Callback<Void, PostEventException>) null);
        }
    }

    public void postEvent(@NonNull String str, @Nullable TagsBundle tagsBundle, Callback<Void, PostEventException> callback) {
        c cVar = this.b;
        if (cVar != null) {
            cVar.a(str, tagsBundle, callback);
        }
    }

    public void registerJavascriptInterface(@NonNull String str, @NonNull String str2) {
        c cVar = this.b;
        if (cVar != null) {
            cVar.a(str, str2);
        }
    }

    public void removeJavascriptInterface(@NonNull String str) {
        c cVar = this.b;
        if (cVar != null) {
            cVar.b(str);
        }
    }

    public void resetBusinessCasesFrequencyCapping() {
        k j;
        q d = q.d();
        if (d != null && (j = d.j()) != null) {
            j.a();
        }
    }

    public void setUserId(@NonNull String str) {
        c cVar = this.b;
        if (cVar != null) {
            cVar.a(str);
        }
    }
}
