package com.pushwoosh.inapp.f;

import android.content.Context;
import androidx.annotation.Nullable;
import java.io.File;

public class a implements c {
    @Nullable
    private final Context a;

    public a(@Nullable Context context) {
        this.a = context;
    }

    @Override // com.pushwoosh.inapp.f.c
    public File a() {
        Context context = this.a;
        if (context == null) {
            return null;
        }
        return context.getCacheDir();
    }

    @Override // com.pushwoosh.inapp.f.c
    public File a(String str) {
        Context context = this.a;
        if (context == null) {
            return null;
        }
        return new File(context.getDir("htmls", 0), str);
    }

    @Override // com.pushwoosh.inapp.f.c
    public File b(String str) {
        File a2 = a(str);
        if (a2 == null) {
            return null;
        }
        return new File(a2, "pushwoosh.json");
    }

    @Override // com.pushwoosh.inapp.f.c
    public File c(String str) {
        File a2 = a(str);
        if (a2 == null) {
            return null;
        }
        return new File(a2, "index.html");
    }

    @Override // com.pushwoosh.inapp.f.c
    public boolean d(String str) {
        File a2 = a(str);
        return a2 != null && a2.exists();
    }
}
