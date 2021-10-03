package com.pushwoosh.inbox.internal.a;

import com.pushwoosh.inbox.c.a;
import com.pushwoosh.inbox.internal.data.b;
import com.pushwoosh.internal.utils.PWLog;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    public static void a(b bVar) {
        b bVar2;
        switch (a.a(bVar.j())) {
            case PLAIN:
                bVar2 = new d();
                break;
            case RICH_MEDIA:
                bVar2 = new f();
                break;
            case URL:
                bVar2 = new g();
                break;
            case DEEP_LINK:
                bVar2 = new a();
                break;
            case REMOTE_URL:
                bVar2 = new e();
                break;
            default:
                PWLog.error("Unknown inbox message type: " + bVar.i());
                return;
        }
        try {
            bVar2.a(new JSONObject(bVar.j()));
        } catch (JSONException e) {
            PWLog.error("Action params is invalid for inbox: " + bVar.a(), e);
        }
    }
}
