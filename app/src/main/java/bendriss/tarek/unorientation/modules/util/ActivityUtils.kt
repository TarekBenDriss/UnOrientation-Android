package bendriss.tarek.unorientation.modules.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import bendriss.tarek.unorientation.modules.base.BaseActivity
import bendriss.tarek.unorientation.modules.base.BaseFragment
import java.util.logging.Logger


/**
 * this class reperents an utils for activities
 */
class ActivityUtils {
    /**
     * this function commits a fragment
     * @param activity
     * @param layout
     * @param clazz
     * @param <T>
    </T> */
    @SuppressLint("ResourceType")
    fun <T : BaseFragment?> commitFragment(@NonNull activity: BaseActivity?, @IdRes layout: Int, @NonNull clazz: Class<T>?) {
        if (activity != null && !activity.isFinishing() && layout >= 0 && clazz != null && StringUtils.isNotEmpty(clazz.name)) {
            try {
                val fragmentManager: FragmentManager = activity.getSupportFragmentManager()
                if (fragmentManager != null) {
                    val fragment: Fragment? = clazz.newInstance()
                    if (fragment != null) {
                        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
                        transaction.replace(layout, fragment, clazz.name)
                        transaction.commitAllowingStateLoss()
                    }
                }
            } catch (exp: Exception) {
                Log.e("CommitFragmentException",exp.message);
                //Logger.error(this, exp)
            }
        }
    }

    companion object {
        fun getManager(context: Context?): FragmentManager? {
            return if (context != null) {
                try {
                    (context as BaseActivity).getSupportFragmentManager()
                } catch (error: Exception) {
                    null
                }
            } else null
        }

        /**
         * this function hides the status bar
         * @param activity
         */
        @JvmStatic
        fun hideStatusBar(activity: Activity) { // Hide the Action Bar
            getWindow(activity).setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        /**
         * this function shows the status bar
         * @param activity
         */
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        fun showStatusBar(activity: Activity) {
            getWindow(activity).setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
        }

        /**
         * this function returns the activity's window
         * @param activity
         * @return
         */
        fun getWindow(@NonNull activity: Activity): Window {
            return activity.window
        }

        /**
         * this function returns the activity's root view
         * @param activity
         * @return
         */
        fun getRootView(@NonNull activity: Activity): View {
            return getWindow(activity).decorView.rootView
        }


    }
}
