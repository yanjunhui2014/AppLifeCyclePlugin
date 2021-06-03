package com.milo.lifecycle.utils;

import java.io.Closeable;
import java.io.Flushable;

/**
 * Title：
 * Describe：
 * Remark：
 * <p>
 * Created by Milo
 * E-Mail : 303767416@qq.com
 * 6/3/21
 */
public class Utils {

    public static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void flush(Flushable flushable) {
        try {
            flushable.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isActivity(String superClassname) {
        final String activityNames[] = new String[]{"android.app.Activity", "android.accounts.AccountAuthenticatorActivity", "android.app.ActivityGroup",
                "android.app.AliasActivity", "androidx.appcompat.app.AppCompatActivity", "androidx.activity.ComponentActivity",
                "androidx.core.app.ComponentActivity", "android.app.ExpandableListActivity", "androidx.fragment.app.FragmentActivity",
                "android.app.LauncherActivity", "android.app.ListActivity", "android.app.NativeActivity", "android.preference.PreferenceActivity"};

        for (String activityName : activityNames) {
            if (activityName.equals(superClassname)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFragment() {
        return false;
    }

}
