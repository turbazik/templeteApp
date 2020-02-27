package com.example.templateapp.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.templateapp.util.Event
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

abstract class BaseViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>()


    fun <T : Any> apiCall(
        call: suspend () -> com.example.templateapp.core.datatype.Result<T>,
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
            when (result) {
                is Result.Success<*> -> {
                    with(result.data as BaseResponse<T>) {
                        if (result.data.StatusCode == 0) {
                            if (result.data.Data != null) {
                                success?.invoke(result.data.Data as T)
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
                            val message = result.data.Message ?: "unknown error"
                            if (handleErrorOutside) {
                                error?.invoke(message, null)
                            } else {
                                showDialog.value = Event(
                                    PosDialog()
                                        .title(message)
                                )
                            }

                            timberLog(message)
                            Crashlytics.logException(Exception(message))
                        }
                    }
                }
                is Result.Error -> {
                    val title: String
                    val content: String

                    if (result.exception is IOException) {
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
    }

    fun timberLog(message: String?) {
        Timber.tag(getTag()).d(message)
    }

    abstract fun getTag(): String
}