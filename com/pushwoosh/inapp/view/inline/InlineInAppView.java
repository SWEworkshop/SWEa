package com.pushwoosh.inapp.view.inline;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.R;
import com.pushwoosh.inapp.view.f;
import com.pushwoosh.inapp.view.i;
import com.pushwoosh.inapp.view.inline.c;
import java.util.ArrayList;
import java.util.List;

public class InlineInAppView extends f {
    private String e;
    private boolean f;
    private c g;
    private boolean h;
    private k i;
    private c j;
    private List<InlineInAppViewListener> k = new ArrayList();

    /* access modifiers changed from: package-private */
    public class a implements com.pushwoosh.inapp.view.c {
        a() {
        }

        @Override // com.pushwoosh.inapp.view.c
        public void c() {
            InlineInAppView.this.setState(c.CLOSED);
        }

        @Override // com.pushwoosh.inapp.view.c
        public void d() {
            if (InlineInAppView.this.g == c.LOADING) {
                InlineInAppView.this.setState(c.LOADED);
            }
        }

        @Override // com.pushwoosh.inapp.view.c
        public int e() {
            return 0;
        }
    }

    /* access modifiers changed from: package-private */
    public static class b extends View.BaseSavedState {
        public static final Parcelable.Creator<b> CREATOR = new Parcelable.Creator<b>() {
            /* class com.pushwoosh.inapp.view.inline.InlineInAppView.b.AnonymousClass1 */

            /* renamed from: a */
            public b createFromParcel(Parcel parcel) {
                return new b(parcel);
            }

            /* renamed from: a */
            public b[] newArray(int i) {
                return new b[i];
            }
        };
        boolean a;
        c.a b;

        private b(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt() != 1 ? false : true;
            this.b = new c.a(parcel);
        }

        b(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a ? 1 : 0);
            this.b.a(parcel);
        }
    }

    /* access modifiers changed from: package-private */
    public enum c {
        LOADING,
        LOADED,
        RENDERED,
        CLOSED
    }

    public InlineInAppView(@NonNull Context context) {
        super(context);
        h();
    }

    public InlineInAppView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.InlineInAppView);
        this.e = obtainStyledAttributes.getString(R.styleable.InlineInAppView_identifier);
        this.f = obtainStyledAttributes.getBoolean(R.styleable.InlineInAppView_disableLayoutAnimation, false);
        obtainStyledAttributes.recycle();
        h();
    }

    private void h() {
        b bVar = new b(this);
        this.i = Build.VERSION.SDK_INT < 19 ? new e(this, bVar) : new j(this, bVar);
        this.j = new c(this, com.pushwoosh.inapp.b.c());
        this.g = c.LOADING;
        this.j.a(this.e);
    }

    private void i() {
        this.j.b();
        if (!this.k.isEmpty()) {
            for (InlineInAppViewListener inlineInAppViewListener : this.k) {
                if (inlineInAppViewListener != null) {
                    inlineInAppViewListener.onInlineInAppLoaded();
                }
            }
        }
    }

    private void j() {
        if (!this.k.isEmpty()) {
            for (InlineInAppViewListener inlineInAppViewListener : this.k) {
                if (inlineInAppViewListener != null) {
                    inlineInAppViewListener.onInlineInAppViewClosed();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.f
    public WebView a() {
        return new WebView(getContext()) {
            /* class com.pushwoosh.inapp.view.inline.InlineInAppView.AnonymousClass1 */

            public void computeScroll() {
            }

            /* access modifiers changed from: protected */
            public void onLayout(boolean z, int i, int i2, int i3, int i4) {
                super.onLayout(z, i, i2, i3, i4);
                InlineInAppView.this.i.a(z, i, i2, i3, i4);
            }

            public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
                return false;
            }

            public void scrollTo(int i, int i2) {
            }
        };
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.f
    @NonNull
    public FrameLayout.LayoutParams a(com.pushwoosh.inapp.e.b.a aVar, int i2) {
        return new FrameLayout.LayoutParams(-2, -2);
    }

    /* access modifiers changed from: package-private */
    public void a(com.pushwoosh.inapp.e.b.b bVar) {
        i iVar = new i(new a(), bVar);
        this.b.setWebViewClient(iVar);
        iVar.a(this);
        iVar.a(this.b);
    }

    public void addInlineInAppViewListener(InlineInAppViewListener inlineInAppViewListener) {
        if (inlineInAppViewListener != null) {
            this.k.add(inlineInAppViewListener);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.f
    public void b() {
        super.b();
        this.b.setScrollContainer(false);
        this.b.setVerticalScrollBarEnabled(false);
        this.b.setHorizontalScrollBarEnabled(false);
    }

    /* access modifiers changed from: package-private */
    public void b(com.pushwoosh.inapp.d.a aVar) {
        a(aVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.f
    public void e() {
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public FrameLayout getContainer() {
        return this.a;
    }

    public String getIdentifier() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public c getState() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public WebView getWebView() {
        return this.b;
    }

    public boolean isLayoutAnimationDisabled() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.i.a(configuration);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        this.h = View.MeasureSpec.getMode(i2) == 1073741824 && View.MeasureSpec.getMode(i3) == 1073741824;
        super.onMeasure(i2, i3);
        this.i.a(i2, i3, a.a(this, i2, i3));
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof b) {
            b bVar = (b) parcelable;
            if (bVar.a) {
                this.g = c.CLOSED;
            }
            this.j.a(bVar.b);
            parcelable = bVar.getSuperState();
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Parcelable onSaveInstanceState() {
        b bVar = new b(super.onSaveInstanceState());
        bVar.a = this.g == c.CLOSED;
        bVar.b = this.j.c();
        return bVar;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (!this.k.isEmpty()) {
            for (InlineInAppViewListener inlineInAppViewListener : this.k) {
                if (inlineInAppViewListener != null) {
                    inlineInAppViewListener.onInlineInAppViewChangedSize(i2, i3);
                }
            }
        }
    }

    public void removeInlineInAppViewListener(InlineInAppViewListener inlineInAppViewListener) {
        if (inlineInAppViewListener != null) {
            this.k.remove(inlineInAppViewListener);
        }
    }

    public void setDisableLayoutAnimation(boolean z) {
        this.f = z;
    }

    public void setIdentifier(String str) {
        String str2 = this.e;
        if (str2 == null) {
            if (str == null) {
                return;
            }
        } else if (str2.equals(str)) {
            return;
        }
        this.e = str;
        this.j.a(str);
    }

    /* access modifiers changed from: package-private */
    public void setState(c cVar) {
        if (cVar != this.g) {
            this.g = cVar;
            if (cVar == c.RENDERED) {
                i();
            }
            if (cVar == c.CLOSED) {
                j();
            }
            this.i.a(cVar);
        }
    }
}
