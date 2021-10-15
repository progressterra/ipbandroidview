package com.progressterra.ipbandroidview.utils.ui.adapters.pager

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.progressterra.ipbandroidview.utils.ui.adapters.recycler.IListItem

class PagerDiffUtil<Item : IListItem> : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}