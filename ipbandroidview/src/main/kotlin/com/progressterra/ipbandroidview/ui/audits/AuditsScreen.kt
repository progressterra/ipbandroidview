package com.progressterra.ipbandroidview.ui.audits

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.AuditCard
import com.progressterra.ipbandroidview.composable.CategoryDivider
import com.progressterra.ipbandroidview.composable.ThemedButton
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AuditsScreen(state: AuditsState, interactor: AuditsInteractor) {
    Scaffold(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.audits))
    }) {
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
            val (list, button) = createRefs()
            LazyColumn(
                modifier = Modifier.constrainAs(list) {
                    width = Dimension.matchParent
                    height = Dimension.matchParent
                },
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.documents.filter { it.done }) {
                    AuditCard(
                        modifier = Modifier.fillMaxWidth(),
                        name = it.name,
                        address = it.address,
                        percentage = it.percentage,
                        done = it.done,
                        onClick = { interactor.onDocumentDetails(it.id) }
                    )
                }
                item {
                    CategoryDivider(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(id = R.string.completed_audits)
                    )
                }
                items(state.documents.filter { !it.done }) {
                    AuditCard(
                        modifier = Modifier.fillMaxWidth(),
                        name = it.name,
                        address = it.address,
                        percentage = it.percentage,
                        done = it.done,
                        onClick = { interactor.onDocumentDetails(it.id) }
                    )
                }
            }
            ThemedButton(
                modifier = Modifier.zIndex(1f).constrainAs(button) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(parent.bottom, 24.dp)
                },
                onClick = { interactor.onAudit() },
                text = stringResource(id = R.string.create_audit)
            )
        }
    }
}

@Preview
@Composable
private fun AuditsScreenPreview() {
    AppTheme {
        AuditsScreen(
            state = AuditsState(
                listOf(
                    Document("", "Some audit 1", false, "Lenina 13", 87),
                    Document("", "Some audit 2", false, "Lenina 13", 15),
                    Document("", "Some audit 3", true, "Lenina 13", 30),
                    Document("", "Some audit 4", true, "Lenina 13", 90),
                    Document("", "Some audit 5", false, "Lenina 13", 87),
                    Document("", "Some audit 6", true, "Lenina 13", 6),
                    Document("", "Some audit 7", true, "Lenina 13", 56),
                    Document("", "Some audit 8", true, "Lenina 13", 99)
                )
            ), interactor = AuditsInteractor.Empty()
        )
    }
}