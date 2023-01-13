package com.progressterra.ipbandroidview.ui.partner

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.Offer
import com.progressterra.ipbandroidview.composable.SimpleImage
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun PartnerScreen(
    state: PartnerState,
    interactor: PartnerInteractor
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.partner), onBack = interactor::onBack)
    }) { _, _ ->
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (header, logo, description, offers, website, phone) = createRefs()
            SimpleImage(
                modifier = Modifier.constrainAs(header) {
                    width = Dimension.matchParent
                    top.linkTo(parent.top)
                },
                url = state.partner.headImageUrl,
                backgroundColor = AppTheme.colors.background
            )
            SimpleImage(
                modifier = Modifier.constrainAs(logo) {
                    start.linkTo(parent.start)
                    top.linkTo(header.bottom)
                    bottom.linkTo(header.bottom)
                },
                url = state.partner.logoImageUrl,
                backgroundColor = AppTheme.colors.background
            )
            Text(
                modifier = Modifier.constrainAs(description) {
                    width = Dimension.matchParent
                    top.linkTo(header.bottom)
                },
                text = state.partner.description,
                style = AppTheme.typography.text,
                color = AppTheme.colors.black
            )
            LazyRow(
                modifier = Modifier.constrainAs(offers) {
                    width = Dimension.matchParent
                    top.linkTo(description.bottom)
                }
            ) {
                items(state.partner.offerList) {
                    Offer(offerUI = it)
                }
            }
            Text(
                modifier = Modifier.constrainAs(phone) {
                    top.linkTo(offers.bottom)
                    start.linkTo(parent.start)
                },
                text = state.partner.phone,
                style = AppTheme.typography.text,
                color = AppTheme.colors.black
            )
            Text(
                modifier = Modifier.constrainAs(website) {
                    top.linkTo(phone.bottom)
                    start.linkTo(parent.start)
                },
                text = state.partner.webSite,
                style = AppTheme.typography.text,
                color = AppTheme.colors.black
            )
        }
    }
}