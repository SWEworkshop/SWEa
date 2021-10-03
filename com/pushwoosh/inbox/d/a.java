package com.pushwoosh.inbox.d;

import android.os.AsyncTask;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inbox.exception.InboxMessagesException;

/* access modifiers changed from: package-private */
public class a<T> extends AsyncTask<Void, Void, T> {
    private final s<T> a;
    private final Callback<T, InboxMessagesException> b;

    public a(s<T> sVar, Callback<T, InboxMessagesException> callback) {
        this.a = sVar;
        this.b = callback;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public T doInBackground(Void... voidArr) {
        return this.a.a();
    }

    /* access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(T t) {
        super.onPostExecute(t);
        Callback<T, InboxMessagesException> callback = this.b;
        if (callback != null) {
            callback.process(Result.fromData(t));
        }
    }
}
