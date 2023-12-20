package com.progressterra.ipbandroidview.pages.organizationaudits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
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
fun OrganizationAuditsScreen(
    state: OrganizationAuditsState,
    useComponent: UseOrganizationAuditsScreen
) {
    ThemedLayout(topBar = {
        TopBar(
            title = stringResource(id = R.string.organization),
            showBackButton = true,
            useComponent = useComponent
        )
    }) { _, _ ->
        StateColumn(
            state = state.screen,
            useComponent = useComponent
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(IpbTheme.colors.surface.asBrush())
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        SimpleImage(
                            modifier = Modifier
                                .height(188.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp)),
                            image = state.imageUrl,
                            backgroundColor = IpbTheme.colors.surface.asColor()
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                BrushedText(
                                    text = state.organizationName,
                                    tint = IpbTheme.colors.textPrimary.asBrush(),
                                    style = IpbTheme.typography.title
                                )
                                BrushedText(
                                    text = state.organizationAddress,
                                    tint = IpbTheme.colors.textTertiary.asBrush(),
                                    style = IpbTheme.typography.footnoteRegular
                                )
                            }
                            IconButton(
                                onClick = { useComponent.handle(OrganizationAuditsEvent.OnMap) }
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    BrushedIcon(
                                        resId = R.drawable.ic_map,
                                        tint = IpbTheme.colors.primary.asBrush()
                                    )
                                    BrushedText(
                                        text = stringResource(id = R.string.map),
                                        tint = IpbTheme.colors.primary.asBrush(),
                                        style = IpbTheme.typography.footnoteRegular
                                    )
                                }
                            }
                        }
                    }
                }
                items(state.audits) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .niceClickable {
                                useComponent.handle(
                                    OrganizationAuditsEvent.OnAuditDetails(
                                        it
                                    )
                                )
                            }
                            .background(IpbTheme.colors.surface.asBrush())
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            BrushedText(
                                text = it.name,
                                tint = IpbTheme.colors.textPrimary.asBrush(),
                                style = IpbTheme.typography.title
                            )
                            Spacer(modifier = Modifier.size(4.dp))
                            BrushedText(
                                text = it.lastTime,
                                tint = IpbTheme.colors.textTertiary.asBrush(),
                                style = IpbTheme.typography.footnoteRegular
                            )
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            BrushedIcon(
                                resId = R.drawable.ic_forward,
                                tint = IpbTheme.colors.iconPrimary.asBrush()
                            )
                        }
                    }
                }
            }
        }
    }
}