package com.pushwoosh.thirdpart.com.ironz.binaryprefs;

import android.content.SharedPreferences;
import java.util.Map;

public interface e extends SharedPreferences {
    f a();

    @Override // android.content.SharedPreferences
    @Deprecated
    Map<String, ?> getAll();
}
