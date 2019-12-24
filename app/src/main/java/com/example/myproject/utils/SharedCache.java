package com.example.myproject.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.MenuItem;

public class SharedCache {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String TEXT="text";
    public static MenuItem loginItem;
    public static MenuItem logoutItem;
    public static MenuItem accountItem;
    public static MenuItem addItem;

    public SharedCache(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
}
