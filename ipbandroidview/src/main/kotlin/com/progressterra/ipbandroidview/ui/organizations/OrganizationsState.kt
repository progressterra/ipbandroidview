package com.progressterra.ipbandroidview.ui.organizations

import com.google.errorprone.annotations.Immutable

@Immutable
data class OrganizationsState(
    val organizations: List<Organization> = emptyList()
)
