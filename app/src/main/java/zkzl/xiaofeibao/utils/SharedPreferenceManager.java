package zkzl.xiaofeibao.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;


/**
 * Created by zhcf-01 on 2015/12/3.
 */
public final class SharedPreferenceManager {

    public static final String COM_XIAOFEIBAO_APP = "com.xiaofeibao.app";
    private static SharedPreferenceManager singleInstance = null;

    private SharedPreferenceManager() {
    }

    public static SharedPreferenceManager getSharedPreferenceManager() {
        if (singleInstance == null) {
            synchronized (SharedPreferenceManager.class) {
                if (singleInstance == null) {
                    singleInstance = new SharedPreferenceManager();
                }
            }

        }
        return singleInstance;
    }


    public  void saveString(@NonNull Context context, @NonNull String key, @NonNull String value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(key, value);
        editor.commit();
    }


    public String getString(@NonNull Context context, @NonNull String key) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public void saveInt(@NonNull Context context, @NonNull String key, @NonNull int value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(@NonNull Context context, @NonNull String key) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getInt(key, 0);
    }

    public void saveBoolean(@NonNull Context context, @NonNull String key, @NonNull boolean value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(@NonNull Context context, @NonNull String key){
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getBoolean(key, false);
    }

    public void saveFloat(@NonNull Context context, @NonNull String key, @NonNull float value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putFloat(key, value);
        editor.commit();
    }

    public float getFloat(@NonNull Context context, @NonNull String key) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getFloat(key, 0f);
    }


    public void saveLong(@NonNull Context context, @NonNull String key, @NonNull long value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putLong(key, value);
        editor.commit();
    }

    public long getLong(@NonNull Context context, @NonNull String key){
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getLong(key, 0l);
    }


    public void clear(@NonNull Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.clear();
        editor.commit();
    }

    public void remove(@NonNull Context context, @NonNull String key) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.remove(key);
        editor.commit();
    }

    private SharedPreferences.Editor getEditor(@NonNull Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.edit();
    }

    private SharedPreferences getSharedPreferences(@NonNull Context context) {
        return context.getSharedPreferences(COM_XIAOFEIBAO_APP, Context.MODE_PRIVATE);
    }
}
