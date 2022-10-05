package com.progressterra.ipbandroidview.ui.organizations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.AuditCard
import com.progressterra.ipbandroidview.composable.CategoryDivider
import com.progressterra.ipbandroidview.composable.TopAppBarWithBackNav
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AuditsScreen(
    state: AuditsState, interactor: AuditsInteractor
) {
    Scaffold(
        topBar = {
            TopAppBarWithBackNav(
                title = stringResource(id = R.string.audits)
            )
        }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background)
                .padding(
                    top = AppTheme.dimensions.small,
                    start = AppTheme.dimensions.small,
                    end = AppTheme.dimensions.small
                )
        ) {
            val (audits, button) = createRefs()
            LazyColumn(modifier = Modifier.constrainAs(audits) {
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }, verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
                items(state.ongoingAudits) {
                    AuditCard(
                        text = it.auditName,
                        address = it.address,
                        percentage = it.percentage,
                        done = false
                    )
                }
                item {
                    CategoryDivider(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.completed_audits)
                    )
                }
                items(state.completedAudits) {
                    AuditCard(
                        text = it.auditName,
                        address = it.address,
                        percentage = it.percentage,
                        done = true
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AuditsScreenPreview() {
    AppTheme {
        AuditsScreen(
            state = AuditsState(),
            interactor = AuditsInteractor.Empty()
        )
    }
}