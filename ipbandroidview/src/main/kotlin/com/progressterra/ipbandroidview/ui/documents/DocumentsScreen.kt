package com.progressterra.ipbandroidview.ui.documents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.Divider
import com.progressterra.ipbandroidview.composable.DocumentCard
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.AppTheme
import kotlinx.coroutines.launch

private val tabHorizontalPadding = 26.dp

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Tab(
    modifier: Modifier = Modifier, pagerState: PagerState, index: Int, text: String
) {
    val scope = rememberCoroutineScope()
    val backgroundColor =
        if (pagerState.currentPage == index) AppTheme.colors.background else AppTheme.colors.surfaces
    Box(modifier = modifier
        .clip(
            AppTheme.shapes.small.copy(
                bottomStart = CornerSize(0), bottomEnd = CornerSize(0)
            )
        )
        .background(backgroundColor)
        .niceClickable {
            scope.launch {
                pagerState.animateScrollToPage(index)
            }
        }
        .padding(
            vertical = AppTheme.dimensions.small, horizontal = tabHorizontalPadding
        ), contentAlignment = Alignment.Center) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = AppTheme.colors.black,
            style = AppTheme.typography.text,
            maxLines = 1
        )
    }
}

/**
 * archive - button
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun DocumentsScreen(
    state: DocumentsState, interactor: DocumentsInteractor
) {
    val pagerState = rememberPagerState()
    ThemedLayout(topBar = {
        Column {
            ThemedTopAppBar(title = stringResource(id = R.string.audits))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppTheme.colors.surfaces)
                    .padding(top = AppTheme.dimensions.small),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                Tab(
                    text = stringResource(R.string.ongoing), pagerState = pagerState, index = 0
                )
                Tab(
                    text = stringResource(R.string.completed), pagerState = pagerState, index = 1
                )
            }
        }
    }) { _, _ ->
        StateBox(state = state.screenState, refresh = { interactor.refresh() }) {
            Column {
                Spacer(Modifier.height(AppTheme.dimensions.small))
                HorizontalPager(
                    count = 2, state = pagerState
                ) {
                    val notEnded = stringResource(R.string.not_ended)
                    val ended = stringResource(id = R.string.completed_audits)
                    val recentlyEnded = stringResource(id = R.string.recentley_ended)
                    val groupedRecentlyEndedDocs by remember(state.documents) {
                        mutableStateOf(state.documents.filter { it.isFinished() }.map {
                            it.copy(finishDate = recentlyEnded)
                        }.groupBy { it.finishDate!! })
                    }
                    val notEndedDocs by remember(state.documents) {
                        mutableStateOf(state.documents.filter { !it.isFinished() })
                    }
                    val groupedArchivedDocs by remember(state.archivedDocuments) {
                        mutableStateOf(state.archivedDocuments.map {
                            it.copy(finishDate = "$ended ${it.finishDate}")
                        }.groupBy { it.finishDate!! })
                    }
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(AppTheme.dimensions.small),
                        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
                    ) {
                        if (it == 0) {
                            if (notEndedDocs.isNotEmpty()) {
                                item {
                                    Divider(
                                        modifier = Modifier.fillMaxWidth(), title = notEnded
                                    )
                                }
                                items(notEndedDocs) { document ->
                                    DocumentCard(modifier = Modifier.fillMaxWidth(),
                                        state = document,
                                        openDocument = { interactor.openDocument(document) })
                                }
                            }
                            groupedRecentlyEndedDocs.forEach {
                                item {
                                    Divider(
                                        modifier = Modifier.fillMaxWidth(), title = it.key
                                    )
                                }
                                items(it.value) { document ->
                                    DocumentCard(modifier = Modifier.fillMaxWidth(),
                                        state = document,
                                        openDocument = { interactor.openDocument(document) })
                                }
                            }
                        } else groupedArchivedDocs.forEach {
                            item {
                                Divider(
                                    modifier = Modifier.fillMaxWidth(), title = it.key
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
        }
    }
}

@Preview
@Composable
private fun DocumentsScreenPreview() {
    AppTheme {
        DocumentsScreen(
            state = DocumentsState(
                documents = listOf(),
                archivedDocuments = listOf(),
                screenState = ScreenState.SUCCESS
            ), interactor = DocumentsInteractor.Empty()
        )
    }
}