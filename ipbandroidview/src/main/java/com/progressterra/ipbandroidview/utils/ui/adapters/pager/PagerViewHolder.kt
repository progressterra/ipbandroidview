package com.progressterra.ipbandroidview.utils.ui.adapters.pager

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.BR
import com.progressterra.ipbandroidview.utils.ui.adapters.recycler.IListItem

class PagerViewHolder<Item : IListItem, Binding : ViewDataBinding>(private val binding: Binding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        item: Item,
        onItemClick: ((Item) -> Unit)?,
        onItemBind: (Binding.(Item) -> Unit)?,
        lifecycleOwner: LifecycleOwner?
    ) {
        onItemClick?.let { onCLick ->
            binding.root.setOnClickListener {
                onCLick.invoke(item)
            }
        }

        onItemBind?.invoke(binding, item)
        binding.setVariable(BR.item, item)
        binding.lifecycleOwner = lifecycleOwner
    }
}