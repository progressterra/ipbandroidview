package com.progressterra.ipbandroidview.ui.organizations

import com.google.errorprone.annotations.Immutable
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.checklist.Organization
import com.progressterra.ipbandroidview.model.partner.Partner

@Immutable
data class OrganizationsState(
    val organizations: List<Organization> = emptyList(),
    val partner: Partner = Partner(),
    val screenState: ScreenState = ScreenState.LOADING
)
