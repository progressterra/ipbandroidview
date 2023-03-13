package com.progressterra.ipbandroidview.ui.documents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.progressterra.ipbandroidview.composable.DocumentCard
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.AppTheme
import kotlinx.coroutines.launch

private val borderWidth = 1.dp

private val tabHorizontalPadding = 32.dp

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Tab(
    modifier: Modifier, selected: Boolean, pagerState: PagerState, index: Int, text: String
) {
    val scope = rememberCoroutineScope()
    val backgroundColor = if (selected) AppTheme.colors.primary else AppTheme.colors.surfaces

    val textColor = if (selected) AppTheme.colors.black else AppTheme.colors.gray1
    val style = if (selected) AppTheme.typography.text else AppTheme.typography.secondaryText
    Box(modifier = modifier
        .clip(AppTheme.shapes.small)
        .border(
            width = borderWidth,
            color = AppTheme.colors.background,
            shape = AppTheme.shapes.small
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
            text = text, color = textColor, style = style, textAlign = TextAlign.Center
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
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.audits))
    }) { _, _ ->
        StateBox(state = state.screenState, refresh = { interactor.refresh() }) {
            Column {
                val pagerState = rememberPagerState()
                Spacer(Modifier.height(AppTheme.dimensions.small))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = AppTheme.dimensions.small)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
                ) {
                    Tab(
                        modifier = Modifier.weight(1f),
                        selected = pagerState.currentPage == 0,
                        pagerState = pagerState,
                        index = 0,
                        text = stringResource(R.string.ongoing)
                    )
                    Tab(
                        modifier = Modifier.weight(1f),
                        selected = pagerState.currentPage == 1,
                        pagerState = pagerState,
                        index = 1,
                        text = stringResource(R.string.archived)
                    )
                }
                HorizontalPager(
                    count = 2, state = pagerState
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(AppTheme.dimensions.small),
                        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
                    ) {
                        items(if (it == 0) state.documents else state.archivedDocuments) {
                            DocumentCard(modifier = Modifier.fillMaxWidth(),
                                state = it,
                                openDocument = { interactor.openDocument(it) })
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
            ),
            interactor = DocumentsInteractor.Empty()
        )
    }
}