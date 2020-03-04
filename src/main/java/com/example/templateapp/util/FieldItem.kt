package com.example.templateapp.util

import androidx.lifecycle.MutableLiveData

class FieldItem<T>(
        sVal: T,
        sTitle: String? = null,
        val errMsg: String = "Пустое значение не допускается"
) {

    val value = MutableLiveData<T>()
    val error = MutableLiveData<String>()
    val title = MutableLiveData<String>()

    var onValChange: (() -> Unit)? = null

    init {
        value.value = sVal
        this.title.value = sTitle
    }

    fun validate(): Boolean {
        val a = value.value

        if (a == null) {
            error.postValue(errMsg)
            return false
        }
        if (a is String && a.isEmpty()) {
            error.postValue(errMsg)
            return false
        }

        error.postValue("")
        return true
    }

    fun clearError() {
        error.postValue("")
    }

    fun isClearError() : Boolean {
        error.postValue("")
        return false
    }
}
