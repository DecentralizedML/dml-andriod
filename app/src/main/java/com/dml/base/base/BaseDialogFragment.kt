package com.dml.base.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment : DialogFragment() {

    protected abstract fun init()

    protected abstract fun setDialogLayoutId(): Int

    protected abstract fun connectViews(dialogView: View)

    protected abstract fun setTextLang()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.setCanceledOnTouchOutside(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = activity!!.layoutInflater.inflate(setDialogLayoutId(), null)
        connectViews(view)
        setTextLang()
        init()

        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(view)
        dialog.window?.setBackgroundDrawable(ContextCompat.getDrawable(activity!!, android.R.color.transparent))
        dialog.show()

        return dialog
    }
}