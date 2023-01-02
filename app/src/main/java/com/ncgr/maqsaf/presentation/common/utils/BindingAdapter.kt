package com.ncgr.maqsaf.presentation.common.utils

import com.ncgr.maqsaf.domain.menu.model.Item
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.presentation.customer.adapter.ItemListAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Item>) {
    val adapter = recyclerView.adapter as ItemListAdapter
    adapter.submitList(data)
}