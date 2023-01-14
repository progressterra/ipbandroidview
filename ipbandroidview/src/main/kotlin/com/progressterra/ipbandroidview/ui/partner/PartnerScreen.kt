package com.progressterra.ipbandroidview.ui.partner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.Offer
import com.progressterra.ipbandroidview.composable.SimpleImage
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.model.partner.Partner
import com.progressterra.ipbandroidview.theme.AppTheme
import de.charlex.compose.HtmlText

private val logoSize: Dp = 48.dp

private val bigMargin: Dp = 40.dp

@Composable
fun PartnerScreen(
    state: PartnerState, interactor: PartnerInteractor
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.partner), onBack = interactor::onBack)
    }) { _, _ ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val (header, logo, description, offers, website, phone) = createRefs()
            SimpleImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(header) {
                        top.linkTo(parent.top)
                    },
                url = state.partner.headImageUrl,
                backgroundColor = AppTheme.colors.background
            )
            val smallMargin = AppTheme.dimensions.small
            Row(
                modifier = Modifier
                    .clip(AppTheme.shapes.small)
                    .background(AppTheme.colors.surfaces)
                    .padding(AppTheme.dimensions.small)
                    .constrainAs(logo) {
                        start.linkTo(parent.start, smallMargin)
                        top.linkTo(header.bottom)
                        bottom.linkTo(header.bottom)
                    },
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SimpleImage(
                    modifier = Modifier.size(logoSize),
                    url = state.partner.logoImageUrl,
                    backgroundColor = AppTheme.colors.background
                )
                Text(
                    text = state.partner.title,
                    style = AppTheme.typography.title,
                    color = AppTheme.colors.black
                )
            }
            Row(modifier = Modifier
                .clip(AppTheme.shapes.small)
                .background(AppTheme.colors.surfaces)
                .padding(AppTheme.dimensions.small)
                .constrainAs(description) {
                    width = Dimension.fillToConstraints
                    top.linkTo(header.bottom, bigMargin)
                    start.linkTo(parent.start, smallMargin)
                    end.linkTo(parent.end, smallMargin)
                }) {
                HtmlText(
                    text = state.partner.description,
                    style = AppTheme.typography.text,
                    color = AppTheme.colors.black
                )
            }
            if (state.partner.offerList.isNotEmpty())
                LazyRow(
                    modifier = Modifier.constrainAs(offers) {
                        width = Dimension.matchParent
                        top.linkTo(description.bottom, smallMargin)
                    }
                ) {
                    items(state.partner.offerList) {
                        Offer(offerUI = it)
                    }
                }
            Row(modifier = Modifier
                .clip(AppTheme.shapes.small)
                .background(AppTheme.colors.surfaces)
                .padding(AppTheme.dimensions.small)
                .constrainAs(phone) {
                    width = Dimension.fillToConstraints
                    end.linkTo(parent.end, smallMargin)
                    top.linkTo(offers.bottom, smallMargin)
                    start.linkTo(parent.start, smallMargin)
                }) {
                Text(
                    text = "${stringResource(R.string.phone_number)}: ${state.partner.phone}",
                    style = AppTheme.typography.title,
                    color = AppTheme.colors.black
                )
            }
            Row(modifier = Modifier
                .clip(AppTheme.shapes.small)
                .background(AppTheme.colors.surfaces)
                .padding(AppTheme.dimensions.small)
                .constrainAs(website) {
                    width = Dimension.fillToConstraints
                    end.linkTo(parent.end, smallMargin)
                    top.linkTo(phone.bottom, smallMargin)
                    start.linkTo(parent.start, smallMargin)
                }) {
                Text(
                    text = "${stringResource(R.string.web_site)}: ${state.partner.webSite}",
                    style = AppTheme.typography.title,
                    color = AppTheme.colors.black
                )
            }
        }
    }
}

@Preview
@Composable
private fun PartnerScreenPreview() {
    AppTheme {
        PartnerScreen(
            state = PartnerState(
                Partner(
                    title = "SOMEEEE COOL",
                    description = "VEGAPEGLMFLE< GRKGORKGO KFELKF OKFEO KFEOFO KEFOKEOFKE NQIUSB IWYB FBI",
                    offerList = listOf(),
                    webSite = "www.qwertyuqwertyui.com",
                    phone = "89966977656",
                    headImageUrl = "",
                    logoImageUrl = "",
                    miniImageUrl = ""

                )
            ), interactor = PartnerInteractor.Empty()
        )
    }
}