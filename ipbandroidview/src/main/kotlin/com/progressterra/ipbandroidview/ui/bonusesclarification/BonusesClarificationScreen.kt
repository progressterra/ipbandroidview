package com.progressterra.ipbandroidview.ui.bonusesclarification

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ExpandableText
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

private val imageHeight = 72.dp

private val imageWidth = 264.dp

@Composable
fun BonusesClarificationScreen(
    state: BonusesClarificationState,
    interactor: BonusesClarificationInteractor
) {
    ThemedLayout(
        topBar = { ThemedTopAppBar(title = stringResource(R.string.faq), onBack = interactor::onBack) }
    ) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(AppTheme.dimensions.small),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
        ) {
            ExpandableText(
                text = stringResource(R.string.how_to_spend_short),
                expanded = state.howToSpendExpand,
                expand = interactor::expandHowToSpend
            ) {
                Text(
                    text = "${stringResource(R.string.how_to_spend_0)} ${stringResource(R.string.app_name)}.",
                    style = AppTheme.typography.text,
                    color = AppTheme.colors.gray1
                )
                Text(
                    text = stringResource(R.string.how_to_spend_1),
                    style = AppTheme.typography.text,
                    color = AppTheme.colors.gray1
                )
                Image(
                    modifier = Modifier.size(width = imageWidth, height = imageHeight),
                    painter = painterResource(R.drawable.bonuses_clarification),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.how_to_spend_2),
                    style = AppTheme.typography.text,
                    color = AppTheme.colors.gray1
                )
            }
            ExpandableText(
                text = stringResource(R.string.bonuses_ratio_short),
                expanded = state.ratioExpand,
                expand = interactor::expandRatio
            ) {
                Text(
                    text = stringResource(R.string.bonuses_ratio),
                    style = AppTheme.typography.text,
                    color = AppTheme.colors.gray1
                )
            }
            ExpandableText(
                text = stringResource(R.string.how_to_obtain_short),
                expanded = state.howToObtainExpand,
                expand = interactor::expandHowToObtain
            ) {
                Text(
                    text = stringResource(R.string.how_to_obtain),
                    style = AppTheme.typography.text,
                    color = AppTheme.colors.gray1
                )
            }
        }
    }
}

@Preview
@Composable
private fun BonusesClarificationScreenPreview() {
    AppTheme {
        BonusesClarificationScreen(
            state = BonusesClarificationState(
                howToSpendExpand = true, ratioExpand = true, howToObtainExpand = false
            ),
            interactor = BonusesClarificationInteractor.Empty()
        )
    }
}