package com.dml.base.view.dialog

import android.content.DialogInterface
import android.view.View
import com.dml.base.R
import com.dml.base.base.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_general_message.view.*

class GeneralMessageDialog : BaseDialogFragment() {
    var title: String = ""
    var titleId: Int = 0
    var image: Int = 0
    var desc: String = ""
    var descId: Int = 0
    var btnText: String = ""
    var btnTextId: Int = 0
    var btnListener: DialogInterface.OnClickListener? = null

    override fun init() {
    }

    override fun setDialogLayoutId(): Int {
        return R.layout.dialog_general_message
    }

    override fun connectViews(dialogView: View) {
        if (titleId == 0)
            dialogView.titleTextView?.text = title
        else
            dialogView.titleTextView?.text = getString(titleId)

        if (descId == 0)
            dialogView.descTextView?.text = desc
        else
            dialogView.descTextView?.text = getString(descId)

        dialogView.iconImageView?.setImageResource(image)

        dialogView.confirmButton?.apply {
            if (btnTextId == 0)
                setTextString(btnText)
            else
                setText(btnTextId)

            setOnClickListener {
                if (btnListener == null)
                    dismiss()
                else
                    btnListener?.onClick(dialog, 0)
            }
        }
    }

    fun builder(): Builder {
        return Builder()
    }

    class Builder {
        private var title: String = ""
        private var titleId: Int = 0
        private var image: Int = 0
        private var desc: String = ""
        private var descId: Int = 0
        private var btnText: String = ""
        private var btnTextId: Int = 0
        private var btnListener: DialogInterface.OnClickListener? = null

        fun title(title: String) = apply { this.title = title }
        fun titleId(titleId: Int) = apply { this.titleId = titleId }
        fun image(image: Int) = apply { this.image = image }
        fun desc(desc: String) = apply { this.desc = desc }
        fun descId(descId: Int) = apply { this.descId = descId }
        fun btnText(btnText: String) = apply { this.btnText = btnText }
        fun btnTextId(btnTextId: Int) = apply { this.btnTextId = btnTextId }
        fun btnListener(btnListener: DialogInterface.OnClickListener) = apply { this.btnListener = btnListener }

        fun build(): GeneralMessageDialog {
            val dialog = GeneralMessageDialog()
            dialog.title = title
            dialog.titleId = titleId
            dialog.image = image
            dialog.desc = desc
            dialog.descId = descId
            dialog.btnText = btnText
            dialog.btnTextId = btnTextId
            dialog.btnListener = btnListener
            return dialog
        }
    }
}