package com.pushwoosh.appevents;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/* access modifiers changed from: package-private */
public class b implements Application.ActivityLifecycleCallbacks {
    private Handler a = new Handler();
    private a b;
    private int c;
    private String d;

    public interface a {
        void a(String str, String str2);
    }

    b(@NonNull a aVar) {
        this.b = aVar;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a() {
        this.a.removeCallbacksAndMessages(null);
        this.a.postDelayed(c.a(this), 100);
    }

    @RequiresApi(api = 26)
    private void a(Activity activity) {
        activity.getFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            /* class com.pushwoosh.appevents.b.AnonymousClass2 */

            public void onFragmentStarted(FragmentManager fragmentManager, Fragment fragment) {
                super.onFragmentStarted(fragmentManager, fragment);
                b.this.a();
            }
        }, true);
    }

    private void a(FragmentActivity fragmentActivity) {
        fragmentActivity.getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            /* class com.pushwoosh.appevents.b.AnonymousClass1 */

            @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
            public void onFragmentStarted(androidx.fragment.app.FragmentManager fragmentManager, androidx.fragment.app.Fragment fragment) {
                super.onFragmentStarted(fragmentManager, fragment);
                b.this.a();
            }
        }, true);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (activity instanceof FragmentActivity) {
            a((FragmentActivity) activity);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            a(activity);
        }
        a();
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        this.d = activity.getClass().getName();
        if (this.c == 0) {
            this.b.a("ApplicationOpened", this.d);
        }
        this.c++;
    }

    public void onActivityStopped(Activity activity) {
        this.c--;
        if (this.c == 0) {
            this.b.a("ApplicationClosed", this.d);
        }
    }
}
