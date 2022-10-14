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
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.yesno.YesNo
import com.progressterra.ipbandroidview.theme.AppTheme

//TODO Colors to composable

@Composable
fun CheckCard(
    modifier: Modifier = Modifier,
    successColor: Color = Color(0xFFA0ECAC),
    failedColor: Color = Color(0xFFF5B5B5),
    yesNo: YesNo,
    onClick: () -> Unit,
    name: String = ""
) {
    Row(modifier = modifier
        .clip(RoundedCornerShape(AppTheme.dimensions.mediumRounding))
        .background(if (yesNo == YesNo.YES) successColor else if (yesNo == YesNo.NO) failedColor else AppTheme.colors.surfaces)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = true)
        ) { onClick() }
        .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            modifier = Modifier.weight(1f, false),
            text = name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = AppTheme.colors.black,
            style = AppTheme.typography.text
        )
        Spacer(modifier = Modifier.size(16.dp))
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
private fun CheckCardPreviewOngoing() {
    AppTheme {
        CheckCard(
            name = "Наличие сопроводительных документов (ветеринарных справок \"Меркурий\", деклараций о соответствии), их соответствие маркировкам",
            onClick = {},
            yesNo = YesNo.YES
        )
    }
}