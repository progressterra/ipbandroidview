package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.stats.ChecklistStats
import com.progressterra.ipbandroidview.composable.stats.Stats
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun DocumentCard(
    modifier: Modifier = Modifier,
    name: String = "",
    address: String = "",
    done: Boolean = false,
    stats: ChecklistStats,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.dimensions.mediumRounding))
            .background(AppTheme.colors.surfaces)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.weight(1f, false)) {
            Text(
                text = name,
                color = AppTheme.colors.black,
                style = AppTheme.typography.text,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(2.dp))
            Text(
                text = address,
                color = AppTheme.colors.gray2,
                style = AppTheme.typography.tertiaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(8.dp))
            Stats(Modifier.fillMaxWidth(), stats)
        }
        Spacer(modifier = Modifier.size(8.dp))
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = R.drawable.ic_forward),
            contentDescription = stringResource(
                id = R.string.forward
            ),
            tint = AppTheme.colors.gray2
        )

    }
}

@Preview
@Composable
private fun AuditCardPreviewEmpty() {
    AppTheme {
        DocumentCard(
            name = "Ирина проводит аудит: «Наличие необходимого минимума продовольственных запасов на складе»",
            address = "пл Дружбы народов, 45, «Кофемания»",
            onClick = {},
            stats = ChecklistStats(
                43, 15, 20, 8
            )
        )
    }
}

@Preview
@Composable
private fun AuditCardPreviewZero() {
    AppTheme {
        DocumentCard(
            name = "Ирина проводит аудит: «Наличие необходимого минимума продовольственных запасов на складе»",
            address = "пл Дружбы народов, 45, «Кофемания»",
            onClick = {},
            stats = ChecklistStats(
                43, 15, 20, 8
            )
        )
    }
}

@Preview
@Composable
private fun AuditCardPreviewFull() {
    AppTheme {
        DocumentCard(
            name = "Ирина проводит аудит: «Наличие необходимого минимума продовольственных запасов на складе»",
            address = "пл Дружбы народов, 45, «Кофемания»",
            onClick = {},
            stats = ChecklistStats(
                43, 15, 20, 8
            )
        )
    }
}