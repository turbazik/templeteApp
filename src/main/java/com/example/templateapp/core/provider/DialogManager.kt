package com.example.templateapp.core.provider


import com.example.templateapp.core.BaseActivity
import kz.kaspibusiness.smartpos.ui.components.dialog.BaseDialog

class DialogManager(var activity: BaseActivity?) {

    private val singletonDialogs = mutableMapOf<String, BaseDialog>()

    fun showDialog(dialog: BaseDialog) {
        val activity = activity ?: return

        if (!activity.isRunning) return

        val type = dialog.type
        if (type != null) {
            singletonDialogs[type]?.dismiss()
            singletonDialogs[type] = dialog
        }

        dialog.setContext(activity)
            .addOnCloseListenerWithDialog { closedDialog ->
                val dialogType = closedDialog.type
                if (dialogType != null) {
                    singletonDialogs.remove(dialogType)
                }
            }
            .show()
    }

    companion object {
        const val TYPE_POS = "TYPE_POS"
    }
}