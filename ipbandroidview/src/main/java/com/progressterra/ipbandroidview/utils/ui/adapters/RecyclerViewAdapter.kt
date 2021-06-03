package com.progressterra.ipbandroidview.utils.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

internal open class RecyclerViewAdapter<Item>(
    @LayoutRes private val layoutRes: Int,
    private val onNormalBind: ((binding: ViewDataBinding, item: Item) -> Unit)? = null,
    private val onBind: ((itemView: View, item: Item) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerViewAdapter<Item>.ViewHolder>() {

    private val items = mutableListOf<Item>()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, layoutRes, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    fun setItems(newItems: List<Item>) {
        val diffUtilCallback = DiffUtilCallback(newItems)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        items.apply {
            clear()
            addAll(newItems)
        }
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            onBind?.invoke(itemView, item)
            onNormalBind?.invoke(binding, item)
        }
    }

    inner class DiffUtilCallback(private val newItems: List<Item>) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            items[oldItemPosition] == newItems[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            items[oldItemPosition] == newItems[newItemPosition]

        override fun getOldListSize(): Int = itemCount

        override fun getNewListSize(): Int = newItems.size
    }
}