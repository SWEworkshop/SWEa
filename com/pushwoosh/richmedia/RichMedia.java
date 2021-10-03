package com.pushwoosh.richmedia;

import com.pushwoosh.inapp.view.b.a.b;
import com.pushwoosh.internal.utils.PWLog;

public class RichMedia {
    private final String a = RichMedia.class.getSimpleName();
    private String b;
    private Source c;
    private b d;
    private boolean e;
    private boolean f;

    public enum Source {
        PushMessageSource,
        InAppSource
    }

    RichMedia(b bVar) {
        Source source;
        this.d = bVar;
        com.pushwoosh.inapp.e.b.b b2 = bVar.b();
        this.e = this.d.d();
        if (b2 == null) {
            PWLog.error(this.a, "resource is empty");
            return;
        }
        this.f = b2.f();
        this.e = this.d.d();
        switch (this.d.e()) {
            case IN_APP:
                this.b = b2.a();
                source = Source.InAppSource;
                break;
            case RICH_MEDIA:
                this.b = b2.a();
                source = Source.PushMessageSource;
                break;
            case REMOTE_URL:
                throw new IllegalArgumentException("ResourceType can not equals REMOTE URL");
            default:
                return;
        }
        this.c = source;
    }

    /* access modifiers changed from: package-private */
    public b a() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RichMedia richMedia = (RichMedia) obj;
        return this.b.equals(richMedia.b) && this.c == richMedia.c;
    }

    public String getContent() {
        return this.b;
    }

    public Source getSource() {
        return this.c;
    }

    public int hashCode() {
        return (this.b.hashCode() * 31) + this.c.hashCode();
    }

    public boolean isLockScreen() {
        return this.e;
    }

    public boolean isRequired() {
        return this.f;
    }

    public String toString() {
        return "RichMedia{content='" + this.b + '\'' + ", resourceType=" + this.c + '}';
    }
}
