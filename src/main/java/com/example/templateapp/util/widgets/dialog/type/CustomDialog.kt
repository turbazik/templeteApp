package com.example.templateapp.util.widgets.dialog.type

import android.view.View
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.example.templateapp.R
import com.example.templateapp.core.provider.DialogManager
import kotlinx.android.synthetic.main.dialog_smartpos.*
import kz.kaspibusiness.smartpos.ui.components.dialog.BaseDialog
import org.jetbrains.anko.sdk25.coroutines.onClick

class CustomDialog : BaseDialog() {

    private var onOkClick: (() -> Unit)? = null
    private var onCancelClick: (() -> Unit)? = null

    var titleText: String? = null
    private var contentText: String? = null

    private var ok: String? = null
    private var cancel: String? = null
    private var showCancel: Boolean = false
    private var cancelable: Boolean = true

    init {
        setType(DialogManager.TYPE_POS)
    }

    fun okClick(onOkClick: () -> Unit): CustomDialog {
        this.onOkClick = onOkClick
        return this
    }

    fun cancelClick(onCancelClick: () -> Unit): CustomDialog {
        this.onCancelClick = onCancelClick
        return this
    }

    fun cancelOnTouchOutside(cancelable: Boolean): CustomDialog {
        this.cancelable = cancelable
        return this
    }

    fun showCancel(): CustomDialog {
        this.showCancel = true
        return this
    }

    fun title(titleText: String): CustomDialog {
        this.titleText = titleText
        return this
    }

    fun content(contentText: String?): CustomDialog {
        this.contentText = contentText
        return this
    }

    fun okText(text: String): CustomDialog {
        this.ok = text
        return this
    }

    fun cancelText(text: String): CustomDialog {
        this.cancel = text
        return this
    }

    override fun show() {
        dialog.setContentView(R.layout.dialog_smartpos)

        dialog.cancelOnTouchOutside(cancelable)

        with(dialog) {
            cancelBtn.visibility = if (showCancel) View.VISIBLE else View.GONE

            if (titleText != null) {
                title.visibility = View.VISIBLE
                title.text = titleText
            } else {
                title.visibility = View.GONE
            }

            if (contentText != null) {
                content.visibility = View.VISIBLE
                content.text = contentText
            } else {
                content.visibility = View.GONE
            }

            if (ok != null) {
                okBtn.text = ok
            }

            if (cancel != null) {
                cancelBtn.text = cancel
            }

            okBtn.onClick {
                onOkClick?.invoke()
                dismiss()
            }

            cancelBtn.onClick {
                onCancelClick?.invoke()
                dismiss()
            }
        }

        dialog.onDismiss {
            // onCancelClick?.invoke()
        }

        dialog.show()
    }
}