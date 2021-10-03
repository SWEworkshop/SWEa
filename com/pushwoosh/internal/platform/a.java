package com.pushwoosh.internal.platform;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import java.util.Date;

public class a {
    private Date a;
    private final Application b;

    /* renamed from: com.pushwoosh.internal.platform.a$a  reason: collision with other inner class name */
    public static class C0012a implements Event {
        C0012a() {
        }
    }

    a(Context context) {
        this.b = (Application) context.getApplicationContext();
    }

    public void a(boolean z) {
        if (z) {
            EventBus.sendEvent(new C0012a());
            this.a = new Date();
        }
        this.b.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            /* class com.pushwoosh.internal.platform.a.AnonymousClass1 */
            private int b;

            public void onActivityCreated(Activity activity, Bundle bundle) {
                if (a.this.a != null) {
                    if (new Date().getTime() - a.this.a.getTime() >= 60000) {
                        EventBus.sendEvent(new C0012a());
                    }
                    a.this.a = null;
                } else if (this.b == 0) {
                    EventBus.sendEvent(new C0012a());
                }
                this.b++;
            }

            public void onActivityDestroyed(Activity activity) {
                this.b--;
            }

            public void onActivityPaused(Activity activity) {
            }

            public void onActivityResumed(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
            }
        });
    }
}
