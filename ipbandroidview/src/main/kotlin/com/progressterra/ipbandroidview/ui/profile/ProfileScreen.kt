package com.progressterra.ipbandroidview.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.EditIcon
import com.progressterra.ipbandroidview.composable.ForwardIcon
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.theme.IpbTheme

@Composable
fun ProfileScreen(
    state: ProfileState,
    interactor: ProfileInteractor,
    settings: ProfileSettings
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(
            title = stringResource(id = R.string.profile)
        )
    }) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(IpbTheme.shapes.medium)
                    .background(IpbTheme.colors.surfaces)
                    .niceClickable { interactor.openDetails() }
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = state.name.ifBlank { stringResource(id = R.string.set_name) },
                        color = IpbTheme.colors.black,
                        style = IpbTheme.typography.title
                    )
                    Text(
                        text = state.phone,
                        color = IpbTheme.colors.gray2,
                        style = IpbTheme.typography.secondaryText
                    )
                }
                IconButton(onClick = { interactor.openDetails() }) {
                    EditIcon()
                }
            }
            if (settings.showOrders)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(IpbTheme.shapes.medium)
                        .background(IpbTheme.colors.surfaces)
                        .niceClickable { interactor.onOrders() }
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.your_orders),
                        color = IpbTheme.colors.black,
                        style = IpbTheme.typography.text
                    )
                    IconButton(onClick = { interactor.onOrders() }) {
                        ForwardIcon()
                    }
                }
            if (settings.showFavorites)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(IpbTheme.shapes.medium)
                        .background(IpbTheme.colors.surfaces)
                        .niceClickable { interactor.onFavorites() }
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.favorites),
                        color = IpbTheme.colors.black,
                        style = IpbTheme.typography.text
                    )
                    IconButton(onClick = { interactor.onFavorites() }) {
                        ForwardIcon()
                    }
                }
            if (settings.showSupport)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(IpbTheme.shapes.medium)
                        .background(IpbTheme.colors.surfaces)
                        .niceClickable { interactor.onSupport() }
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.support),
                        color = IpbTheme.colors.black,
                        style = IpbTheme.typography.text
                    )
                    IconButton(onClick = { interactor.onSupport() }) {
                        ForwardIcon()
                    }
                }
            if (settings.showReferral)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(IpbTheme.shapes.medium)
                        .background(IpbTheme.colors.surfaces)
                        .niceClickable { interactor.onReferral() }
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.referral_program),
                        color = IpbTheme.colors.black,
                        style = IpbTheme.typography.text
                    )
                    IconButton(onClick = { interactor.onReferral() }) {
                        ForwardIcon()
                    }
                }
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    IpbTheme {
    }
}