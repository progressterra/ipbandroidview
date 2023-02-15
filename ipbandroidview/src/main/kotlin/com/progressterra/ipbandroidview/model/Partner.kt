package com.progressterra.ipbandroidview.model

import android.os.Parcelable
import com.google.errorprone.annotations.Immutable
import com.progressterra.ipbandroidview.core.IsEmpty
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class Partner(
    override val id: String = "",
    val title: String = "",
    val description: String = "",
    val offerList: List<OfferUI> = emptyList(),
    val webSite: String = "",
    val phone: String = "",
    val headImageUrl: String = "",
    val logoImageUrl: String = ""
) : Id, IsEmpty, Parcelable {

    override fun isEmpty(): Boolean = this == Partner()
}