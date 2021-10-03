package com.pushwoosh.inbox.ui.presentation.view.adapter.inbox;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.pushwoosh.inbox.ui.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J4\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0016J>\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\t2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\f\u001a\u00020\u0005H\u0016¨\u0006\u0011"}, d2 = {"com/pushwoosh/inbox/ui/presentation/view/adapter/inbox/InboxViewHolder$fillView$requestBuilder$1", "Lcom/bumptech/glide/request/RequestListener;", "Landroid/graphics/drawable/Drawable;", "(Lcom/pushwoosh/inbox/ui/presentation/view/adapter/inbox/InboxViewHolder;)V", "onLoadFailed", "", "e", "Lcom/bumptech/glide/load/engine/GlideException;", "model", "", "target", "Lcom/bumptech/glide/request/target/Target;", "isFirstResource", "onResourceReady", "resource", "dataSource", "Lcom/bumptech/glide/load/DataSource;", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 9})
/* compiled from: InboxViewHolder.kt */
public final class InboxViewHolder$fillView$requestBuilder$1 implements RequestListener<Drawable> {
    final /* synthetic */ InboxViewHolder this$0;

    public boolean onResourceReady(@Nullable Drawable drawable, @Nullable Object obj, @Nullable Target<Drawable> target, @Nullable DataSource dataSource, boolean z) {
        return false;
    }

    /* JADX WARN: Incorrect args count in method signature: ()V */
    InboxViewHolder$fillView$requestBuilder$1(InboxViewHolder inboxViewHolder) {
        this.this$0 = inboxViewHolder;
    }

    @Override // com.bumptech.glide.request.RequestListener
    public boolean onLoadFailed(@Nullable GlideException glideException, @Nullable Object obj, @Nullable Target<Drawable> target, boolean z) {
        View view = this.this$0.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
        ((ImageView) view.findViewById(R.id.inboxImageView)).setImageDrawable(this.this$0.colorSchemeProvider.getDefaultIcon());
        return true;
    }
}
