package com.pushwoosh.inapp.view;

import android.content.Context;
import android.content.Intent;
import com.pushwoosh.inapp.e.b.b;

public class RemoteUrlActivity extends WebActivity {
    private String a;

    public static void a(Context context, String str) {
        Intent intent = new Intent(context, RemoteUrlActivity.class);
        a(intent, new b(str), "", 2);
        context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.WebActivity
    public void a(b bVar, String str, int i) {
        if (i == 2) {
            this.a = bVar.b();
        } else {
            c();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.WebActivity
    public void a(f fVar) {
        if (fVar != null) {
            fVar.a(this.a);
            setContentView(fVar);
        }
    }
}
