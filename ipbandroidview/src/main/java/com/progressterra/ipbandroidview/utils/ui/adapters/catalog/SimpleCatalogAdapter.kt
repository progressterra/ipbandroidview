package com.progressterra.ipbandroidview.utils.ui.adapters.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.databinding.ItemCategoryUnfoldableLibBinding
import com.progressterra.ipbandroidview.utils.ui.adapters.recycler.DiffUtilCallback
import com.progressterra.ipbandroidview.utils.ui.adapters.recycler.ScrollStateHolder

class SimpleCatalogAdapter(
    private val onCategoryClick: ((CategoryUILib) -> Unit)
) : ListAdapter<CategoryUILib, SimpleCatalogAdapter.CategoryViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            binding = ItemCategoryUnfoldableLibBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onCategoryClick = onCategoryClick
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class CategoryViewHolder(
        private val binding: ItemCategoryUnfoldableLibBinding,
        private val onCategoryClick: ((CategoryUILib) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root), ScrollStateHolder.ScrollStateKeyProvider {

        private var currentId: String? = null

        override fun getScrollStateKey(): String? = currentId

        fun onBind(categoryUI: CategoryUILib) {
            with(binding) {
                currentId = categoryUI.id
                item = categoryUI
                lifecycleOwner = lifecycleOwner
                root.setOnClickListener { onCategoryClick.invoke(categoryUI) }
            }
        }
    }
}