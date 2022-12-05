package com.progressterra.ipbandroidview.ui.documents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.component.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.component.DocumentCard
import com.progressterra.ipbandroidview.composable.component.ThemedLayout
import com.progressterra.ipbandroidview.composable.element.StateBox
import com.progressterra.ipbandroidview.composable.element.ThemedButton
import com.progressterra.ipbandroidview.model.Document
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun DocumentsScreen(
    state: () -> DocumentsState,
    refresh: () -> Unit,
    openArchive: () -> Unit,
    openDocument: (Document) -> Unit
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.audits))
    }) { _, _ ->
        StateBox(
            modifier = Modifier
                .fillMaxSize(),
            state = state()::screenState,
            refresh = refresh
        ) {
            var buttonSize by remember { mutableStateOf(0.dp) }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 8.dp, start = 8.dp, end = 8.dp
                    ), verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
            ) {
                items(state().documents) {
                    DocumentCard(
                        modifier = Modifier.fillMaxWidth(),
                        state = { it },
                        openDocument = openDocument
                    )
                }
                item {
                    Spacer(modifier = Modifier.size(buttonSize + 24.dp))
                }
            }
            val density = LocalDensity.current
            ThemedButton(
                modifier = Modifier
                    .zIndex(1f)
                    .align(Alignment.BottomCenter)
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        buttonSize = with(density) { it.size.height.toDp() }
                    },
                onClick = openArchive,
                text = stringResource(id = R.string.to_archive)
            )
        }
    }
}

@Preview
@Composable
private fun DocumentsScreenPreview() {
    AppTheme {
    }
}