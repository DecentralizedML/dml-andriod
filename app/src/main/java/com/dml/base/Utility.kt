package com.dml.base

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.net.ConnectivityManager
import android.util.DisplayMetrics
import android.util.Patterns
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.widget.NestedScrollView
import com.dml.base.view.ui.WelcomeActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


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

        fun convertDpToPixel(dp: Float): Float {
            return dp * (Resources.getSystem().displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }

        fun convertPixelsToDp(px: Float): Float {
            return px / (Resources.getSystem().displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }

        fun getScreenWidth(): Int {
            return Resources.getSystem().displayMetrics.widthPixels
        }

        fun getScreenHeight(): Int {
            return Resources.getSystem().displayMetrics.heightPixels
        }

        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        fun isLoggedIn(context: Context): Boolean {
            return Preferences.getJWT(context).isNotBlank()
        }

        fun getGoogleSignInClient(context: Context): GoogleSignInClient {

            val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestProfile()
                    .build()
            return GoogleSignIn.getClient(context, googleSignInOptions)
        }

        fun logout(context: Context) {
            Preferences.setJWT(context, "")

            val googleSignInAccount = GoogleSignIn.getLastSignedInAccount(context)
            if (googleSignInAccount != null) {
                getGoogleSignInClient(context).signOut()
            }

            val intent = Intent(context, WelcomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
            finishAffinity(context as Activity)
        }

        fun canScroll(scrollView: NestedScrollView): Boolean {
            val child = scrollView.getChildAt(0)
            if (child != null) {
                val childHeight = child.height
                return scrollView.height < childHeight + scrollView.paddingTop + scrollView.paddingBottom
            }
            return false
        }
    }
}