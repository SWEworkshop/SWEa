package com.pushwoosh.inapp.view.b;

import android.content.Context;
import androidx.annotation.Nullable;
import com.pushwoosh.inapp.e.b.b;
import com.pushwoosh.inapp.view.RichMediaWebActivity;
import com.pushwoosh.internal.utils.PWLog;

class c implements e {
    private Context a;

    c(Context context) {
        this.a = context;
    }

    @Override // com.pushwoosh.inapp.view.b.e
    public void a(@Nullable b bVar) {
        if (bVar == null) {
            PWLog.noise("InAppRequiredViewStrategy", "resource is empty");
            return;
        }
        Context context = this.a;
        context.startActivity(RichMediaWebActivity.a(context, bVar));
    }
}
