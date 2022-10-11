package com.progressterra.ipbandroidview.ui.auditchecks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.composable.AuditTitle
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AuditChecksScreen(state: AuditChecksState, interactor: AuditChecksInteractor) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .padding(
                top = AppTheme.dimensions.small,
                start = AppTheme.dimensions.small,
                end = AppTheme.dimensions.small
            )
    ) {
        item {
            AuditTitle(
                modifier = Modifier.fillMaxWidth(),
                name = state.name,
                repetitiveness = state.repetitiveness,
                lastTimeChecked = state.lastTimeChecked,
                checkCounter = state.checkCounter
            )
        }

    }
}