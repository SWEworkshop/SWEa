package com.pushwoosh.inapp.view.b.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;

public class b {
    private final com.pushwoosh.inapp.e.b.b a;
    private final String b;
    private final boolean c;
    private final a d;
    private long e;

    public static class a {
        private com.pushwoosh.inapp.e.b.b a;
        private String b = "";
        private boolean c = false;
        private a d = a.IN_APP;
        private long e = 0;

        public a a(long j) {
            this.e = j;
            return this;
        }

        public a a(com.pushwoosh.inapp.e.b.b bVar) {
            this.a = bVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(a aVar) {
            this.d = aVar;
            return this;
        }

        public a a(String str) {
            if (str == null) {
                return this;
            }
            try {
                return a(com.pushwoosh.inapp.e.b.b.a(str)).a(a.RICH_MEDIA);
            } catch (com.pushwoosh.inapp.b.a e2) {
                PWLog.error("Can't parse richMedia: " + str, e2);
                return this;
            }
        }

        public a a(boolean z) {
            this.c = z;
            return this;
        }

        public b a() {
            return new b(this.a, this.b, this.c, this.d, this.e);
        }

        public a b(String str) {
            return str == null ? this : a(new com.pushwoosh.inapp.e.b.b(str)).a(a.REMOTE_URL);
        }

        public a c(String str) {
            this.b = str;
            return this;
        }
    }

    private b(@Nullable com.pushwoosh.inapp.e.b.b bVar, @Nullable String str, boolean z, @NonNull a aVar, long j) {
        this.a = bVar;
        this.b = str;
        this.c = z;
        this.d = aVar;
        this.e = j;
    }

    public long a() {
        return this.e;
    }

    @Nullable
    public com.pushwoosh.inapp.e.b.b b() {
        return this.a;
    }

    @Nullable
    public String c() {
        return this.b;
    }

    public boolean d() {
        return this.c;
    }

    @NonNull
    public a e() {
        return this.d;
    }
}
