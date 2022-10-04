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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun CheckCard(
    modifier: Modifier = Modifier,
    gap: Dp = 16.dp,
    padding: Dp = 12.dp,
    cornerRadius: Dp = 12.dp,
    successColor: Color = Color(0xFFA0ECAC),
    failedColor: Color = Color(0xFFF5B5B5),
    success: Boolean = false,
    onClick: () -> Unit,
    text: String = ""
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(if (success) successColor else failedColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true)
            ) { onClick() }
            .padding(padding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f, false),
            text = text,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = AppTheme.colors.black,
            style = AppTheme.typography.text
        )
        Spacer(modifier = Modifier.size(gap))
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = R.drawable.ic_forward),
            contentDescription = stringResource(
                id = R.string.forward
            ),
            tint = AppTheme.colors.gray1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CheckCardPreviewCheck() {
    AppTheme {
        CheckCard(
            text = "Наличие сопроводительных документов (ветеринарных справок \"Меркурий\", деклараций о соответствии), их соответствие маркировкам",
            onClick = {},
            success = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CheckCardPreviewUncheck() {
    AppTheme {
        CheckCard(
            text = "Наличие сопроводительных документов (ветеринарных справок \"Меркурий\", деклараций о соответствии), их соответствие маркировкам",
            onClick = {},
            success = false
        )
    }
}