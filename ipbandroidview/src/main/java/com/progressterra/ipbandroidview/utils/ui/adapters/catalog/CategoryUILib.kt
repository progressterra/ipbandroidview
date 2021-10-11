package com.progressterra.ipbandroidview.utils.ui.adapters.catalog

import android.os.Parcelable
import com.progressterra.ipbandroidview.utils.ui.adapters.recycler.IListItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryUILib(
    override val id: String,
    val title: String,
    val subCategory: List<SubCategoryUILib>,
    val urlImage: String?,
    var expanded: Boolean = false
) : IListItem, Parcelable