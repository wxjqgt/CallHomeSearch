package com.weibo.callhomesearch.util;

import android.content.SharedPreferences;

public class SPUtil {

    private static SharedPreferences getShardPreferences() {
        return MainApp.getSp();
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getShardPreferences().edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolean(String key) {
        boolean value = getShardPreferences().getBoolean(key, false);
        return value;
    }

    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = getShardPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(String key) {
        String value = getShardPreferences().getString(key, "default");
        return value;
    }
}
