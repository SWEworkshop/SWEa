package com.pushwoosh.inapp.c;

import android.net.Uri;
import androidx.annotation.WorkerThread;
import com.pushwoosh.inapp.a;
import com.pushwoosh.inapp.f.c;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.e;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class b {
    private final c a;
    private final a b;

    public b(c cVar) {
        this.a = cVar;
        this.b = new a(cVar);
    }

    private String a(String str, Map<String, String> map) throws IOException {
        String b2 = e.b(this.a.c(str));
        try {
            b2 = a(b2, Pattern.compile("\\{\\{(.+?)\\|(.+?)\\|(.*?)\\}\\}", 32), this.b.a(str));
            return a(b2, Pattern.compile("\\{(.+?)\\|(.+?)\\|(.*?)\\}"), map);
        } catch (Exception e) {
            PWLog.warn("[InApp]ResourceMapper", "Failed to process html: " + e.getMessage());
            return b2;
        }
    }

    private String a(String str, Pattern pattern, Map<String, String> map) {
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            if (matcher.groupCount() != 3) {
                PWLog.warn("[InApp]ResourceMapper", "Incorrect matching count");
            } else {
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                String group3 = matcher.group(3);
                PWLog.noise("[InApp]ResourceMapper", "Key: \"" + group + "\", Type: \"" + group2 + "\", Default Value: \"" + group3 + "\"");
                if (map.containsKey(group)) {
                    group3 = a.a(map.get(group), group2);
                }
                String group4 = matcher.group(0);
                str = str.replace(group4, group3);
                PWLog.debug("[InApp]ResourceMapper", "Replacing \"" + group4 + "\" with \"" + group3 + "\"");
            }
        }
        return str;
    }

    @WorkerThread
    public com.pushwoosh.inapp.d.a a(com.pushwoosh.inapp.e.b.b bVar) throws IOException {
        return new com.pushwoosh.inapp.d.a(bVar.a(), Uri.fromFile(this.a.a(bVar.a())).toString(), a(bVar.a(), bVar.h()));
    }
}
