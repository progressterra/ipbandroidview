package com.progressterra.ipbandroidview.utils.ui.adapters.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.ItemCategoryLibBinding
import com.progressterra.ipbandroidview.databinding.ItemCategorySubLibBinding
import com.progressterra.ipbandroidview.utils.ui.adapters.recycler.DataBindingRecyclerAdapter
import com.progressterra.ipbandroidview.utils.ui.adapters.recycler.DiffUtilCallback
import com.progressterra.ipbandroidview.utils.ui.adapters.recycler.ScrollStateHolder

class BaseCatalogAdapter(
    private val onSubItemClick: ((SubCategoryUILib) -> Unit),
    private val lifeCycleOwner: LifecycleOwner,
    private val onCategoryClick: ((position: Int, isExpanded: Boolean) -> Unit)? = null,
    private val scrollStateHolder: ScrollStateHolder? = null
) : ListAdapter<CategoryUILib, BaseCatalogAdapter.CategoryViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryLibBinding.inflate(layoutInflater, parent, false)
        val vh = CategoryViewHolder(
            binding = binding,
            lifecycleOwner = lifeCycleOwner,
            onSubItemClick = onSubItemClick,
            onCategoryClick = onCategoryClick
        )

        vh.onCreated()

        return vh
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onViewRecycled(holder: CategoryViewHolder) {
        super.onViewRecycled(holder)
        holder.onRecycled()
    }

    inner class CategoryViewHolder(
        private val binding: ItemCategoryLibBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val onSubItemClick: (SubCategoryUILib) -> Unit,
        private val onCategoryClick: ((position: Int, isExpanded: Boolean) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root), ScrollStateHolder.ScrollStateKeyProvider {

        private var currentItem: CategoryUILib? = null

        private val adapter: DataBindingRecyclerAdapter<SubCategoryUILib, ItemCategorySubLibBinding> by lazy {
            DataBindingRecyclerAdapter(
                layoutRes = R.layout.item_category_sub_lib,
                lifecycleOwner = lifecycleOwner,
                onItemClick = onSubItemClick
            )
        }

        override fun getScrollStateKey(): String? = currentItem?.title

        fun onBind(categoryUI: CategoryUILib) {
            with(binding) {
                currentItem = categoryUI

                item = categoryUI
                lifecycleOwner = lifecycleOwner

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

            scrollStateHolder?.restoreScrollState(binding.rvSub, this)
        }

        fun onCreated() {
            binding.rvSub.adapter = adapter
            scrollStateHolder?.setupRecyclerView(binding.rvSub, this)
        }

        fun onRecycled() {
            scrollStateHolder?.saveScrollState(binding.rvSub, this)
            currentItem = null
        }
    }
}