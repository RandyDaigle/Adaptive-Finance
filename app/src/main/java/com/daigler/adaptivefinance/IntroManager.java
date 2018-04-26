package com.daigler.adaptivefinance;


import android.content.Context;
import android.content.SharedPreferences;

public class IntroManager {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    public IntroManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("first", 0);
        editor = preferences.edit();
    }

    public void setFirstTimeRunning(Boolean isFirstTimeRunning) {
        editor.putBoolean("isFirstTimeRunning", isFirstTimeRunning);
        editor.commit();
    }

    public boolean FirstTime() {
        return preferences.getBoolean("isFirstTimeRunning", true);
    }
}
