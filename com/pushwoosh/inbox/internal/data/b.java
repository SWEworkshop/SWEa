package com.pushwoosh.inbox.internal.data;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.inbox.data.InboxMessageType;
import com.pushwoosh.internal.utils.JsonUtils;
import com.pushwoosh.internal.utils.PWLog;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements Serializable, Comparable<b> {
    private final String a;
    private final long b;
    private final long c;
    private final long d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final InboxMessageType i;
    private final String j;
    private final String k;
    private final InboxMessageStatus l;
    private final InboxMessageSource m;

    public static class a {
        private String a;
        private long b;
        private long c;
        private long d;
        private String e;
        private String f;
        private String g;
        private String h;
        private InboxMessageType i;
        private String j;
        private String k;
        private InboxMessageStatus l;
        private InboxMessageSource m;

        public a a(long j2) {
            this.b = j2;
            return this;
        }

        public a a(Bundle bundle) {
            this.a = com.pushwoosh.inbox.c.a.a(bundle);
            this.f = com.pushwoosh.inbox.c.a.b(bundle);
            this.g = com.pushwoosh.inbox.c.a.d(bundle);
            try {
                JSONObject jSONObject = new JSONObject(com.pushwoosh.inbox.c.a.e(bundle));
                if (jSONObject.has("image")) {
                    this.h = jSONObject.getString("image");
                }
                if (jSONObject.has("rt")) {
                    this.c = jSONObject.getLong("rt");
                }
            } catch (JSONException e2) {
                PWLog.error("Problem with parsing inboxParams", e2);
            }
            this.d = TimeUnit.MILLISECONDS.toSeconds(com.pushwoosh.inbox.c.a.c(bundle));
            this.i = com.pushwoosh.inbox.c.a.f(bundle);
            this.j = JsonUtils.bundleToJsonWithUserData(bundle).toString();
            this.e = com.pushwoosh.inbox.c.a.g(bundle);
            this.l = InboxMessageStatus.DELIVERED;
            this.m = InboxMessageSource.PUSH;
            return this;
        }

        public a a(InboxMessage inboxMessage) {
            this.a = inboxMessage.getCode();
            this.c = -1;
            this.d = inboxMessage.getSendDate().getTime();
            this.f = inboxMessage.getTitle();
            this.g = inboxMessage.getMessage();
            this.h = inboxMessage.getImageUrl();
            this.i = inboxMessage.getType();
            this.l = inboxMessage.isActionPerformed() ? InboxMessageStatus.OPEN : inboxMessage.isRead() ? InboxMessageStatus.READ : InboxMessageStatus.DELIVERED;
            this.j = "";
            this.m = InboxMessageSource.SERVICE;
            this.e = "";
            return this;
        }

        public a a(InboxMessageType inboxMessageType) {
            this.i = inboxMessageType;
            return this;
        }

        public a a(InboxMessageSource inboxMessageSource) {
            this.m = inboxMessageSource;
            return this;
        }

        public a a(InboxMessageStatus inboxMessageStatus) {
            this.l = inboxMessageStatus;
            return this;
        }

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a a(JSONObject jSONObject) throws JSONException, C0011b {
            if (!jSONObject.has("inbox_id") || !jSONObject.has("order") || !jSONObject.has("rt") || !jSONObject.has("text") || !jSONObject.has("action_type") || !jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                throw new C0011b();
            }
            this.a = jSONObject.getString("inbox_id");
            this.b = jSONObject.getLong("order");
            this.c = jSONObject.getLong("rt");
            this.g = jSONObject.getString("text");
            this.i = InboxMessageType.getByCode(jSONObject.getInt("action_type"));
            this.l = InboxMessageStatus.getByCode(jSONObject.getInt(NotificationCompat.CATEGORY_STATUS));
            if (jSONObject.has("send_date")) {
                this.d = jSONObject.getLong("send_date");
            }
            if (jSONObject.has("title")) {
                this.f = jSONObject.getString("title");
            }
            if (jSONObject.has("image")) {
                this.h = jSONObject.getString("image");
            }
            if (jSONObject.has("action_params")) {
                this.j = jSONObject.getString("action_params");
                g(this.j);
            }
            if (jSONObject.has("hash")) {
                this.e = jSONObject.getString("hash");
            }
            this.m = InboxMessageSource.SERVICE;
            return this;
        }

        public b a() {
            return new b(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m);
        }

        public a b(long j2) {
            this.c = j2;
            return this;
        }

        public a b(String str) {
            this.e = str;
            return this;
        }

        public a c(long j2) {
            this.d = j2;
            return this;
        }

        public a c(String str) {
            this.f = str;
            return this;
        }

        public a d(String str) {
            this.g = str;
            return this;
        }

        public a e(String str) {
            this.h = str;
            return this;
        }

        public a f(String str) {
            this.j = str;
            g(str);
            return this;
        }

        public a g(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.has("b")) {
                        this.k = jSONObject.get("b").toString();
                    }
                } catch (JSONException unused) {
                }
            }
            return this;
        }
    }

    /* renamed from: com.pushwoosh.inbox.internal.data.b$b  reason: collision with other inner class name */
    public static class C0011b extends Exception {
    }

    private b(String str, long j2, long j3, long j4, String str2, String str3, String str4, String str5, InboxMessageType inboxMessageType, String str6, String str7, InboxMessageStatus inboxMessageStatus, InboxMessageSource inboxMessageSource) {
        this.a = str;
        this.b = j2;
        this.c = j3;
        this.d = j4;
        this.e = str2;
        this.f = str3;
        this.g = str4;
        this.h = str5;
        this.i = inboxMessageType;
        this.j = str6;
        this.k = str7;
        this.l = inboxMessageStatus;
        this.m = inboxMessageSource;
    }

    /* renamed from: a */
    public int compareTo(@NonNull b bVar) {
        String str;
        int compare = this.m.compare(bVar.m);
        if (compare == 0) {
            compare = Long.valueOf(this.b).compareTo(Long.valueOf(bVar.b));
        }
        if (compare == 0) {
            compare = Long.valueOf(this.d).compareTo(Long.valueOf(bVar.d));
        }
        if (compare == 0) {
            String str2 = this.f;
            if (str2 != null && (str = bVar.f) != null) {
                compare = str2.compareTo(str);
            } else if (this.f == null && bVar.f != null) {
                compare = 1;
            } else if (this.f != null) {
                compare = -1;
            }
        }
        return compare == 0 ? this.a.compareTo(bVar.a) : compare;
    }

    public String a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public long d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        String str = this.a;
        return str != null ? str.equals(bVar.a) : bVar.a == null;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public String h() {
        return this.h;
    }

    public int hashCode() {
        String str = this.a;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public InboxMessageType i() {
        return this.i;
    }

    public String j() {
        return this.j;
    }

    public String k() {
        return this.k;
    }

    public InboxMessageStatus l() {
        return this.l;
    }

    public InboxMessageSource m() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public boolean n() {
        switch (this.l) {
            case DELIVERED:
                return false;
            case READ:
            case OPEN:
                return true;
            case DELETED_BY_USER:
            case DELETED_FROM_SERVICE:
            default:
                return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean o() {
        switch (this.l) {
            case DELIVERED:
            case READ:
                return false;
            case OPEN:
                return true;
            case DELETED_BY_USER:
            case DELETED_FROM_SERVICE:
            default:
                return false;
        }
    }

    public boolean p() {
        switch (this.l) {
            case DELIVERED:
            case READ:
            case OPEN:
                return false;
            case DELETED_BY_USER:
            case DELETED_FROM_SERVICE:
                return true;
            default:
                return false;
        }
    }

    public String q() {
        if (TextUtils.isEmpty(this.j)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.j);
            if (jSONObject.has("md")) {
                return jSONObject.get("md").toString();
            }
        } catch (JSONException e2) {
            PWLog.error(e2.getMessage());
        }
        return null;
    }
}
