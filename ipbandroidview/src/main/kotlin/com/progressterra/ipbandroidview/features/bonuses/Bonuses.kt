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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.ProjectType
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun Bonuses(
    modifier: Modifier = Modifier,
    state: BonusesState,
    style: BonusesStyle = BonusesStyle.MAIN,
    useComponent: UseBonuses
) {
    when (IpbAndroidViewSettings.PROJECT_TYPE) {
        ProjectType.REDI -> {
            var rotated by remember { mutableStateOf(false) }
            val animateColor by animateColorAsState(
                targetValue = if (rotated) IpbTheme.colors.surface.asColor() else IpbTheme.colors.secondaryPressed.asColor(),
                animationSpec = tween(500)
            )

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(animateColor)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                var height by remember { mutableStateOf(0.dp) }
                val density = LocalDensity.current
                Column(modifier = Modifier.onGloballyPositioned {
                    with(density) { height = it.size.height.toDp() }
                }) {
                    BrushedText(
                        text = "${stringResource(R.string.you_have)} ${state.bonuses} ${
                            stringResource(
                                R.string.bonuses
                            )
                        }",
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
                    if (style == BonusesStyle.MAIN) {
                        IconButton(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .zIndex(1f),
                            onClick = { useComponent.handle(BonusesEvent.Action) },
                        ) {
                            BrushedIcon(
                                resId = R.drawable.ic_arrow,
                                tint = IpbTheme.colors.iconPrimary3.asBrush(),
                            )
                        }
                    }
                }
            }
        }

        ProjectType.WHITELABEL -> {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(IpbTheme.colors.primary.asBrush())
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    BrushedText(
                        text = "${stringResource(R.string.you_have)} ${state.bonuses} ${
                            stringResource(
                                R.string.bonuses
                            )
                        }",
                        style = IpbTheme.typography.title,
                        tint = IpbTheme.colors.textPrimary.asBrush()
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BrushedIcon(
                            resId = R.drawable.ic_fire,
                            tint = IpbTheme.colors.iconTertiary4.asBrush(),
                        )
                        BrushedText(
                            text = "${state.burningDate} ${stringResource(R.string.will_burn)} ${state.burningQuantity} ${
                                stringResource(R.string.bonuses)
                            }",
                            style = IpbTheme.typography.subHeadlineBold,
                            tint = IpbTheme.colors.textPrimary2.asBrush()
                        )
                    }
                }
                IconButton(
                    modifier = Modifier.zIndex(1f),
                    onClick = { useComponent.handle(BonusesEvent.Action) },
                ) {
                    BrushedIcon(
                        resId = R.drawable.ic_arrow,
                        tint = IpbTheme.colors.iconPrimary3.asBrush(),
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
                bonuses = "100",
                canWithdraw = "50",
                rate = "10",
                loan = "100",
                burningDate = "01.01.2021",
                burningQuantity = "50"
            ), useComponent = UseBonuses.Empty()
        )
    }
}