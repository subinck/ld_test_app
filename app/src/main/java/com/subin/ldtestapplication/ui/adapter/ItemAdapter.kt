package com.subin.ldtestapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.subin.ldtestapplication.R
import com.subin.ldtestapplication.Utils.Utility
import com.subin.ldtestapplication.data.database.TvShowEntity
import com.subin.ldtestapplication.data.models.TVShowResponseItem
import com.subin.ldtestapplication.databinding.ListItemBinding

class ItemAdapter constructor(private val list: List<TvShowEntity>?,
                            ): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
            view,
            R.layout.list_item,
            parent,
            false)

        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = list?.get(position)
        holder.bind(itemsViewModel)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class ViewHolder( private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ItemsViewModel: TvShowEntity?) {
            binding.resItem=ItemsViewModel
            binding.listItemDescription.text=Utility.html2text(ItemsViewModel?.summary)
        }
    }
}