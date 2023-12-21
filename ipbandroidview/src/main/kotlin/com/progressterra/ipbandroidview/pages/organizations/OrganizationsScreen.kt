package com.progressterra.ipbandroidview.pages.organizations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn

@Composable
fun OrganizationsScreen(
    state: OrganizationsState, useComponent: UseOrganizationsScreen
) {
    ThemedLayout(topBar = {
        TopBar(title = stringResource(id = R.string.organizations), useComponent = useComponent)
    }) { _, _ ->
        StateColumn(state = state.screen, useComponent = useComponent) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(state.organizations) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .niceClickable {
                                useComponent.handle(OrganizationsScreenEvent.OnOrganization(it))
                            }
                            .background(IpbTheme.colors.surface.asBrush())
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SimpleImage(
                            modifier = Modifier
                                .size(width = 90.dp, height = 110.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            image = it.imageUrl,
                            backgroundColor = IpbTheme.colors.surface.asColor()
                        )
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            BrushedText(
                                text = it.name,
                                tint = IpbTheme.colors.textPrimary.asBrush(),
                                style = IpbTheme.typography.body,
                                maxLines = 1
                            )
                            BrushedText(
                                text = it.address,
                                tint = IpbTheme.colors.textSecondary.asBrush(),
                                style = IpbTheme.typography.body,
                                maxLines = 1
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                BrushedIcon(
                                    resId = R.drawable.ic_audits,
                                    tint = IpbTheme.colors.iconSecondary.asBrush()
                                )
                                BrushedText(
                                    text = it.audits,
                                    tint = IpbTheme.colors.textSecondary.asBrush(),
                                    style = IpbTheme.typography.body2
                                )
                                BrushedIcon(
                                    resId = R.drawable.ic_docs,
                                    tint = IpbTheme.colors.iconSecondary.asBrush()
                                )
                                BrushedText(
                                    text = it.documents,
                                    tint = IpbTheme.colors.textSecondary.asBrush(),
                                    style = IpbTheme.typography.body2
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