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
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.Icon
import com.progressterra.ipbandroidview.shared.ui.Image
import com.progressterra.ipbandroidview.shared.ui.Text
import com.progressterra.ipbandroidview.shared.ui.counter.Counter
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable

@Composable
fun WantThisCard(
    modifier: Modifier = Modifier,
    state: WantThisCardState,
    useComponent: UseWantThisCard
) {
    Row(
        modifier =
        modifier
            .width(157.dp)
            .clip(RoundedCornerShape(8.dp))
            .niceClickable {
                useComponent.handle(WantThisCardEvent.Open(state.document))
            },
        horizontalArrangement = Arrangement.spacedBy(8.dp)
        //verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        //Image(modifier = Modifier.size(157.dp).clip(RoundedCornerShape(8.dp)), image = state.image)
        Icon(
            modifier =
            Modifier.size(width = 97.dp, height = 97.dp).clip(RoundedCornerShape(8.dp)),
            resId = R.drawable.ic_docs,
            tint = IpbTheme.colors.iconPrimary.asBrush()
        )
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {


            Text(
                text = state.name,
                style = IpbTheme.typography.footnoteRegular,
                tint = IpbTheme.colors.textPrimary.asBrush(),
            )
            Text(
                text =
                when (state.status) {
                    TypeStatusDoc.NOT_FILL -> stringResource(R.string.request_not_fill)
                    TypeStatusDoc.WAIT_IMAGE -> stringResource(R.string.request_wait_image)
                    TypeStatusDoc.WAIT_REVIEW -> stringResource(R.string.request_wait_review)
                    TypeStatusDoc.REJECTED -> stringResource(R.string.request_rejected)
                    TypeStatusDoc.CONFIRMED -> stringResource(R.string.request_confirmed)
                },
                style = IpbTheme.typography.footnoteBold,
                tint =
                when (state.status) {
                    TypeStatusDoc.NOT_FILL -> IpbTheme.colors.textTertiary.asBrush()
                    TypeStatusDoc.WAIT_IMAGE -> IpbTheme.colors.textTertiary.asBrush()
                    TypeStatusDoc.WAIT_REVIEW -> IpbTheme.colors.textTertiary.asBrush()
                    TypeStatusDoc.REJECTED -> IpbTheme.colors.textPrimary2.asBrush()
                    TypeStatusDoc.CONFIRMED -> IpbTheme.colors.onBackground.asBrush()
                }
            )
            if (state.status == TypeStatusDoc.CONFIRMED) {
            }
        }
    }
}
