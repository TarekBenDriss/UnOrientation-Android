package bendriss.tarek.unorientation.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.Secure;

import androidx.annotation.Nullable;

/**
 * this class represents application's utils
 */
public class ApplicationUtils {

    public static final String NAME = "AVkkzer";
    public static final String PACKAGE = "digitu.com.movies";

    /**
     * this function returns the app package info
     * @param context
     * @return
     */
    public static PackageInfo getPackageInfo(@Nullable Context context) {
        PackageInfo packageInfo;
        if (context != null) {
            try {
                packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                packageInfo = null;
            }
        } else {
            packageInfo = null;
        }
        return packageInfo;
    }

    /**
     * this function returns the app version
     * @param context
     * @return
     */
    public static String getVersion(@Nullable Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo != null ? packageInfo.versionName : null;
    }

    /**
     * this function returns the device id
     * @param context
     * @return
     */
    public static String getDeviceID(@Nullable Context context) {
        return context != null ? Secure.getString(context.getContentResolver(), Secure.ANDROID_ID) : null;
    }
}
