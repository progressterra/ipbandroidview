package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.utils.niceClickable
import com.progressterra.ipbandroidview.model.DeliveryMethod
import com.progressterra.ipbandroidview.theme.AppTheme

interface DeliveryPickerState {

    val address: String

    val entryway: String

    val apartment: String

    val comment: String

    val selectedDeliveryMethod: DeliveryMethod?

    val deliveryMethods: List<DeliveryMethod>
}


@Composable
fun DeliveryPicker(
    modifier: Modifier = Modifier,
    state: () -> DeliveryPickerState,
    changeAddress: () -> Unit,
    selectPickUpPoint: () -> Unit,
    selectDeliveryMethod: (DeliveryMethod) -> Unit,
    editApartment: (String) -> Unit,
    editEntryway: (String) -> Unit,
    editComment: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.medium),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
    ) {
        Text(
            text = stringResource(R.string.delivery),
            color = AppTheme.colors.black,
            style = AppTheme.typography.title
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(AppTheme.shapes.medium)
                .background(AppTheme.colors.background)
                .niceClickable(changeAddress)
                .padding(AppTheme.dimensions.large),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = state().address,
                color = AppTheme.colors.black,
                style = AppTheme.typography.text
            )
            ForwardIcon()
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
            userScrollEnabled = false
        ) {
            items(state().deliveryMethods) {
                Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)) {
                    ThemedRadioButton(
                        checked = { it == state().selectedDeliveryMethod },
                        onClick = { selectDeliveryMethod(it) })
                    Column {
                        Text(
                            text = "${it.deliveryTime}, ${it.price.formattedPrice}",
                            color = AppTheme.colors.black,
                            style = AppTheme.typography.text
                        )
                        Text(
                            text = it.deliveryTypeText,
                            color = AppTheme.colors.black,
                            style = AppTheme.typography.text
                        )
                    }
                }
                if (it.addressNeeded && it == state().selectedDeliveryMethod)
                    Column(
                        modifier = modifier,
                        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.large)) {
                            ThemedTextField(
                                modifier = Modifier.weight(1f),
                                text = state()::entryway,
                                hint = stringResource(R.string.entryway),
                                onChange = editEntryway
                            )
                            ThemedTextField(
                                modifier = Modifier.weight(1f),
                                text = state()::apartment,
                                hint = stringResource(R.string.apartment),
                                onChange = editApartment
                            )
                        }
                        ThemedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            text = state()::comment,
                            hint = stringResource(R.string.apartment),
                            onChange = editComment
                        )
                    }
                if (!it.addressNeeded && it == state().selectedDeliveryMethod)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(AppTheme.shapes.medium)
                            .background(AppTheme.colors.background)
                            .niceClickable(selectPickUpPoint)
                            .padding(AppTheme.dimensions.large),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.choose_pick_up),
                            color = AppTheme.colors.black,
                            style = AppTheme.typography.text
                        )
                        ForwardIcon()
                    }
            }
        }
    }
}