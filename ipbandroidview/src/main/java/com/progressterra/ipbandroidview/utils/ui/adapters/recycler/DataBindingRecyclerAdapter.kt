package com.progressterra.ipbandroidview.utils.ui.adapters.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class DataBindingRecyclerAdapter<Item : IListItem, Binding : ViewDataBinding>(
    @LayoutRes
    private val layoutRes: Int,
    private val onItemClick: ((Item) -> Unit)? = null,
    private val onItemBind: (Binding.(Item) -> Unit)? = null,
    private val lifecycleOwner: LifecycleOwner? = null,
    diffUtilCallback: DiffUtil.ItemCallback<Item> = DiffUtilCallback()
) : ListAdapter<Item, DataBindingViewHolder<Item, Binding>>(diffUtilCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBindingViewHolder<Item, Binding> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<Binding>(layoutInflater, layoutRes, parent, false)
        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<Item, Binding>, position: Int) {
        getItem(position)?.let {
            holder.onBind(
                item = it,
                onItemClick = onItemClick,
                onItemBind = onItemBind,
                lifecycleOwner = lifecycleOwner
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}