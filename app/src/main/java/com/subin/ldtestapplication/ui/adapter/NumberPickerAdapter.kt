package com.subin.ldtestapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.subin.ldtestapplication.R
import com.subin.ldtestapplication.data.models.SeasonNumbers
import com.subin.ldtestapplication.databinding.NumberPickerItemBinding

class NumberPickerAdapter(val list:List<SeasonNumbers>,private val context: Context,private val clickListener:(SeasonNumbers)->Unit): RecyclerView.Adapter<NumberPickerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: NumberPickerItemBinding = DataBindingUtil.inflate(
            view,
            R.layout.number_picker_item,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = list?.get(position)
        holder.bind(itemsViewModel,clickListener,context)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class ViewHolder(private val binding: NumberPickerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ItemsViewModel: SeasonNumbers?,clickListener: (SeasonNumbers) -> Unit,context: Context) {
            binding.itemNumberText.text=ItemsViewModel?.number.toString()
            binding.root.setOnClickListener {
              //  binding.itemNumberText.background= ContextCompat.getDrawable(context, R.drawable.selection_circle);
                ItemsViewModel?.let { it1 -> clickListener(it1) }
            }
        }
    }
}

