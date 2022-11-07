package com.subin.ldtestapplication.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.subin.ldtestapplication.R

@BindingAdapter("imgUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.loading)
            .error(R.drawable.loading)
            .fallback(R.drawable.loading)
            .into(view)
    }
}