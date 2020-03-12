package bendriss.tarek.unorientation.modules.util;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import bendriss.tarek.unorientation.R;

/**
 * this class represents alert dialog utils
 */
public class AlertDialogUtils {

    /**
     * this function shows a dialog
     * @param context
     * @param message
     */
    public static void show(@NonNull Context context, String message) {
        showString(context, R.string.empty, message, null);
    }

    /**
     * this function shows a string in a dialog
     * @param context
     * @param title
     * @param message
     * @param listener
     */
    public static void showString(@NonNull Context context, @StringRes int title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //set title alert
        builder.setTitle(title);
        // set message alert
        builder.setMessage(message);
        if (listener != null) {
            builder.setPositiveButton(R.string.ok, listener);
        } else {
            builder.setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss());
        }
        // show alert
        builder.create().show();
    }

    /**
     * this fuction shows a dialog
     * @param context
     * @param message
     * @param listener
     * @param cancelable
     */
    public static void show(@NonNull Context context, @StringRes int message, DialogInterface.OnClickListener listener, boolean cancelable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // set message alert
        builder.setMessage(message);
        // Sets whether this dialog is canceled when touched outside the window's bounds. or not
        builder.setCancelable(cancelable);
        if (listener != null) {
            builder.setPositiveButton(R.string.ok, listener);
        } else {
            builder.setNegativeButton(R.string.ok, (dialog, which) -> dialog.dismiss());
        }
        // show alert
        builder.create().show();

    }

    /**
     * this function returns a dialog
     * @param context
     * @param message
     * @param positive
     * @param negative
     */
    public static void show(@NonNull Context context, @StringRes int message, DialogInterface.OnClickListener positive,
                            DialogInterface.OnClickListener negative) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // set message alert
        builder.setMessage(message);
        // Sets whether this dialog is canceled when touched outside the window's bounds. or not
        builder.setCancelable(false);
        if (positive != null && negative != null) {
            builder.setPositiveButton(R.string.dialog_action_yes, positive);
            builder.setNegativeButton(R.string.dialog_action_no, negative);
        }
        // show alert
        builder.create().show();

    }
}

