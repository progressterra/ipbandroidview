package com.progressterra.ipbandroidview.ui.referral

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ReferralLink
import com.progressterra.ipbandroidview.shared.ui.StateBox
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar

@Composable
fun ReferralScreen(
    state: ReferralState,
    interactor: ReferralInteractor
) {
    ThemedLayout(
        topBar = {
            ThemedTopAppBar(
                title = stringResource(R.string.referral_program),
                onBack = { interactor.onBack() }
            )
        }
    ) { _, _ ->
        StateBox(
            state = state.screenState,
            refresh = { interactor.refresh() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                ReferralLink(
                    promoCode = state.userInvite.promoCode,
                    onCopy = { interactor.copy() },
                    onShare = { interactor.share() }
                )
            }
        }
    }
}