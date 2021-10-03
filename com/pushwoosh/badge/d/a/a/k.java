package com.pushwoosh.badge.d.a.a;

import android.content.AsyncQueryHandler;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import com.pushwoosh.badge.d.a.a;
import com.pushwoosh.badge.d.a.b;
import java.util.Arrays;
import java.util.List;

public class k implements a {
    private final Uri a = Uri.parse("content://com.sonymobile.home.resourceprovider/badge");
    private AsyncQueryHandler b;

    private ContentValues a(int i, ComponentName componentName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("badge_count", Integer.valueOf(i));
        contentValues.put("package_name", componentName.getPackageName());
        contentValues.put("activity_name", componentName.getClassName());
        return contentValues;
    }

    private void a(ContentValues contentValues) {
        this.b.startInsert(0, null, this.a, contentValues);
    }

    private void a(Context context, ContentValues contentValues) {
        context.getApplicationContext().getContentResolver().insert(this.a, contentValues);
    }

    private static boolean a(Context context) {
        return context.getPackageManager().resolveContentProvider("com.sonymobile.home.resourceprovider", 0) != null;
    }

    private static void b(Context context, ComponentName componentName, int i) {
        Intent intent = new Intent("com.sonyericsson.home.action.UPDATE_BADGE");
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", componentName.getPackageName());
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", componentName.getClassName());
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", String.valueOf(i));
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", i > 0);
        context.sendBroadcast(intent);
    }

    private void c(Context context, ComponentName componentName, int i) {
        if (i >= 0) {
            ContentValues a2 = a(i, componentName);
            if (Looper.myLooper() == Looper.getMainLooper()) {
                if (this.b == null) {
                    this.b = new AsyncQueryHandler(context.getApplicationContext().getContentResolver()) {
                        /* class com.pushwoosh.badge.d.a.a.k.AnonymousClass1 */
                    };
                }
                a(a2);
                return;
            }
            a(context, a2);
        }
    }

    @Override // com.pushwoosh.badge.d.a.a
    public List<String> a() {
        return Arrays.asList("com.sonyericsson.home", "com.sonymobile.home");
    }

    @Override // com.pushwoosh.badge.d.a.a
    public void a(Context context, ComponentName componentName, int i) throws b {
        if (a(context)) {
            c(context, componentName, i);
        } else {
            b(context, componentName, i);
        }
    }
}
