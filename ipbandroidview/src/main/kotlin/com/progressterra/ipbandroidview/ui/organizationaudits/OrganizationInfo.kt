package com.progressterra.ipbandroidview.ui.organizationaudits

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrganizationInfo(
    val id: String,
    val imageUrl: String,
    val name: String,
    val address: String
) : Parcelable
