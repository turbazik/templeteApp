package com.example.templateapp.core

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.templateapp.util.widgets.loader.SRProgressDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber
import kotlin.coroutines.CoroutineContext


@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), CoroutineScope {

    private val tag: String = this.javaClass.simpleName

    private lateinit var job: Job

    var isRunning: Boolean = false

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timberLog("onCreate")
        job = Job()
    }

    override fun onStart() {
        super.onStart()
        timberLog("onStart")
    }

    override fun onStop() {
        super.onStop()
        timberLog("onStop")
    }

    override fun onResume() {
        super.onResume()
        timberLog("onResume")
        isRunning = true
    }

    override fun onPause() {
        super.onPause()
        timberLog("onPause")
        isRunning = false
    }

    override fun onDestroy() {
        super.onDestroy()
        timberLog("onDestroy")
        job.cancel()
    }


    fun navigate(@IdRes navViewId: Int, action: NavDirections, navOptions: NavOptions? = null) {
        timberLog("navigate")
        try {

            timberLog(
                "navigate: navViewId=${resources.getResourceEntryName(navViewId)}, " +
                        "action={actionId=${resources.getResourceEntryName(action.actionId)}, " +
                        "args=${action.arguments}}"
            )

            val navController = Navigation.findNavController(this, navViewId)
            navController.navigate(action, navOptions)
        } catch (e: Exception) {
            Timber.tag(tag).e(e)
        }
    }

    private var loadingDialog: SRProgressDialog? = null

    fun showLoadingLayout() {
        if (loadingDialog == null) {
            loadingDialog = SRProgressDialog.show(this, false, null)
        }

        if (loadingDialog?.isShowing == false) {
            loadingDialog!!.show()
        }
    }

    fun hideLoadingLayout() {
        loadingDialog?.dismiss()
    }

    private fun timberLog(message: String?) {
        Timber.tag(tag).d(message)
    }
}

