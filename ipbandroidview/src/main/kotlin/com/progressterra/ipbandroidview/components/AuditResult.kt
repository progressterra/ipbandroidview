package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AuditResult(
    modifier: Modifier = Modifier,
    score: Int = 0,
    maxScore: Int = 0,
    passed: Boolean = false,
    audit: String = "",
    worker: String = "",
    time: String = "",
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.dimensions.mediumRounding))
            .background(AppTheme.colors.surfaces)
            .padding(12.dp)
    ) {
        Text(
            text = "${stringResource(id = R.string.result)}: $score/$maxScore",
            color = if (passed) AppTheme.colors.primary else AppTheme.colors.error,
            style = AppTheme.typography.title
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = audit, color = AppTheme.colors.black, style = AppTheme.typography.text)
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "$worker, $time",
            color = AppTheme.colors.gray2,
            style = AppTheme.typography.secondaryText
        )
    }
}

@Preview
@Composable
private fun AuditResultPreviewSuccess() {
    AppTheme {
        AuditResult(
            score = 23,
            maxScore = 25,
            audit = "Входной контроль и условия хранения сырья",
            passed = true,
            worker = "Васильев Андрей",
            time = "два дня назад"
        )
    }
}

@Preview
@Composable
private fun AuditResultPreviewFailed() {
    AppTheme {
        AuditResult(
            score = 12,
            maxScore = 25,
            audit = "Входной контроль и условия хранения сырья",
            passed = false,
            worker = "Васильев Андрей",
            time = "два дня назад"
        )
    }
}