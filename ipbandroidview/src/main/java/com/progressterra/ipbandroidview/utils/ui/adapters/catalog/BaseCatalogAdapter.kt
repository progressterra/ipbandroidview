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

class BaseCatalogAdapter(
    private val onSubItemClick: ((SubCategoryUILib) -> Unit),
    private val lifeCycleOwner: LifecycleOwner,
    private val onCategoryClick: ((position: Int, isExpanded: Boolean) -> Unit)? = null
) : ListAdapter<CategoryUILib, BaseCatalogAdapter.CategoryViewHolder>(DiffUtilCallback()) {
    private val rvPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryLibBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(
            binding = binding,
            lifecycleOwner = lifeCycleOwner,
            onSubItemClick = onSubItemClick,
            rvPool = rvPool,
            onCategoryClick = onCategoryClick
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }


    inner class CategoryViewHolder(
        private val binding: ItemCategoryLibBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val onSubItemClick: (SubCategoryUILib) -> Unit,
        private val rvPool: RecyclerView.RecycledViewPool,
        private val onCategoryClick: ((position: Int, isExpanded: Boolean) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {

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
                rvSub.setRecycledViewPool(rvPool)

                adapter.submitList(categoryUI.subCategory)

                tvHint.visibility =
                    if (categoryUI.urlImage.isNullOrBlank()) View.VISIBLE else View.GONE

                rvSub.isVisible = categoryUI.expanded

                root.setOnClickListener {
                    onCategoryClick?.invoke(layoutPosition, !categoryUI.expanded)

                    categoryUI.expanded = !categoryUI.expanded
                    notifyItemChanged(layoutPosition)
                }
            }
        }
    }
}