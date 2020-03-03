package com.example.templateapp.core

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder(private val view: View) :RecyclerView.ViewHolder(view), View.OnClickListener {

    init {
        view.setOnClickListener(this)
    }

    @Throws(Exception::class)
    abstract fun bindData(data: Any)

    protected fun view(): View {
        return view
    }

    protected fun context(): Context {
        return view.context
    }
}