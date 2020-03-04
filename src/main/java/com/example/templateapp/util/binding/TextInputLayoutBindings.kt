package com.example.templateapp.util.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("textInputLayout_error")
fun setError(textInputLayout: TextInputLayout, error: String?) {
    val errorEnabled = !error.isNullOrEmpty()
    if (errorEnabled) {
        textInputLayout.isErrorEnabled = true
    }
    textInputLayout.error = error
    if (!errorEnabled) {
        textInputLayout.isErrorEnabled = false
    }
}