package com.progressterra.ipbandroidview.features.bonuses

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun Bonuses(
    modifier: Modifier = Modifier,
    state: BonusesState,
    style: BonusesStyle = BonusesStyle.MAIN,
    useComponent: UseBonuses
) {
    var rotated by remember { mutableStateOf(false) }
    val animateColor by animateColorAsState(
        label = "bonuses color",
        targetValue = if (rotated) IpbTheme.colors.surface.asColor() else IpbTheme.colors.secondaryPressed.asColor(),
        animationSpec = tween(500)
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(animateColor)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            BrushedText(
                text = "${stringResource(R.string.you_have)} ${state.roubles} ${
                    stringResource(
                        R.string.roubles
                    )
                }",
                style = IpbTheme.typography.title,
                tint = if (rotated) IpbTheme.colors.textPrimary.asBrush() else IpbTheme.colors.textButton.asBrush()
            )
            Spacer(modifier = Modifier.height(6.dp))
            BrushedText(
                text = "${stringResource(R.string.you_have)} ${state.bonuses} ${
                    stringResource(
                        R.string.bonuses
                    )
                }",
                style = IpbTheme.typography.subHeadlineItalic,
                tint = IpbTheme.colors.textTertiary.asBrush()
            )
            if (rotated) {
                Row {
                    BrushedText(
                        text = state.burningDate,
                        style = IpbTheme.typography.subHeadlineRegular,
                        tint = IpbTheme.colors.textPrimary2.asBrush()
                    )
                    BrushedText(
                        text = " ${stringResource(R.string.will_burn)} ${state.burningQuantity} ${
                            stringResource(
                                R.string.bonuses
                            )
                        }",
                        style = IpbTheme.typography.subHeadlineBold,
                        tint = IpbTheme.colors.textPrimary2.asBrush()
                    )
                }
            }
        }
        Box(modifier = Modifier.height(160.dp)) {
            IconButton(
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.TopCenter)
                    .zIndex(1f),
                onClick = { rotated = !rotated },
            ) {
                if (rotated) {
                    BrushedIcon(
                        resId = R.drawable.ic_money_bag_off,
                        tint = IpbTheme.colors.iconDisabled.asBrush(),
                    )
                } else {
                    BrushedIcon(
                        resId = R.drawable.ic_money_bag,
                        tint = IpbTheme.colors.iconTertiary4.asBrush(),
                    )
                }
            }
            if (style == BonusesStyle.MAIN) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .zIndex(1f),
                    onClick = { useComponent.handle(BonusesEvent) },
                ) {
                    BrushedIcon(
                        resId = R.drawable.ic_arrow,
                        tint = IpbTheme.colors.primary.asBrush(),
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun BonusesPreview() {
    IpbTheme {
        Bonuses(
            state = BonusesState(
                roubles = "100",
                burningDate = "01.01.2021",
                burningQuantity = "50",
                bonuses = "500"
            ), useComponent = UseBonuses.Empty()
        )
    }
}