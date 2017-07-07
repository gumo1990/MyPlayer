package Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by whq on 17/7/7 0007.
 * SharePrefrence封装
 */

public class PrefUtils {
    private static final String PREF_NAME = "myplayer";
    private static SharedPreferences sp;

    public static void putBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }
}
