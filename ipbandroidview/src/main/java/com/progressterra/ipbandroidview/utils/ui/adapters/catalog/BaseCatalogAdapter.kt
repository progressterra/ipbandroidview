package com.progressterra.ipbandroidview.utils.ui.adapters.catalog

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.ItemCategoryLibBinding
import com.progressterra.ipbandroidview.databinding.ItemCategorySubLibBinding
import com.progressterra.ipbandroidview.utils.ui.adapters.recycler.DataBindingRecyclerAdapter
import com.progressterra.ipbandroidview.utils.ui.adapters.recycler.DiffUtilCallback

class BaseCatalogAdapter(
    private val onSubItemClick: ((SubCategoryUILib) -> Unit),
    private val lifeCycleOwner: LifecycleOwner,
    private val onCategoryClick: ((position: Int, isExpanded: Boolean) -> Unit)? = null
) : ListAdapter<CategoryUILib, BaseCatalogAdapter.CategoryViewHolder>(DiffUtilCallback()) {

    private val scrollStates = hashMapOf<String, Parcelable>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryLibBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(
            binding = binding,
            lifecycleOwner = lifeCycleOwner,
            onSubItemClick = onSubItemClick,
            onCategoryClick = onCategoryClick
        )
    }

    override fun onViewRecycled(holder: CategoryViewHolder) {
        super.onViewRecycled(holder)

        val key = currentList[holder.absoluteAdapterPosition].title
        holder.layoutManager.onSaveInstanceState()?.let { scrollStates[key] = it }
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }

        val key = currentList[holder.absoluteAdapterPosition].title
        val state = scrollStates[key]

        if (state != null) {
            holder.layoutManager.onRestoreInstanceState(state)
        } else {
            holder.layoutManager.scrollToPosition(0)
        }
    }


    inner class CategoryViewHolder(
        private val binding: ItemCategoryLibBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val onSubItemClick: (SubCategoryUILib) -> Unit,
        private val onCategoryClick: ((position: Int, isExpanded: Boolean) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {

        val layoutManager =
            LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)

        private val adapter: DataBindingRecyclerAdapter<SubCategoryUILib, ItemCategorySubLibBinding> by lazy {
            DataBindingRecyclerAdapter(
                layoutRes = R.layout.item_category_sub_lib,
                lifecycleOwner = lifecycleOwner,
                onItemClick = onSubItemClick
            )
        }

        fun onBind(categoryUI: CategoryUILib) {
            with(binding) {
                item = categoryUI
                lifecycleOwner = lifecycleOwner

                rvSub.adapter = adapter
                rvSub.layoutManager = layoutManager

                adapter.submitList(categoryUI.subCategory)

                tvHint.visibility =
                    if (categoryUI.urlImage.isNullOrBlank()) View.VISIBLE else View.GONE

                rvSub.isVisible = categoryUI.expanded

                root.setOnClickListener {
                    if (categoryUI.subCategory.isEmpty()) {
                        onSubItemClick.invoke(
                            SubCategoryUILib(
                                id = categoryUI.id,
                                name = categoryUI.title,
                                urlImage = categoryUI.urlImage
                            )
                        )
                    } else {
                        onCategoryClick?.invoke(layoutPosition, !categoryUI.expanded)
                        categoryUI.expanded = !categoryUI.expanded
                        notifyItemChanged(layoutPosition)
                    }
                }
            }
        }
    }
}