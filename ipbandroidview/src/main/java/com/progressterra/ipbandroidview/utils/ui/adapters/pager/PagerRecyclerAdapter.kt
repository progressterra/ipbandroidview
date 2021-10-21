package com.progressterra.ipbandroidview.utils.ui.adapters.pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.progressterra.ipbandroidview.utils.ui.adapters.recycler.IListItem

class PagerRecyclerAdapter<Item : IListItem, Binding : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int,
    private val onItemClick: ((Item) -> Unit)? = null,
    private val onItemBind: (Binding.(Item, position: Int) -> Unit)? = null,
    private val lifecycleOwner: LifecycleOwner? = null,
    diffUtil: DiffUtil.ItemCallback<Item> = PagerDiffUtil()
) : PagingDataAdapter<Item, PagerViewHolder<Item, Binding>>(diffUtil) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PagerViewHolder<Item, Binding> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<Binding>(layoutInflater, layoutRes, parent, false)
        return PagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder<Item, Binding>, position: Int) {
        getItem(position)?.let {
            holder.onBind(
                item = it,
                onItemClick = onItemClick,
                onItemBind = onItemBind,
                lifecycleOwner = lifecycleOwner
            )
        }
    }
}