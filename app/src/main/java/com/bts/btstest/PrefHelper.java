package com.bts.btstest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.Gson;

public class PrefHelper {
    public static SharedPreferences getPref(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void saveToPref(Context context, String key, String val) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, val).apply();
    }

    public static void saveObjectToPref(Context context, String key, Object val) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, new Gson().toJson(val)).apply();
    }

    public static void clearAll(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply();
    }

    public static void remove(Context context, String key) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().remove(key).apply();
    }

    public static String getPref(Context context, String key) {
        if (PreferenceManager.getDefaultSharedPreferences(context).contains(key)) {
            return PreferenceManager.getDefaultSharedPreferences(context).getString(key, null);
        } else {
            return null;
        }
    }

    public static <T> T getObject(Context context, String key, Class<T> a) {
        String gson = PrefHelper.getPref(context, key);
        if (gson == null) {
            return null;
        } else {
            try {
                return new Gson().fromJson(gson, a);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object storaged with key " + key + " is instanceof other class");
            }
        }
    }

    public static boolean isPrefNotEmpty(Context context, String string) {
        return !TextUtils.isEmpty(getPref(context).getString(string, null));
    }
}
