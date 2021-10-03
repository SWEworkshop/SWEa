package com.pushwoosh.badge.d.a.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.pushwoosh.badge.d.a.a;
import com.pushwoosh.badge.d.a.b;
import java.util.Arrays;
import java.util.List;

public class l implements a {
    @Override // com.pushwoosh.badge.d.a.a
    public List<String> a() {
        return Arrays.asList("com.vivo.launcher");
    }

    @Override // com.pushwoosh.badge.d.a.a
    public void a(Context context, ComponentName componentName, int i) throws b {
        Intent intent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
        intent.putExtra("packageName", context.getPackageName());
        intent.putExtra("className", componentName.getClassName());
        intent.putExtra("notificationNum", i);
        context.sendBroadcast(intent);
    }
}
