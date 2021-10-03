package com.pushwoosh.internal.platform.b;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.Nullable;

public interface b {
    @Nullable
    Intent a(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter);

    void a(Intent intent, String str);
}
