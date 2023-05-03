package com.progressterra.ipbandroidview.ui.agreement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.LinkText
import com.progressterra.ipbandroidview.composable.LinkTextData
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AgreementScreen(
    useComponent: UseAgreement
) {
    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .fillMaxSize()
    ) {
        ThemedTopAppBar(
            title = stringResource(R.string.user_agreement_title),
            onBack = { useComponent.onBack() })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(AppTheme.dimensions.small)
                .clip(AppTheme.shapes.medium)
                .background(AppTheme.colors.surfaces)
                .padding(AppTheme.dimensions.medium),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
        ) {
            Text(
                text = stringResource(R.string.user_agreement_0),
                color = AppTheme.colors.black,
                style = AppTheme.typography.title
            )
            Text(
                text = stringResource(R.string.user_agreement_1),
                color = AppTheme.colors.black,
                style = AppTheme.typography.text
            )
            Text(
                text = stringResource(R.string.user_agreement_2),
                color = AppTheme.colors.black,
                style = AppTheme.typography.text
            )
            Text(
                text = stringResource(R.string.user_agreement_3),
                color = AppTheme.colors.black,
                style = AppTheme.typography.text
            )
            Text(
                text = stringResource(R.string.user_agreement_4),
                color = AppTheme.colors.black,
                style = AppTheme.typography.text
            )
            LinkText(
                linkTextData = listOf(
                    LinkTextData(text = stringResource(id = R.string.user_agreement_5)),
                    LinkTextData(text = "info@progressterra.com",
                        tag = "offer",
                        annotation = "info@progressterra.com",
                        onClick = { useComponent.mailTo(it) })
                ),
                modifier = Modifier.padding(top = AppTheme.dimensions.small),
                style = AppTheme.typography.text,
                align = TextAlign.Start,
                color = AppTheme.colors.black
            )
        }
    }
}

@Preview
@Composable
private fun PhotoViewerPreview() {
    AppTheme {
        AgreementScreen(useComponent = UseAgreement.Empty())
    }
}