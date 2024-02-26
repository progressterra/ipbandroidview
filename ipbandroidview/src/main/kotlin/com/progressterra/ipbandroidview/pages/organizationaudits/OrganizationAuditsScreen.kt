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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.OrganizationAudit
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
fun OrganizationAuditsScreen(
    state: OrganizationAuditsScreenState,
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
                contentPadding = PaddingValues(16.dp)
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
                            image = state.imageUrl
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
                                onClick = { useComponent.handle(OrganizationAuditsScreenEvent.OnMap) }
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
                            .background(IpbTheme.colors.surface.asBrush())
                            .niceClickable {
                                useComponent.handle(
                                    OrganizationAuditsScreenEvent.OnAuditDetails(
                                        it
                                    )
                                )
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            BrushedText(
                                text = it.name,
                                tint = IpbTheme.colors.textPrimary.asBrush(),
                                style = IpbTheme.typography.title2
                            )
                            Spacer(modifier = Modifier.size(6.dp))
                            BrushedText(
                                text = it.lastTime,
                                tint = IpbTheme.colors.textTertiary.asBrush(),
                                style = IpbTheme.typography.footnoteRegular
                            )
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            BrushedIcon(
                                modifier = Modifier.size(width = 10.dp, height = 17.dp),
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

@Preview
@Composable
private fun OrganizationAuditsScreenPreview() {
    IpbTheme {
        OrganizationAuditsScreen(
            state = OrganizationAuditsScreenState(
                screen = StateColumnState(state = ScreenState.SUCCESS),
                organizationName = "Organization name",
                organizationAddress = "Organization address",
                imageUrl = "https://picsum.photos/200/300",
                audits = listOf(
                    OrganizationAudit(
                        id = "1",
                        name = "Audit 1",
                        lastTime = "Last time 1"
                    ),
                    OrganizationAudit(
                        id = "2",
                        name = "Audit 2",
                        lastTime = "Last time 2"
                    ),
                    OrganizationAudit(
                        id = "3",
                        name = "Audit 3",
                        lastTime = "Last time 3"
                    ),
                    OrganizationAudit(
                        id = "4",
                        name = "Audit 4",
                        lastTime = "Last time 4"
                    ),
                    OrganizationAudit(
                        id = "5",
                        name = "Audit 5",
                        lastTime = "Last time 5"
                    ),
                    OrganizationAudit(
                        id = "6",
                        name = "Audit 6",
                        lastTime = "Last time 6"
                    ),
                    OrganizationAudit(
                        id = "7",
                        name = "Audit 7",
                        lastTime = "Last time 7"
                    )
                )
            ),
            useComponent = UseOrganizationAuditsScreen.Empty()
        )
    }
}
