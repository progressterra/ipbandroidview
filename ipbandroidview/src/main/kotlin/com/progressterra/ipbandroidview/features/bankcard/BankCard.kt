package com.progressterra.ipbandroidview.features.bankcard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.toColor
import com.progressterra.ipbandroidview.entities.toString
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.toBrush
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun BankCard(
    modifier: Modifier = Modifier,
    state: BankCardState,
    useComponent: UseBankCard,
    canBeRemoved: Boolean = false,
    canBePicked: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .border(
                width = 1.dp,
                brush = if (state.isSelected && canBePicked) {
                    IpbTheme.colors.primary.asBrush()
                } else {
                    Color.Transparent.toBrush()
                },
                shape = RoundedCornerShape(8.dp)
            )
            .niceClickable(enabled = canBePicked || state.document.status != TypeStatusDoc.CONFIRMED) {
                useComponent.handleEvent(BankCardEvent.Click(state))
            }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            BrushedText(
                text = state.document.name,
                style = IpbTheme.typography.subHeadlineRegular,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
            if (state.isMainCard) {
                BrushedText(
                    text = stringResource(id = R.string.main_card),
                    style = IpbTheme.typography.footnoteRegular,
                    tint = IpbTheme.colors.textTertiary.asBrush()
                )
            }
            if (state.document.status != TypeStatusDoc.CONFIRMED) {
                BrushedText(
                    text = state.document.status.toString { stringResource(id = it) },
                    style = IpbTheme.typography.footnoteRegular,
                    tint = state.document.status.toColor()
                )
            }
        }
        if (canBeRemoved) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = { useComponent.handleEvent(BankCardEvent.Delete(state)) }) {
                BrushedIcon(
                    resId = R.drawable.ic_trash, tint = IpbTheme.colors.iconTertiary.asBrush()
                )
            }
        }
    }
}

@Preview
@Composable
private fun BankCardPreview0() {
    BankCard(
        state = BankCardState(
            isMainCard = true,
            document = Document(
                name = "VISA **** **** **** 1234",
                status = TypeStatusDoc.CONFIRMED
            ),
            id = "",
            isSelected = false
        ),
        useComponent = UseBankCard.Empty(),
        canBeRemoved = true
    )
}

@Preview
@Composable
private fun BankCardPreview1() {
    BankCard(
        state = BankCardState(
            isMainCard = true,
            document = Document(
                name = "VISA **** **** **** 1234",
                status = TypeStatusDoc.WAIT_IMAGE
            ),
            id = "",
            isSelected = false
        ),
        useComponent = UseBankCard.Empty()
    )
}