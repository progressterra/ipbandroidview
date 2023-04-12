package com.progressterra.ipbandroidview.features.proshkabonuses

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun ProshkaBonuses(
    modifier: Modifier = Modifier,
    state: ProshkaBonusesState,
    style: ProshkaBonusesStyle = ProshkaBonusesStyle.NEXT,
    useComponent: UseProshkaBonuses
) {
    var rotated by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f, animationSpec = tween(500)
    )
    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f, animationSpec = tween(500)
    )
    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f, animationSpec = tween(500)
    )
    val animateColor by animateColorAsState(
        targetValue = if (rotated) IpbTheme.colors.surface.asColor() else IpbTheme.colors.secondaryPressed.asColor(),
        animationSpec = tween(500)
    )
    Row(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(animateColor)
        .graphicsLayer {
            rotationY = rotation
            cameraDistance = 8 * density
        }
        .graphicsLayer {
            alpha = if (rotated) animateBack else animateFront
            rotationY = rotation
        }
        .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        var height by remember { mutableStateOf(0.dp) }
        val density = LocalDensity.current
        Column(modifier = Modifier
            .onGloballyPositioned {
                with(density) { height = it.size.height.toDp() }
            }) {
            BrushedText(
                text = "${stringResource(R.string.you_have)} ${state.bonuses} ${stringResource(R.string.bonuses)}",
                style = IpbTheme.typography.title,
                tint = if (rotated) IpbTheme.colors.textPrimary.asBrush() else IpbTheme.colors.textButton.asBrush()
            )
            Spacer(modifier = Modifier.height(6.dp))
            BrushedText(
                text = "${stringResource(R.string.can_withdraw)} ${state.canWithdraw}",
                style = IpbTheme.typography.subHeadlineItalic,
                tint = IpbTheme.colors.textTertiary.asBrush()
            )
            Spacer(modifier = Modifier.height(14.dp))
            BrushedText(
                text = stringResource(R.string.bonuses_rate),
                style = IpbTheme.typography.subHeadlineRegular,
                tint = IpbTheme.colors.textSecondary.asBrush()
            )
            Spacer(modifier = Modifier.height(30.dp))
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
            } else {
                Row {
                    BrushedText(
                        text = "${stringResource(R.string.available_loan)} - ",
                        style = IpbTheme.typography.subHeadlineRegular,
                        tint = IpbTheme.colors.textButton.asBrush()
                    )
                    BrushedText(
                        text = state.loan,
                        style = IpbTheme.typography.subHeadlineBold,
                        tint = IpbTheme.colors.textButton.asBrush()
                    )
                }

            }
        }
        Box(modifier = Modifier.height(height)) {
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
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .zIndex(1f),
                onClick = { useComponent.handle(ProshkaBonusesEvent.Action) },
            ) {
                if (style == ProshkaBonusesStyle.NEXT) {
                    BrushedIcon(
                        resId = R.drawable.ic_arrow_pro,
                        tint = IpbTheme.colors.iconPrimary3.asBrush(),
                    )
                } else if (style == ProshkaBonusesStyle.PLUS) {
                    BrushedIcon(
                        resId = R.drawable.ic_plus_pro,
                        tint = IpbTheme.colors.iconPrimary3.asBrush(),
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun ProshkaBonusesPreview() {
    IpbTheme {
        ProshkaBonuses(
            state = ProshkaBonusesState(
                bonuses = "100",
                canWithdraw = "50",
                rate = "10",
                loan = "100",
                burningDate = "01.01.2021",
                burningQuantity = "50"
            ), useComponent = UseProshkaBonuses.Empty()
        )
    }
}

@Composable
@Preview
private fun ProshkaBonusesReversedPreview() {
    IpbTheme {
        ProshkaBonuses(
            state = ProshkaBonusesState(
                bonuses = "100",
                canWithdraw = "50",
                rate = "10",
                loan = "100",
                burningDate = "01.01.2021",
                burningQuantity = "50"
            ), useComponent = UseProshkaBonuses.Empty()
        )
    }
}