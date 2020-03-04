package com.example.templateapp.util.binding

import androidx.databinding.BindingAdapter
import android.view.View

/**
 * Show the view if the argument is true, hide otherwise.
 */
@BindingAdapter("show")
fun show(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

/**
 * Hide the view if the argument is true, show otherwise.
 */
@BindingAdapter("hide")
fun hide(view: View, hide: Boolean) {
    view.visibility = if (hide) View.GONE else View.VISIBLE
}

/**
 * Make the view invisible (but it will still take up space) if the argument is false, make
 * it visible otherwise.
 */
@BindingAdapter("visible")
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}