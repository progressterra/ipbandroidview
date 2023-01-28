package com.progressterra.ipbandroidview.ui.organizationedit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrganizationEditScreen(
    state: OrganizationEditState,
    interactor: OrganizationEditInteractor
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = sheetState, sheetShape = AppTheme.shapes.dialog, sheetContent = {

            state.currentEmployee?.let {
                Column(
                    modifier = Modifier.padding(
                        top = AppTheme.dimensions.small,
                        start = AppTheme.dimensions.small,
                        end = AppTheme.dimensions.small,
                        bottom = 25.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .clip(AppTheme.shapes.medium)
                            .background(AppTheme.colors.surfaces)
                            .padding(AppTheme.dimensions.medium)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
                    ) {

                    }
                }
            }
        }, sheetBackgroundColor = AppTheme.colors.surfaces
    ) {
        ThemedLayout(topBar = {
            ThemedTopAppBar(
                onBack = { interactor.onBack() }, title = stringResource(id = R.string.audit)
            )
        }) { _, _ ->

        }
    }
}