package com.example.templateapp.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.templateapp.core.datatype.ResultType
import com.example.templateapp.features.rates.data.datasource.remote.exceptions.NetworkConnectionException
import com.example.templateapp.util.Event
import com.example.templateapp.util.widgets.dialog.type.CustomDialog
import com.example.templateapp.core.datatype.Result
import com.example.templateapp.features.rates.data.datasource.remote.exceptions.GenericNetworkException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val showDialog = MutableLiveData<Event<CustomDialog>>()

    fun <T : Any> apiCall(
        call: suspend () -> Result<BaseResponse<T>>,
        success: ((T) -> Unit)? = null,
        successVoid: (() -> Unit)? = null,
        error: ((String, String?) -> Unit)? = null,
        loading: (() -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        handleErrorOutside: Boolean = false
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            loading?.invoke()
            val result = call.invoke()
            if (result.resultType == ResultType.SUCCESS) {
                if (result.data?.success!!) {
                    if (result.data.data != null) {
                        success?.invoke(result.data.data!!)
                    } else {
                        if (success != null) {
                            val message = "Неверный ответ сервера"

                            showDialog.postValue(
                                Event(
                                    CustomDialog()
                                        .title("Ошибка")
                                        .content(message)
                                )
                            )

                            timberLog(message)
                        } else {
                            successVoid?.invoke()
                        }
                    }
                } else {
                    val message = result.data.error?.info ?: "unknown error"
                    if (handleErrorOutside) {
                        error?.invoke(message, null)
                    } else {
                        showDialog.postValue(
                            Event(
                                CustomDialog()
                                    .title(message)
                            )
                        )
                    }

                    timberLog(message)
                }

            } else {
                val resultError = result.error
                val title = resultError?.message ?: ""
                val content = resultError?.cause?.message

                if (handleErrorOutside) {
                    error?.invoke(title, content)
                } else {
                    showDialog.postValue(
                        Event(
                            CustomDialog()
                                .title(title)
                                .content(content)
                        )
                    )
                }

                Timber.tag(getTag()).d(result.error)
            }
            onComplete?.invoke()
        }
    }


    private fun timberLog(message: String?) {
        Timber.tag(getTag()).d(message)
    }

    abstract fun getTag(): String
}