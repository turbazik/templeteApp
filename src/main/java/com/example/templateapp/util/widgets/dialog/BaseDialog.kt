package kz.kaspibusiness.smartpos.ui.components.dialog

import android.content.DialogInterface
import androidx.annotation.CallSuper
import com.afollestad.materialdialogs.MaterialDialog
import com.example.templateapp.core.BaseActivity

abstract class BaseDialog {

    protected lateinit var activity: BaseActivity
    protected lateinit var dialog: MaterialDialog

    var type: String? = null
        private set
    private val onCloseListeners = mutableListOf<(BaseDialog) -> Unit>()

    // Boolean flag to make sure that the OnClose listeners are only run once.
    private var onCloseListenersHaveBeenRun = false

    private val onCancelListener = DialogInterface.OnCancelListener { runOnCloseListeners() }
    private val onDismissListener = DialogInterface.OnDismissListener { runOnCloseListeners() }

    private fun runOnCloseListeners() {
        if (!onCloseListenersHaveBeenRun) {
            for (listener in onCloseListeners) {
                listener(this)
            }
        }
        onCloseListenersHaveBeenRun = true
    }

    fun addOnCloseListener(listener: () -> Unit): BaseDialog {
        onCloseListeners.add { listener() }
        return this
    }

    fun addOnCloseListenerWithDialog(listener: (BaseDialog) -> Unit): BaseDialog {
        onCloseListeners.add(listener)
        return this
    }


    fun setContext(activity: BaseActivity): BaseDialog {
        this.activity = activity
        this.dialog = MaterialDialog(activity)
        return this
    }

    fun setType(type: String): BaseDialog {
        this.type = type
        return this
    }

    @CallSuper
    open fun dismiss() {
        runOnCloseListeners()

        if (activity.isFinishing) {
            return
        }

        dialog.dismiss()
    }

    abstract fun show()
}