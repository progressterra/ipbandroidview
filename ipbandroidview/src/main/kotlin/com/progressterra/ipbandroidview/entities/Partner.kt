package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import com.google.errorprone.annotations.Immutable
import com.progressterra.ipbandroidview.shared.IsEmpty
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class Partner(
    override val id: String = "",
    val title: String = "",
    val description: String = "",
    val offerList: List<Offer> = emptyList(),
    val webSite: String = "",
    val phone: String = "",
    val headImageUrl: String = "",
    val logoImageUrl: String = ""
) : Id, IsEmpty, Parcelable {

    override fun isEmpty(): Boolean = this == Partner()
}