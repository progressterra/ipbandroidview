package com.progressterra.ipbandroidview.ui.archive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.Divider
import com.progressterra.ipbandroidview.composable.DocumentCard
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ArchiveScreen(
    state: ArchiveState, interactor: ArchiveInteractor
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = state.title, onBack = { interactor.onBack() })
    }) { _, _ ->
        val notEnded = stringResource(R.string.not_ended)
        val finishedGroupedDocs by remember(state.documents) {
            mutableStateOf(state.documents.map { it.copy(finishDate = it.finishDate ?: notEnded) }
                .groupBy { it.finishDate!! })
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(AppTheme.dimensions.small),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
        ) {
            finishedGroupedDocs.forEach {
                item {
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        title = "${stringResource(id = R.string.completed_audits)} ${it.key}"
                    )
                }
                items(it.value) { document ->
                    DocumentCard(modifier = Modifier.fillMaxWidth(),
                        state = document,
                        openDocument = { interactor.openDocument(document) })
                }
            }
        }
    }
}

@Preview
@Composable
private fun DocumentsScreenPreview() {
    AppTheme {}
}