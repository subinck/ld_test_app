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

class NumberPickerAdapter(val list:List<SeasonNumbers>,
                          private val context:Context,
                          private var selected:Int,
                          private val clickListener:(SeasonNumbers)->Unit): RecyclerView.Adapter<NumberPickerAdapter.ViewHolder>() {

    private lateinit var binding:NumberPickerItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
          binding = DataBindingUtil.inflate(
            view,
            R.layout.number_picker_item,
            parent,
            false
        )

        return ViewHolder(binding,selected)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = list?.get(position)
        holder.bind(itemsViewModel, clickListener, context, position)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: NumberPickerItemBinding, private var selected: Int) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            ItemsViewModel: SeasonNumbers?,
            clickListener: (SeasonNumbers) -> Unit,
            context: Context,
            position: Int
        ) {
            binding.itemNumberText.text = ItemsViewModel?.number.toString()
            if (binding.itemNumberText.text == selected.toString()) {
                binding.itemNumberText.background =
                    ContextCompat.getDrawable(context, R.drawable.selection_circle)
            }
            if (ItemsViewModel?.isSelected==true){
                binding.itemNumberText.background =
                    ContextCompat.getDrawable(context, R.drawable.selection_circle)
            }
            binding.root.setOnClickListener {
                ItemsViewModel?.let { it1 -> clickListener(it1) }

            }

        }
    }

}

