package com.progressterra.ipbandroidview.ui.root

import androidx.compose.foundation.layout.RowScope
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.bottomnav.BottomMenuItem

data class RootState(
    val onBack: (() -> Unit)? = null,
    val title: String? = "",
    val actions: (RowScope.() -> Unit)? = null,
    val bottomBarVisibility: Boolean = false,
) {

    companion object Item {

        fun buildAudits(active: Boolean, count: Int = 0) = BottomMenuItem(
            id = "audits",
            titleId = R.string.audits,
            count = count,
            iconId = R.drawable.ic_audits
        )

        fun buildOrganizations(active: Boolean, count: Int = 0) = BottomMenuItem(
            id = "organizations",
            titleId = R.string.organizations,
            count = count,
            iconId = R.drawable.ic_organization
        )

        fun buildProfile(active: Boolean, count: Int = 0) = BottomMenuItem(
            id = "profile",
            titleId = R.string.profile,
            count = count,
            iconId = R.drawable.ic_profile
        )
    }
}