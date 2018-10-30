package com.dml.base.connection

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.network.ErrorResponse
import com.google.gson.Gson
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException

abstract class DefaultRequestObserver<T>(var context: Context) : DisposableObserver<T>() {
    override fun onError(throwable: Throwable) {
        if (throwable is HttpException) {
            try {
                if (throwable.response().code() == 401) {
                    //                    Bus.send(CallBadge())
                    //                    Bus.INSTANCE.send(LogoutEvent.class);
                    val error = Gson().fromJson(throwable.response().errorBody()!!.string(), ErrorResponse::class.java)
                    Utility.showDialog(context, error.errorMessage + " (err:" + error.errorCode + ")")
//                    context?.let { Utility.showDialog(it, error.errorMessage + " (err:" + error.errorCode + ")", DialogInterface.OnClickListener { dialog, which -> Bus.INSTANCE.send(LogoutEvent()) }, false) }
                } else {
                    val message = throwable.response().errorBody()!!.string()
                    onError(Gson().fromJson(message, ErrorResponse::class.java))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                Utility.showDialog(context, throwable.toString())
            }

        } else {
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setTitle(context.getString(R.string.no_network_title))
            alertDialogBuilder.setMessage(context.getString(R.string.no_network))
            alertDialogBuilder.setPositiveButton(context.getString(R.string.common_ok)) { dialog, which -> }
            alertDialogBuilder.show()
        }
        onComplete()
    }


    override fun onComplete() {}

    private fun onError(errorResponse: ErrorResponse) {
        Utility.showDialog(context, errorResponse.errorMessage + " (err:" + errorResponse.errorCode + ")")
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
