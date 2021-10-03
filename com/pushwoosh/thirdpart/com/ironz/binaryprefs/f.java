package com.pushwoosh.thirdpart.com.ironz.binaryprefs;

import android.content.SharedPreferences;
import java.util.Set;

public interface f extends SharedPreferences.Editor {
    f a(String str, float f);

    f a(String str, int i);

    f a(String str, long j);

    f a(String str, String str2);

    f a(String str, Set<String> set);

    f a(String str, boolean z);
}
