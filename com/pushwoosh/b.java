package com.pushwoosh;

import android.content.Context;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

final /* synthetic */ class b implements Callback {
    private final Context a;

    private b(Context context) {
        this.a = context;
    }

    public static Callback a(Context context) {
        return new b(context);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(Result result) {
        DeepLinkActivity.a(this.a, result);
    }
}
