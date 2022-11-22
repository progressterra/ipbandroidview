package com.progressterra.ipbandroidview.ui.bonusesclarification

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.component.ThemedLayout
import com.progressterra.ipbandroidview.composable.component.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.element.ExpandableText
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun BonusesClarificationScreen(
    state: () -> BonusesClarificationState,
    back: () -> Unit,
    expandHowToSpend: () -> Unit,
    expandRatio: () -> Unit,
    expandHowToObtain: () -> Unit
) {
    ThemedLayout(
        topBar = { ThemedTopAppBar(title = stringResource(R.string.faq), onBack = back) }
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
                expanded = state()::howToSpendExpand,
                expand = expandHowToSpend
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
                expanded = state()::ratioExpand,
                expand = expandRatio
            ) {
                Text(
                    text = stringResource(R.string.bonuses_ratio),
                    style = AppTheme.typography.text,
                    color = AppTheme.colors.gray1
                )
            }
            ExpandableText(
                text = stringResource(R.string.how_to_obtain_short),
                expanded = state()::howToObtainExpand,
                expand = expandHowToObtain
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