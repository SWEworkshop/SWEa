package com.pushwoosh.internal.command;

import android.util.Pair;
import com.pushwoosh.q;
import com.pushwoosh.repository.t;

public class b implements a<Pair<String, String>> {
    @Override // com.pushwoosh.internal.command.a
    public void a(CommandParams<Pair<String, String>> commandParams) {
        Pair<String, String> params = commandParams.getParams();
        String str = (String) params.first;
        t g = q.d().g();
        g.a(str);
        g.a(str, (String) params.second);
    }
}
