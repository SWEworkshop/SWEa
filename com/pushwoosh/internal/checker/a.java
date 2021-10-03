package com.pushwoosh.internal.checker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a {
    private static final a a = new a();
    private List<Checker> b = new ArrayList();

    public static a a() {
        return a;
    }

    public boolean b() {
        Iterator<Checker> it = this.b.iterator();
        while (true) {
            boolean z = true;
            while (true) {
                if (!it.hasNext()) {
                    return z;
                }
                Checker next = it.next();
                if (!z || !next.check()) {
                    z = false;
                }
            }
        }
    }
}
