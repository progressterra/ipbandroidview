package com.progressterra.ipbandroidview.model.partner

import android.os.Parcelable
import com.google.errorprone.annotations.Immutable
import com.progressterra.ipbandroidview.core.IsEmpty
import com.progressterra.ipbandroidview.model.Id
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
    val logoImageUrl: String = "",
    val miniImageUrl: String = ""
) : Id, IsEmpty, Parcelable {

    override fun isEmpty(): Boolean = this == Partner()
}