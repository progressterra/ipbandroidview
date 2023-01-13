package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.checklist.ChecklistStats
import com.progressterra.ipbandroidview.model.checklist.Document
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun DocumentCard(
    modifier: Modifier = Modifier,
    state: Document,
    openDocument: (Document) -> Unit
) {
    Row(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .background(if (state.isFinished()) AppTheme.colors.surfaces else AppTheme.colors.secondary)
            .niceClickable(onClick = { openDocument(state) })
            .padding(AppTheme.dimensions.medium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.weight(1f, false)) {
            Text(
                text = state.name,
                color = AppTheme.colors.black,
                style = AppTheme.typography.text,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(AppTheme.dimensions.tiniest))
            Text(
                text = state.address,
                color = AppTheme.colors.gray2,
                style = AppTheme.typography.tertiaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
            Stats(Modifier.fillMaxWidth(), state.stats)
        }
        Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
        ForwardIcon()
    }
}

@Preview
@Composable
private fun AuditCardPreview() {
    AppTheme {
        DocumentCard(
            state = Document(
                documentId = "",
                checklistId = "",
                placeId = "",
                name = "Some cool doc",
                address = "123 123 123",
                checkCounter = 10,
                finishDate = null,
                stats = ChecklistStats(
                    total = 11,
                    successful = 10,
                    failed = 120,
                    remaining = 10
                )
            ),
            openDocument = {}
        )
    }
}