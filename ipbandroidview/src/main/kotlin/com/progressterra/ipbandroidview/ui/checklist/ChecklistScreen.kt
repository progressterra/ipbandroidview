package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.AuditTitle
import com.progressterra.ipbandroidview.composable.BottomHolder
import com.progressterra.ipbandroidview.composable.CheckCard
import com.progressterra.ipbandroidview.composable.Divider
import com.progressterra.ipbandroidview.shared.ui.StateBox
import com.progressterra.ipbandroidview.composable.Stats
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.component.Button
import com.progressterra.ipbandroidview.composable.component.ButtonStyle
import com.progressterra.ipbandroidview.ext.createStats
import com.progressterra.ipbandroidview.model.ChecklistStatus
import kotlinx.coroutines.launch

/**
 * commentary - text field
 * finish, start, ready, send - buttons
 * main - current check
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChecklistScreen(
    state: ChecklistState, interactor: ChecklistInteractor
) {
    val internalSheetState =
        rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, skipHalfExpanded = true)
    val scope = rememberCoroutineScope()
    CurrentCheckDialog(
        id = "main",
        state = state.currentCheckState,
        useComponent = interactor,
        sheetState = internalSheetState
    ) {
        ThemedLayout(topBar = {
            ThemedTopAppBar(
                onBack = { interactor.onBack() }, title = stringResource(id = R.string.audit)
            )
        }, bottomBar = {
            BottomHolder {
                when (state.status) {
                    ChecklistStatus.READ_ONLY -> Button(
                        modifier = Modifier.fillMaxWidth(),
                        id = "send",
                        state = state.sendButton,
                        useComponent = interactor
                    )
                    ChecklistStatus.CAN_BE_STARTED -> Button(
                        modifier = Modifier.fillMaxWidth(),
                        id = "start",
                        state = state.startButton,
                        useComponent = interactor
                    )
                    ChecklistStatus.ONGOING -> Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            8.dp
                        )
                    ) {
                        val stats by remember(state.checks) { mutableStateOf(state.checks.createStats()) }
                        Button(
                            modifier = Modifier.weight(1f),
                            id = "finish",
                            state = state.finishButton,
                            style = if (stats.remaining >= 1) ButtonStyle.Secondary else ButtonStyle.Primary,
                            useComponent = interactor
                        )
                        Stats(modifier = Modifier.weight(1f), stats = stats)
                    }
                }
            }
        }, bottomOverlap = true) { _, bottomPadding ->
            StateBox(modifier = Modifier.fillMaxSize(),
                state = state.screenState,
                refresh = { interactor.refreshChecklist() }) {
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
                        AuditTitle(
                            modifier = Modifier.fillMaxWidth(),
                            name = state.auditDocument.name,
                            checkCounter = state.checks.size
                        )
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
                            CheckCard(modifier = Modifier.fillMaxWidth(), onClick = {
                                interactor.openCheck(it)
                                scope.launch { internalSheetState.show() }
                            }, state = it)
                        }
                    }
                    item { Spacer(modifier = Modifier.size(bottomPadding)) }
                }
            }
        }
    }
}
