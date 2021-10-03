package com.pushwoosh.inapp.e.a;

import androidx.core.util.Pair;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.e;
import java.io.File;

/* access modifiers changed from: package-private */
public class b {
    b() {
    }

    public boolean a(Pair<File, com.pushwoosh.inapp.e.b.b> pair) {
        F f = pair.first;
        S s = pair.second;
        if (f == null || s == null) {
            PWLog.noise("[InApp]FileHashChecker", "incorrect state of arguments");
            return false;
        }
        String c = s.c();
        if (c == null || c.isEmpty()) {
            PWLog.noise("[InApp]FileHashChecker", "Hash is empty for " + s.b());
            return true;
        }
        String c2 = e.c(f);
        PWLog.noise("[InApp]FileHashChecker", "Resource hash " + c + ", file hash " + c2);
        return c.equals(c2);
    }
}
