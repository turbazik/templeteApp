package com.example.templateapp.main.rates.ui.adapterlist

import android.view.View
import android.widget.TextView
import com.example.templateapp.core.BaseViewHolder
import com.example.templateapp.data.datasource.remote.model.api.Rates
import kotlinx.android.synthetic.main.rates_item.view.*

class RatesViewHolder(val mView: View, private val delegate: Delegate) : BaseViewHolder(mView) {
    private lateinit var item: Rates

    override fun bindData(data: Any) {
        if (data is Rates) {
            item = data
        }
    }

    interface Delegate {
        fun onItemClick(item: Rates)
    }

    override fun onClick(v: View?) {
        delegate.onItemClick(this.item)
    }

    init {
        mView.setOnClickListener(this)
    }

    val name: TextView = itemView.nameTv
    val value: TextView = itemView.valueTv
}