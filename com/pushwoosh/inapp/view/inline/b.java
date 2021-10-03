package com.pushwoosh.inapp.view.inline;

import android.animation.LayoutTransition;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import androidx.annotation.RequiresApi;
import com.pushwoosh.inapp.view.inline.InlineInAppView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

class b {
    private InlineInAppView a;
    private ArrayList<WeakReference<ViewGroup>> b = new ArrayList<>();

    /* access modifiers changed from: private */
    public static class a extends LayoutTransition {
        private a() {
        }
    }

    public b(InlineInAppView inlineInAppView) {
        this.a = inlineInAppView;
    }

    @RequiresApi(api = 16)
    private void a(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != this.a && (childAt instanceof ViewGroup)) {
                ViewGroup viewGroup2 = (ViewGroup) childAt;
                if (viewGroup2.getLayoutTransition() == null) {
                    viewGroup2.setLayoutTransition(c());
                    this.b.add(new WeakReference<>(viewGroup2));
                    a(viewGroup2);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b() {
        Iterator<WeakReference<ViewGroup>> it = this.b.iterator();
        while (it.hasNext()) {
            WeakReference<ViewGroup> next = it.next();
            if (next.get() != null && (next.get().getLayoutTransition() instanceof a)) {
                next.get().setLayoutTransition(null);
            }
        }
        this.b.clear();
    }

    @RequiresApi(api = 16)
    private LayoutTransition c() {
        a aVar = new a();
        aVar.setDuration(300);
        aVar.enableTransitionType(4);
        return aVar;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d() {
        float f = 1.0f;
        this.a.getContainer().setAlpha(this.a.getState() == InlineInAppView.c.CLOSED ? 1.0f : 0.0f);
        ViewPropertyAnimator animate = this.a.getContainer().animate();
        if (this.a.getState() == InlineInAppView.c.CLOSED) {
            f = 0.0f;
        }
        animate.alpha(f).setDuration(300).start();
    }

    public void a() {
        if (this.a.g()) {
            d();
        } else if (!this.a.isLayoutAnimationDisabled() && Build.VERSION.SDK_INT >= 16 && (this.a.getParent() instanceof ViewGroup)) {
            final ViewGroup viewGroup = (ViewGroup) this.a.getParent();
            if (viewGroup.getLayoutTransition() == null) {
                LayoutTransition c = c();
                c.addTransitionListener(new LayoutTransition.TransitionListener() {
                    /* class com.pushwoosh.inapp.view.inline.b.AnonymousClass1 */

                    public void endTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
                        if (view == b.this.a) {
                            viewGroup.setLayoutTransition(null);
                            b.this.b();
                        }
                    }

                    public void startTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
                        if (view == b.this.a) {
                            b.this.d();
                        }
                    }
                });
                viewGroup.setLayoutTransition(c);
                ViewParent viewParent = viewGroup;
                while (viewParent.getParent() instanceof ViewGroup) {
                    viewParent = viewParent.getParent();
                }
                a(viewParent);
            }
        }
    }
}
