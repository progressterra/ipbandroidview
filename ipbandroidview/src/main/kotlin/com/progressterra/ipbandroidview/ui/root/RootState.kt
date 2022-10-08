package com.progressterra.ipbandroidview.ui.root

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.bottomnav.BottomMenuItem

data class RootState(
    val onBack: (() -> Unit)? = null,
    val title: String? = "",
    val actions: (@Composable RowScope.() -> Unit)? = null,
    val bottomBarVisibility: Boolean = false,
) {

    companion object Item {

        fun buildAudits(count: Int = 0) = BottomMenuItem(
            id = "audits",
            titleId = R.string.audits,
            count = count,
            iconId = R.drawable.ic_audits
        )

        fun buildOrganizations(count: Int = 0) = BottomMenuItem(
            id = "organizations",
            titleId = R.string.organizations,
            count = count,
            iconId = R.drawable.ic_organization
        )

        fun buildProfile(count: Int = 0) = BottomMenuItem(
            id = "profile",
            titleId = R.string.profile,
            count = count,
            iconId = R.drawable.ic_profile
        )
    }
}