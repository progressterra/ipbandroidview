package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ArrowRightIcon
import com.progressterra.ipbandroidview.composable.PlusPeopleIcon
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.theme.AppTheme

private val spacing1 = 22.dp

private val spacing2 = 19.dp

private val spacing3 = 3.dp

private val buttonBackground = Color(0xFFD9D9D9)

@Immutable
data class ExtendedBonusesState(
    val bonuses: String = "", val burningDate: String = "", val burningQuantity: String = ""
)

sealed class ExtendedBonusesEvent {

    object SpendBonuses : ExtendedBonusesEvent()

    object InviteFriends : ExtendedBonusesEvent()

    object OnClick : ExtendedBonusesEvent()
}

interface UseExtendedBonuses {

    fun handleEvent(id: String, event: ExtendedBonusesEvent)

    class Empty : UseExtendedBonuses {
        override fun handleEvent(id: String, event: ExtendedBonusesEvent) = Unit
    }
}

@Composable
private fun BonusesButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(
                AppTheme.shapes.medium
            )
            .background(buttonBackground)
            .niceClickable { onClick() }
            .padding(
                horizontal = AppTheme.dimensions.medium, vertical = AppTheme.dimensions.large
            ),
        verticalArrangement = Arrangement.spacedBy(spacing3),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        icon()
        Text(
            modifier = modifier,
            text = text,
            style = AppTheme.typography.tertiaryText,
            color = AppTheme.colors.black
        )
    }
}

@Composable
fun ExtendedBonuses(
    modifier: Modifier = Modifier,
    id: String = "default",
    state: ExtendedBonusesState,
    useComponent: UseExtendedBonuses
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.large)
    ) {
        Text(
            text = "${stringResource(R.string.you_have)} ${state.bonuses} ${stringResource(R.string.bonuses)}",
            style = AppTheme.typography.title,
            color = AppTheme.colors.black
        )
        Spacer(modifier = Modifier.height(spacing1))
        Text(
            text = "${state.burningDate} ${stringResource(R.string.will_burn)} ${state.burningQuantity} ${
                stringResource(R.string.bonuses)
            }", style = AppTheme.typography.secondaryText, color = AppTheme.colors.error
        )
        Spacer(modifier = Modifier.height(spacing2))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
            ) {
                BonusesButton(text = stringResource(R.string.spend),
                    icon = { PlusPeopleIcon() },
                    onClick = { useComponent.handleEvent(id, ExtendedBonusesEvent.SpendBonuses) })
                BonusesButton(text = stringResource(R.string.invite_friends),
                    icon = { PlusPeopleIcon() },
                    onClick = { useComponent.handleEvent(id, ExtendedBonusesEvent.InviteFriends) })
            }
            IconButton(onClick = { useComponent.handleEvent(id, ExtendedBonusesEvent.OnClick) }) {
                ArrowRightIcon()
            }
        }
    }
}

@Preview
@Composable
fun BonusesPreview() {
    ExtendedBonuses(
        state = ExtendedBonusesState(
            bonuses = "1000",
            burningDate = "01.01.2021",
            burningQuantity = "100"
        ),
        useComponent = UseExtendedBonuses.Empty()
    )
}