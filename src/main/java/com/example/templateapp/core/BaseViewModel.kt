package com.example.templateapp.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.templateapp.core.datatype.ResultType
import com.example.templateapp.data.datasource.remote.exceptions.NetworkConnectionException
import com.example.templateapp.main.rates.domain.model.RatesEntity
import com.example.templateapp.util.Event
import com.example.templateapp.util.widgets.dialog.type.PosDialog
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val showDialog = MutableLiveData<Event<PosDialog>>()


    fun <T : Any> apiCall(
        call: suspend () -> com.example.templateapp.core.datatype.Result<BaseResponse<T>>,
        success: ((T) -> Unit)? = null,
        successVoid: (() -> Unit)? = null,
        error: ((String, String?) -> Unit)? = null,
        loading: (() -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        handleErrorOutside: Boolean = false
    ) {
        viewModelScope.launch {
            loading?.invoke()
            val result = call.invoke()
            if (result.resultType == ResultType.SUCCESS) {
                if (result.data?.success!!) {
                    if (result.data.data != null) {
                        success?.invoke(result.data.data!!)
                    } else {
                        if (success != null) {
                            val message = "Неверный ответ сервера"

                            showDialog.value = Event(
                                PosDialog()
                                    .title("Ошибка")
                                    .content(message)
                            )

                            timberLog(message)
                        } else {
                            successVoid?.invoke()
                        }
                    }
                } else {
                    val message = result.data.data.info ?: "unknown error"
                    if (handleErrorOutside) {
                        error?.invoke(message, null)
                    } else {
                        showDialog.value = Event(
                            PosDialog()
                                .title(message)
                        )
                    }

                    timberLog(message)
                }

            } else {
                val title: String
                val content: String

                if (result.error is NetworkConnectionException) {
                    title = "Интернет недоступен"
                    content = "Проверьте соединение и\nповторите попытку"
                } else {
                    title = "Сервис временно\nнедоступен"
                    content = "Попробуйте позже"
                }

                if (handleErrorOutside) {
                    error?.invoke(title, content)
                } else {
                    showDialog.value = Event(
                        PosDialog()
                            .title(title)
                            .content(content)
                    )
                }

                Timber.tag(getTag()).d(result.error)
            }
        }

        onComplete?.invoke()
    }


    private fun timberLog(message: String?) {
        Timber.tag(getTag()).d(message)
    }

    abstract fun getTag(): String
}