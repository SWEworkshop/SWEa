package com.pushwoosh.badge.d.a.a;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import com.pushwoosh.badge.d.a.a;
import com.pushwoosh.badge.d.a.b;
import java.util.Arrays;
import java.util.List;

public class e implements a {
    @Override // com.pushwoosh.badge.d.a.a
    public List<String> a() {
        return Arrays.asList("me.everything.launcher");
    }

    @Override // com.pushwoosh.badge.d.a.a
    public void a(Context context, ComponentName componentName, int i) throws b {
        ContentValues contentValues = new ContentValues();
        contentValues.put("package_name", componentName.getPackageName());
        contentValues.put("activity_name", componentName.getClassName());
        contentValues.put("count", Integer.valueOf(i));
        context.getContentResolver().insert(Uri.parse("content://me.everything.badger/apps"), contentValues);
    }
}
