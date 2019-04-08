package com.example.supplychain.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtils {
    public static final String user_name =  "user_name";
    public static final String user_email  = "user_email";
    public static final String user_dcorg   =  "user_dcorg";
    public static final String orgTypeId = "orgTypeId";
    public static final String orgTypeName  = "orgTypeName";
    public static final String siteCode = "siteCode";
    public static final String user_auth = "user_auth";
    public static final String token_string = "token_string";
    public static void saveToPrefs(Context context, String key, String value){
        SharedPreferences prefs         = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key,value);
        editor.commit();

    }
    public static String getFromPrefs(Context context, String key, String defaultValue){
        SharedPreferences preferences   =       PreferenceManager.getDefaultSharedPreferences(context);
        try{
            return preferences.getString(key,defaultValue);
        }catch (Exception e){
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static void removePrefs(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putString(token_string,"");
        editor.putString(user_name,"");
        editor.putString(user_email,"");
        editor.putString(user_dcorg,"");
        editor.putString(orgTypeId,"");
        editor.putString(orgTypeName,"");
        editor.putString(siteCode,"");
        editor.putString(user_auth,"");
        editor.commit();
    }
}