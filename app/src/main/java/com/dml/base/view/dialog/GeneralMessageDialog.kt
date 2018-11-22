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
    var rightButtonText: String = ""
    var rightButtonTextId: Int = 0
    var rightButtonListener: DialogInterface.OnClickListener? = null
    var leftButtonText: String = ""
    var leftButtonTextId: Int = 0
    var leftButtonListener: DialogInterface.OnClickListener? = null

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
            if (rightButtonTextId == 0)
                setTextString(rightButtonText)
            else
                setText(rightButtonTextId)

            setOnClickListener {
                if (rightButtonListener == null)
                    dismiss()
                else
                    rightButtonListener?.onClick(dialog, 0)
            }
        }

        dialogView.cancelButton?.apply {
            if (leftButtonTextId == 0) {
                setTextString(leftButtonText)
                visibility = View.VISIBLE
                setBackground(R.drawable.button_white)
                setTextColor(R.color.text_green)
                buttonSpace.visibility = View.VISIBLE
            } else if (!leftButtonText.isNullOrEmpty()) {
                setText(leftButtonTextId)
                visibility = View.VISIBLE
                setBackground(R.drawable.button_white)
                setTextColor(R.color.text_green)
                buttonSpace.visibility = View.VISIBLE
            }

            setOnClickListener {
                if (leftButtonListener == null)
                    dismiss()
                else
                    leftButtonListener?.onClick(dialog, 0)
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
        private var rightButtonText: String = ""
        private var rightButtonTextId: Int = 0
        private var rightButtonListener: DialogInterface.OnClickListener? = null
        private var leftButtonText: String = ""
        private var leftButtonTextId: Int = 0
        private var leftButtonListener: DialogInterface.OnClickListener? = null

        fun title(title: String) = apply { this.title = title }
        fun titleId(titleId: Int) = apply { this.titleId = titleId }
        fun image(image: Int) = apply { this.image = image }
        fun desc(desc: String) = apply { this.desc = desc }
        fun descId(descId: Int) = apply { this.descId = descId }
        fun rightButtonText(btnText: String) = apply { this.rightButtonText = btnText }
        fun rightButtonTextId(btnTextId: Int) = apply { this.rightButtonTextId = btnTextId }
        fun rightButtonListener(btnListener: DialogInterface.OnClickListener) = apply { this.rightButtonListener = btnListener }
        fun leftButtonText(btnText: String) = apply { this.leftButtonText = btnText }
        fun leftButtonTextId(btnTextId: Int) = apply { this.leftButtonTextId = btnTextId }
        fun leftButtonListener(btnListener: DialogInterface.OnClickListener) = apply { this.leftButtonListener = btnListener }

        fun build(): GeneralMessageDialog {
            val dialog = GeneralMessageDialog()
            dialog.title = title
            dialog.titleId = titleId
            dialog.image = image
            dialog.desc = desc
            dialog.descId = descId
            dialog.rightButtonText = rightButtonText
            dialog.rightButtonTextId = rightButtonTextId
            dialog.rightButtonListener = rightButtonListener
            dialog.leftButtonText = leftButtonText
            dialog.leftButtonTextId = leftButtonTextId
            dialog.leftButtonListener = leftButtonListener
            return dialog
        }
    }
}