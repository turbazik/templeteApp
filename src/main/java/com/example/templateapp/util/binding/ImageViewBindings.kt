package com.example.templateapp.util.binding

import androidx.databinding.BindingAdapter
import android.widget.ImageView

@BindingAdapter("imageView_src")
fun setDrawableResource(imageView: ImageView, drawableRes: Int) {
    if (drawableRes != 0) {
        imageView.setImageResource(drawableRes)
    } else {
        imageView.setImageDrawable(null)
    }
}