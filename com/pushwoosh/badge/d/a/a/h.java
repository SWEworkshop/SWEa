package com.pushwoosh.badge.d.a.a;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import com.pushwoosh.badge.d.a.a;
import com.pushwoosh.badge.d.a.b;
import java.util.Arrays;
import java.util.List;

public class h implements a {
    @Override // com.pushwoosh.badge.d.a.a
    public List<String> a() {
        return Arrays.asList("com.teslacoilsw.launcher");
    }

    @Override // com.pushwoosh.badge.d.a.a
    public void a(Context context, ComponentName componentName, int i) throws b {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tag", componentName.getPackageName() + "/" + componentName.getClassName());
        contentValues.put("count", Integer.valueOf(i));
        context.getContentResolver().insert(Uri.parse("content://com.teslacoilsw.notifier/unread_count"), contentValues);
    }
}
