package com.example.templateapp.features.rates.presentation.ui.adapterlist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.templateapp.R
import com.example.templateapp.features.rates.data.datasource.remote.model.api.Rates

class RatesAdapter(val delegate: RatesViewHolder.Delegate) :
    RecyclerView.Adapter<RatesViewHolder>() {
    private var mValues: ArrayList<Rates> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rates_item, parent, false)
        return RatesViewHolder(
            view,
            delegate
        )
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        val item = mValues[position]
        holder.bindData(item)
        holder.name.text = item.name
        holder.value.text = item.value.toString()

    }

    override fun getItemCount(): Int = mValues.size

    fun updateAll(newValues: List<Rates>) {
        mValues.clear()
        mValues.addAll(newValues)
        notifyDataSetChanged()
    }
}

