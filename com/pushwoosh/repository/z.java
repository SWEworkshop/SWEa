package com.pushwoosh.repository;

import androidx.annotation.NonNull;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.utils.JsonUtils;
import com.pushwoosh.internal.utils.a;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class z implements a.AbstractC0017a<a> {
    private com.pushwoosh.internal.utils.a<a> a = new com.pushwoosh.internal.utils.a<>(this, 1000);

    public static class a {
        private final JSONObject a;
        private final Callback<Void, PushwooshException> b;

        a(JSONObject jSONObject, Callback<Void, PushwooshException> callback) {
            this.a = jSONObject;
            this.b = callback;
        }

        @NonNull
        public JSONObject a() {
            return this.a;
        }

        public Callback<Void, PushwooshException> b() {
            return this.b;
        }
    }

    static /* synthetic */ void a(z zVar, List list, Result result) {
        if (result.isSuccess()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar.b() != null) {
                    aVar.b().process(Result.fromData(null));
                }
            }
            return;
        }
        zVar.a(list, (NetworkException) result.getException());
    }

    private void a(List<a> list, NetworkException networkException) {
        RepositoryModule.getRegistrationPreferences().setTagsFailed().set(true);
        for (a aVar : list) {
            if (aVar.b() != null) {
                aVar.b().process(Result.fromException(networkException));
            }
        }
    }

    @Override // com.pushwoosh.internal.utils.a.AbstractC0017a
    public void a(List<a> list) {
        JSONObject jSONObject = new JSONObject();
        for (a aVar : list) {
            JsonUtils.mergeJson(aVar.a(), jSONObject);
        }
        RequestManager requestManager = NetworkModule.getRequestManager();
        if (requestManager == null) {
            a(list, new NetworkException("Request manager is null"));
        } else {
            requestManager.sendRequest(new ac(jSONObject), aa.a(this, list));
        }
    }

    public void a(@NonNull JSONObject jSONObject, Callback<Void, PushwooshException> callback) {
        this.a.a(new a(jSONObject, callback));
    }
}
