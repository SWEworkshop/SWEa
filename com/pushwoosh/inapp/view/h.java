package com.pushwoosh.inapp.view;

final /* synthetic */ class h implements Runnable {
    private final RichMediaWebActivity a;

    private h(RichMediaWebActivity richMediaWebActivity) {
        this.a = richMediaWebActivity;
    }

    public static Runnable a(RichMediaWebActivity richMediaWebActivity) {
        return new h(richMediaWebActivity);
    }

    public void run() {
        RichMediaWebActivity.a(this.a);
    }
}
