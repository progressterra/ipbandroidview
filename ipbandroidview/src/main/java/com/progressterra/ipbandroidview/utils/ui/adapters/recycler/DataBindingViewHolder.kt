package com.progressterra.ipbandroidview.utils.ui.adapters.recycler

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.BR

class DataBindingViewHolder<Item : IListItem, Binding : ViewDataBinding>(private val binding: Binding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        item: Item,
        onItemClick: ((Item) -> Unit)?,
        onItemBind: (Binding.(Item) -> Unit)?,
        lifecycleOwner: LifecycleOwner?
    ) {

        // Setup on item click listener
        onItemClick?.let { onClick ->
            binding.root.setOnClickListener {
                onClick.invoke(item)
            }
        }

        // Call on bind
        onItemBind?.invoke(binding, item)
        binding.setVariable(BR.item, item)
        binding.lifecycleOwner = lifecycleOwner
    }
}