package com.pushwoosh.badge.d.a.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.pushwoosh.badge.d.a.a;
import java.util.Arrays;
import java.util.List;

public class b implements a {
    @Override // com.pushwoosh.badge.d.a.a
    public List<String> a() {
        return Arrays.asList("com.anddoes.launcher");
    }

    @Override // com.pushwoosh.badge.d.a.a
    public void a(Context context, ComponentName componentName, int i) throws com.pushwoosh.badge.d.a.b {
        Intent intent = new Intent("com.anddoes.launcher.COUNTER_CHANGED");
        intent.putExtra("package", componentName.getPackageName());
        intent.putExtra("count", i);
        intent.putExtra("class", componentName.getClassName());
        if (com.pushwoosh.badge.d.a.b.a.a(context, intent)) {
            context.sendBroadcast(intent);
            return;
        }
        throw new com.pushwoosh.badge.d.a.b("unable to resolve intent: " + intent.toString());
    }
}
