package com.dml.base

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.DisplayMetrics
import android.util.Patterns
import androidx.core.app.ActivityCompat.finishAffinity
import com.dml.base.activity.WelcomeActivity


class Utility {
    companion object {
        fun Log(tag: String, msg: String) {
//        if (BuildSettings.SHOW_LOG)
            android.util.Log.d(tag, msg)
        }

        fun Error(tag: String, msg: String) {
//        if (BuildSettings.SHOW_ERROR)
            android.util.Log.e(tag, msg)
        }

        fun showDialog(context: Context, errorMessage: Int) {
            showDialog(context, errorMessage, null)
        }

        fun showDialog(context: Context, errorMessage: String) {
            showDialog(context, errorMessage, null)
        }

        fun showDialog(context: Context, errorMessage: Int, listener: DialogInterface.OnClickListener?) {
            showDialog(context, context.getString(errorMessage), listener)
        }

        fun showDialog(context: Context, errorMessage: String, listener: DialogInterface.OnClickListener?) {
            showDialog(context, errorMessage, context.getString(R.string.common_ok), listener, null, null, true)
        }

        fun showDialog(context: Context, errorMessage: String, listener: DialogInterface.OnClickListener, closeOutSlide: Boolean) {
            showDialog(context, errorMessage, context.getString(R.string.common_ok), listener, null, null, closeOutSlide)
        }

        fun showDialog(context: Context, errorMessage: Int, positiveButton: Int, positiveButtonListener: DialogInterface.OnClickListener,
                       negativeButton: Int, negativeButtonListener: DialogInterface.OnClickListener) {
            showDialog(context, context.getString(errorMessage), context.getString(positiveButton), positiveButtonListener, context.getString(negativeButton), negativeButtonListener)
        }

        fun showDialog(context: Context, errorMessage: String, positiveButton: String, positiveButtonListener: DialogInterface.OnClickListener,
                       negativeButton: String, negativeButtonListener: DialogInterface.OnClickListener) {
            showDialog(context, errorMessage, positiveButton, positiveButtonListener, negativeButton, negativeButtonListener, true)
        }

        fun showDialog(context: Context, errorMessage: String, positiveButton: String?, positiveButtonListener: DialogInterface.OnClickListener?,
                       negativeButton: String?, negativeButtonListener: DialogInterface.OnClickListener?, closeOutside: Boolean) {
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setMessage(errorMessage)
            if (positiveButton != null && !positiveButton.isEmpty())
                alertDialogBuilder.setPositiveButton(positiveButton, positiveButtonListener)
            if (negativeButton != null && !negativeButton.isEmpty())
                alertDialogBuilder.setNegativeButton(negativeButton, negativeButtonListener)
            alertDialogBuilder.setCancelable(closeOutside)
            alertDialogBuilder.show()
        }

        fun isValidEmail(email: String): Boolean {
            return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun setClipboard(context: Context, label: String, text: String) {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            val clip = android.content.ClipData.newPlainText(label, text)
            clipboard.primaryClip = clip
        }

        fun convertDpToPixel(context: Context, dp: Float): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }

        fun convertPixelsToDp(context: Context, px: Float): Float {
            return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }

        fun isLoggedIn(context: Context): Boolean {
            return Preferences.getJWT(context).isNotBlank()
        }

        fun logout(context: Context) {
            Preferences.setJWT(context, "")
            val intent = Intent(context, WelcomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
            finishAffinity(context as Activity)
        }
    }
}