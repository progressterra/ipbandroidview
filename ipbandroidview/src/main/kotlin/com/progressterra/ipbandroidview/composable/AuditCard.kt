package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AuditCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 12.dp,
    padding: Dp = 12.dp,
    verticalGap: Dp = 2.dp,
    horizontalGap: Dp = 8.dp,
    text: String = "",
    address: String = "",
    percentage: Int = 0,
    successPercentage: Int = 85,
    done: Boolean = false
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(AppTheme.colors.surfaces)
            .padding(padding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f, false)) {
            Text(
                text = text,
                color = AppTheme.colors.black,
                style = AppTheme.typography.text,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.size(verticalGap))
            Text(
                text = address,
                color = AppTheme.colors.gray2,
                style = AppTheme.typography.tertiaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (!done) {
            Spacer(modifier = Modifier.size(horizontalGap))
            Percentage(
                percentage = percentage,
                successPercentage = successPercentage
            )
            Icon(
                modifier = modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_forward),
                contentDescription = stringResource(
                    id = R.string.forward
                ),
                tint = AppTheme.colors.gray2
            )
        }
    }
}

@Preview
@Composable
private fun AuditCardPreviewEmpty() {
    AppTheme {
        AuditCard(
            text = "Ирина проводит аудит: «Наличие необходимого минимума продовольственных запасов на складе»",
            address = "пл Дружбы народов, 45, «Кофемания»",
            percentage = 15
        )
    }
}

@Preview
@Composable
private fun AuditCardPreviewZero() {
    AppTheme {
        AuditCard(
            text = "Ирина проводит аудит: «Наличие необходимого минимума продовольственных запасов на складе»",
            address = "пл Дружбы народов, 45, «Кофемания»"
        )
    }
}

@Preview
@Composable
private fun AuditCardPreviewFull() {
    AppTheme {
        AuditCard(
            text = "Ирина проводит аудит: «Наличие необходимого минимума продовольственных запасов на складе»",
            address = "пл Дружбы народов, 45, «Кофемания»",
            percentage = 89
        )
    }
}