package com.progressterra.ipbandroidview.utils.ui.adapters.catalog

import android.os.Parcelable
import com.progressterra.ipbandroidview.utils.ui.adapters.recycler.IListItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubCategoryUILib(
    override val id: String,
    val name: String,
    val urlImage: String?
) : IListItem, Parcelable