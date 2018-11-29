package com.dml.base.connection

import com.dml.base.Utility
import com.dml.base.network.ErrorResponse
import com.google.gson.Gson
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException

abstract class DefaultRequestObserver<T> : DisposableObserver<T>() {

    abstract fun onErrorResponse(errorResponse: ErrorResponse)

    abstract fun onErrorUnknown()

    override fun onError(throwable: Throwable) {
        if (throwable is HttpException) {
            try {
//                if (throwable.response().code() == 401) {
//                    //                    Bus.send(CallBadge())
//                    //                    Bus.INSTANCE.send(LogoutEvent.class);
//                    val error = Gson().fromJson(throwable.response().errorBody()!!.string(), ErrorResponse::class.java)
//                    Utility.showDialog(context, error.errors.toString())
////                    context?.let { Utility.showDialog(it, error.errorMessage + " (err:" + error.errorCode + ")", DialogInterface.OnClickListener { dialog, which -> Bus.INSTANCE.send(LogoutEvent()) }, false) }
//                } else {
//                    val message = throwable.response().errorBody()!!.string()
//
//                    Utility.showDialog(context, message)
////                    onErrorResponse(Gson().fromJson(message, ErrorResponse::class.java))
//                }

                onErrorResponse(Gson().fromJson(throwable.response().errorBody()!!.string(), ErrorResponse::class.java))

            } catch (e: Exception) {
                Utility.Log("onErrorResponse", e.printStackTrace().toString())
//                Utility.showDialog(context, throwable.toString())
            }

        } else {
            onErrorUnknown()
//            val alertDialogBuilder = AlertDialog.Builder(context)
//            alertDialogBuilder.setTitle(context.getString(R.string.no_network_title))
//            alertDialogBuilder.setMessage(context.getString(R.string.no_network))
//            alertDialogBuilder.setPositiveButton(context.getString(R.string.common_ok)) { dialog, which -> }
//            alertDialogBuilder.show()
        }

        onComplete()
    }

    override fun onComplete() {}
}
