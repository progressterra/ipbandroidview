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
import com.progressterra.ipbandroidview.model.ChecklistStats
import com.progressterra.ipbandroidview.model.Document
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun DocumentCard(
    modifier: Modifier = Modifier,
    state: Document,
    openDocument: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(IpbTheme.shapes.medium)
            .background(if (state.isFinished()) IpbTheme.colors.surfaces else IpbTheme.colors.secondary)
            .niceClickable { openDocument() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.weight(1f, false)) {
            Text(
                text = state.name,
                color = IpbTheme.colors.black,
                style = IpbTheme.typography.text,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(2.dp))
            Text(
                text = state.address,
                color = IpbTheme.colors.gray2,
                style = IpbTheme.typography.tertiaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(8.dp))
            Stats(Modifier.fillMaxWidth(), state.stats)
        }
        Spacer(modifier = Modifier.size(8.dp))
        ForwardIcon()
    }
}

@Preview
@Composable
private fun AuditCardPreview() {
    IpbTheme {
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