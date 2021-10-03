package com.pushwoosh.inapp.view;

import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.Nullable;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.e.b.b;
import com.pushwoosh.inapp.e.c;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.platform.utils.GeneralUtils;
import com.pushwoosh.internal.utils.JsonUtils;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RepositoryModule;
import java.util.Map;
import org.json.JSONException;

public class a extends AsyncTask<Void, Void, Result<com.pushwoosh.inapp.d.a, com.pushwoosh.inapp.b.a>> {
    private final b a;
    private final AbstractC0008a b;
    private final c c = com.pushwoosh.inapp.b.c();

    /* renamed from: com.pushwoosh.inapp.view.a$a  reason: collision with other inner class name */
    public interface AbstractC0008a {
        void a();

        void a(Result<com.pushwoosh.inapp.d.a, com.pushwoosh.inapp.b.a> result);
    }

    public a(b bVar, AbstractC0008a aVar) {
        this.a = bVar;
        this.b = aVar;
    }

    private void a(@Nullable Map<String, Object> map) {
        if (map != null) {
            map.put("OS Version", Build.VERSION.RELEASE);
            map.put("Device Model", com.pushwoosh.internal.platform.utils.a.o());
            map.put("Jailbroken", GeneralUtils.isStoreApp() ? "0" : "1");
            com.pushwoosh.inapp.c.a.a(map);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Result<com.pushwoosh.inapp.d.a, com.pushwoosh.inapp.b.a> doInBackground(Void... voidArr) {
        try {
            Map<String, Object> jsonToMap = JsonUtils.jsonToMap(RepositoryModule.getNotificationPreferences().p().get());
            a(jsonToMap);
            this.a.a(jsonToMap);
        } catch (JSONException e) {
            PWLog.error("Failed parse tags", e);
        }
        return this.c.a(this.a);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Result<com.pushwoosh.inapp.d.a, com.pushwoosh.inapp.b.a> result) {
        super.onPostExecute(result);
        if (!result.isSuccess()) {
            EventBus.sendEvent(new com.pushwoosh.inapp.event.c(this.a, result.getException()));
        }
        this.b.a(result);
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        this.b.a();
    }
}
