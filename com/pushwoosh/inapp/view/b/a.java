package com.pushwoosh.inapp.view.b;

import android.content.Context;
import android.os.AsyncTask;
import com.pushwoosh.inapp.f.c;
import com.pushwoosh.inapp.view.RichMediaWebActivity;
import com.pushwoosh.internal.utils.PWLog;
import java.lang.ref.WeakReference;

/* access modifiers changed from: package-private */
public class a implements e {
    private final Context a;
    private final c b;

    /* access modifiers changed from: private */
    /* renamed from: com.pushwoosh.inapp.view.b.a$a  reason: collision with other inner class name */
    public interface AbstractC0009a {
        void a();
    }

    private static class b extends AsyncTask<Void, Void, Boolean> {
        private final WeakReference<a> a;
        private final com.pushwoosh.inapp.e.b.b b;
        private final AbstractC0009a c;

        public b(a aVar, com.pushwoosh.inapp.e.b.b bVar, AbstractC0009a aVar2) {
            this.a = new WeakReference<>(aVar);
            this.b = bVar;
            this.c = aVar2;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Boolean doInBackground(Void... voidArr) {
            return Boolean.valueOf(this.a.get() != null ? this.a.get().b.d(this.b.a()) : false);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (!bool.booleanValue() || this.a.get() == null) {
                this.c.a();
                return;
            }
            Context context = this.a.get().a;
            context.startActivity(RichMediaWebActivity.a(context, this.b));
        }
    }

    a(Context context, c cVar) {
        this.a = context;
        this.b = cVar;
    }

    static /* synthetic */ void a(a aVar, com.pushwoosh.inapp.e.b.b bVar) {
        if (aVar.b.d(bVar.a())) {
            Context context = aVar.a;
            context.startActivity(RichMediaWebActivity.a(context, bVar));
            return;
        }
        PWLog.noise("[InApp]InAppDefaultViewStrategy", "resource is not downloaded, abort show inApp");
    }

    @Override // com.pushwoosh.inapp.view.b.e
    public void a(com.pushwoosh.inapp.e.b.b bVar) {
        if (bVar == null) {
            PWLog.noise("[InApp]InAppDefaultViewStrategy", "resource is empty");
        } else {
            new b(this, bVar, b.a(this, bVar)).execute(new Void[0]);
        }
    }
}
