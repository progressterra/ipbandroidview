package com.progressterra.ipbandroidview.features.wantthiscard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.ProjectType
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.counter.Counter

@Composable
fun WantThisCard(
    modifier: Modifier = Modifier,
    state: WantThisCardState,
    useComponent: UseWantThisCard
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        SimpleImage(
            modifier = Modifier
                .size(157.dp)
                .clip(RoundedCornerShape(8.dp)),
            url = state.image,
            backgroundColor = IpbTheme.colors.background.asColor()
        )
        BrushedText(
            text = state.name,
            style = IpbTheme.typography.footnoteRegular,
            tint = IpbTheme.colors.textPrimary.asBrush(),
        )
        BrushedText(
            text = when (state.status) {
                TypeStatusDoc.NOT_FILL -> stringResource(R.string.request_not_fill)
                TypeStatusDoc.WAIT_IMAGE -> stringResource(R.string.request_wait_image)
                TypeStatusDoc.WAIT_REVIEW -> stringResource(R.string.request_wait_review)
                TypeStatusDoc.REJECTED -> stringResource(R.string.request_rejected)
                TypeStatusDoc.CONFIRMED -> stringResource(R.string.request_confirmed)
            },
            style = IpbTheme.typography.footnoteBold,
            tint = when (state.status) {
                TypeStatusDoc.NOT_FILL -> IpbTheme.colors.textTertiary.asBrush()
                TypeStatusDoc.WAIT_IMAGE -> IpbTheme.colors.textTertiary.asBrush()
                TypeStatusDoc.WAIT_REVIEW -> IpbTheme.colors.textTertiary.asBrush()
                TypeStatusDoc.REJECTED -> IpbTheme.colors.textPrimary2.asBrush()
                TypeStatusDoc.CONFIRMED -> IpbTheme.colors.onBackground.asBrush()
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.width(80.dp), verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                BrushedText(
                    text = state.price.toString(),
                    style = IpbTheme.typography.subHeadlineRegular,
                    tint = IpbTheme.colors.textPrimary2.asBrush(),
                )
                if (IpbAndroidViewSettings.PROJECT_TYPE == ProjectType.REDI) {
                    BrushedText(
                        text = "(${stringResource(R.string.installment)}: ${
                            state.installment.months
                        } ${stringResource(R.string.payments)} ${stringResource(R.string.po)} ${state.installment.perMonth}",
                        style = IpbTheme.typography.footnoteRegular,
                        tint = IpbTheme.colors.textPrimary.asBrush(),
                    )
                }
            }
            if (state.counter.isEmpty()) {
                IconButton(onClick = {
                    useComponent.handle(WantThisCardEvent(state.id))
                }) {
                    BrushedIcon(
                        resId = R.drawable.ic_cart, tint = IpbTheme.colors.iconPrimary.asBrush()
                    )
                }
            } else {
                Counter(
                    state = state.counter, useComponent = useComponent
                )
            }
        }
    }
}

