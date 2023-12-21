package com.progressterra.ipbandroidview.pages.checklist

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.ChecklistStatus
import com.progressterra.ipbandroidview.entities.createStats
import com.progressterra.ipbandroidview.features.divider.Divider
import com.progressterra.ipbandroidview.features.stats.Stats
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChecklistScreen(
    state: ChecklistState, useComponent: UseChecklistScreen
) {
    val internalSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, skipHalfExpanded = true)
    val scope = rememberCoroutineScope()
    CurrentCheckDialog(
        state = state.currentCheckState,
        useComponent = useComponent,
        sheetState = internalSheetState
    ) {
        ThemedLayout(topBar = {
            TopBar(
                title = stringResource(id = R.string.audit),
                showBackButton = true,
                useComponent = useComponent
            )
        }, bottomBar = {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(8.dp)
            ) {
                when (state.status) {
                    ChecklistStatus.READ_ONLY -> Button(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(id = R.string.send),
                        state = state.sendButton,
                        useComponent = useComponent
                    )

                    ChecklistStatus.CAN_BE_STARTED -> Button(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(id = R.string.start_audit),
                        state = state.startButton,
                        useComponent = useComponent
                    )

                    ChecklistStatus.ONGOING -> Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            8.dp
                        )
                    ) {
                        val stats by remember(state.checks) { mutableStateOf(state.checks.createStats()) }
                        Button(
                            modifier = Modifier.weight(1f),
                            title = stringResource(id = R.string.end_audit),
                            state = state.finishButton,
                            useComponent = useComponent
                        )
                        Stats(modifier = Modifier.weight(1f), stats = stats)
                    }
                }
            }
        }, bottomOverlap = true) { _, bottomPadding ->
            StateColumn(
                modifier = Modifier.fillMaxSize(),
                state = state.screen,
                useComponent = useComponent
            ) {
                val groupedChecks by remember(state.checks) {
                    mutableStateOf(state.checks.groupBy { it.categoryNumber })
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(
                        start = 8.dp,
                        top = 8.dp,
                        end = 8.dp
                    )
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(IpbTheme.colors.surface.asBrush())
                                .padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            BrushedText(
                                text = state.auditDocument.name,
                                tint = IpbTheme.colors.textPrimary.asBrush(),
                                style = IpbTheme.typography.title2
                            )
                            BrushedText(
                                text = "${stringResource(id = R.string.questions)}: ${state.checks.size}",
                                tint = IpbTheme.colors.textTertiary.asBrush(),
                                style = IpbTheme.typography.footnoteRegular
                            )
                        }
                    }
                    groupedChecks.forEach { (_, checks) ->
                        item {
                            Divider(
                                modifier = Modifier.fillMaxWidth(),
                                title = checks.firstOrNull()?.printCategory()
                                    ?: stringResource(R.string.no_data)
                            )
                        }
                        items(checks) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (it.yesNo == true) IpbTheme.colors.success.asBrush() else if (it.yesNo == false) IpbTheme.colors.secondary.asBrush() else IpbTheme.colors.surface.asBrush())
                                    .niceClickable {
                                        useComponent.handle(ChecklistEvent.OnCheck(it))
                                        scope.launch { internalSheetState.show() }
                                    }
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                BrushedText(
                                    modifier = Modifier.weight(1f, false),
                                    text = it.name,
                                    maxLines = 2,
                                    tint = IpbTheme.colors.textPrimary.asBrush(),
                                    style = IpbTheme.typography.body
                                )
                                Spacer(modifier = Modifier.size(20.dp))
                                BrushedIcon(
                                    resId = R.drawable.ic_forward,
                                    tint = IpbTheme.colors.iconPrimary.asBrush()
                                )
                            }
                        }

                    }
                    item { Spacer(modifier = Modifier.size(bottomPadding)) }
                }
            }
        }
    }
}
