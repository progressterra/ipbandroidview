package com.progressterra.ipbandroidview.pages.organizations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Organization
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

@Composable
fun OrganizationsScreen(
    state: OrganizationsScreenState, useComponent: UseOrganizationsScreen
) {
    ThemedLayout(topBar = {
        TopBar(title = stringResource(id = R.string.organizations), useComponent = useComponent)
    }) { _, _ ->
        StateColumn(state = state.screen, useComponent = useComponent) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.organizations) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(IpbTheme.colors.surface.asBrush())
                        .niceClickable {
                            useComponent.handle(OrganizationsScreenEvent.OnOrganization(it))
                        }
                        .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        SimpleImage(
                            modifier = Modifier
                                .size(width = 110.dp, height = 90.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            image = it.imageUrl
                        )
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            BrushedText(
                                text = it.name,
                                tint = IpbTheme.colors.textPrimary.asBrush(),
                                style = IpbTheme.typography.title2,
                                maxLines = 1
                            )
                            BrushedText(
                                text = it.address,
                                tint = IpbTheme.colors.textTertiary.asBrush(),
                                style = IpbTheme.typography.footnoteRegular,
                                maxLines = 1
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                BrushedIcon(
                                    resId = R.drawable.ic_audits,
                                    tint = IpbTheme.colors.iconTertiary.asBrush()
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                BrushedText(
                                    text = it.audits,
                                    tint = IpbTheme.colors.textTertiary.asBrush(),
                                    style = IpbTheme.typography.subHeadlineRegular
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                BrushedIcon(
                                    resId = R.drawable.ic_docs,
                                    tint = IpbTheme.colors.iconTertiary.asBrush()
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                BrushedText(
                                    text = it.documents,
                                    tint = IpbTheme.colors.textTertiary.asBrush(),
                                    style = IpbTheme.typography.subHeadlineRegular
                                )
                            }
                        }
                        BrushedIcon(
                            resId = R.drawable.ic_forward,
                            tint = IpbTheme.colors.iconSecondary.asBrush()
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun OrganizationsScreenPreview() {
    IpbTheme {
        OrganizationsScreen(
            state = OrganizationsScreenState(
                screen = StateColumnState(state = ScreenState.SUCCESS), organizations = listOf(
                    Organization(
                        id = "1",
                        name = "Organization 1",
                        address = "Address 1",
                        imageUrl = "https://picsum.photos/200/300",
                        audits = "10",
                        documents = "20"
                    ), Organization(
                        id = "2",
                        name = "Organization 2",
                        address = "Address 2",
                        imageUrl = "https://picsum.photos/200/300",
                        audits = "10",
                        documents = "20"
                    ), Organization(
                        id = "3",
                        name = "Organization 3",
                        address = "Address 3",
                        imageUrl = "https://picsum.photos/200/300",
                        audits = "10",
                        documents = "20"
                    ), Organization(
                        id = "4",
                        name = "Organization 4",
                        address = "Address 4",
                        imageUrl = "https://picsum.photos/200/300",
                        audits = "10",
                        documents = "20"
                    )
                )
            ), useComponent = UseOrganizationsScreen.Empty()
        )
    }
}