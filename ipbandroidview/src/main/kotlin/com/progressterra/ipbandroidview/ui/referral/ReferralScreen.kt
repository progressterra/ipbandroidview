package com.progressterra.ipbandroidview.ui.referral

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ReferralLink
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ReferralScreen(
    state: () -> ReferralState,
    onCopy: () -> Unit,
    onShare: () -> Unit,
    onRefresh: () -> Unit
) {
    ThemedLayout(
        topBar = {
            ThemedTopAppBar(title = stringResource(R.string.referral_program))
        }
    ) { _, _ ->
        StateBox(
            state = state()::screenState,
            refresh = onRefresh
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(AppTheme.dimensions.small)) {
                ReferralLink(
                    promoCode = state()::promoCode,
                    onCopy = onCopy,
                    onShare = onShare
                )
            }
        }
    }
}