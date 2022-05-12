package com.progressterra.ipbandroidview.utils.ui.adapters.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.databinding.ItemSubcategoryLibBinding
import com.progressterra.ipbandroidview.utils.ui.adapters.recycler.DiffUtilCallback
import com.progressterra.ipbandroidview.utils.ui.adapters.recycler.ScrollStateHolder

class SubCategoryAdapter(
    private val onSubcategoryClick: ((SubCategoryUILib) -> Unit)
) : ListAdapter<SubCategoryUILib, SubCategoryAdapter.SubCategoryViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        return SubCategoryViewHolder(
            binding = ItemSubcategoryLibBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onSubcategoryClick = onSubcategoryClick
        )
    }

    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    class SubCategoryViewHolder(
        private val binding: ItemSubcategoryLibBinding,
        private val onSubcategoryClick: (SubCategoryUILib) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), ScrollStateHolder.ScrollStateKeyProvider {
        private var currentId: String? = null

        override fun getScrollStateKey(): String? = currentId

        fun onBind(subCategoryUI: SubCategoryUILib) {
            with(binding) {
                currentId = subCategoryUI.id
                item = subCategoryUI
                root.setOnClickListener {
                    onSubcategoryClick.invoke(subCategoryUI)
                }
            }
        }
    }
}