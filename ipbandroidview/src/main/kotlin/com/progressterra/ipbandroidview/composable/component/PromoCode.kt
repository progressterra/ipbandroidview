package com.progressterra.ipbandroidview.composable.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.element.ThemedTextField
import com.progressterra.ipbandroidview.model.SimplePrice
import com.progressterra.ipbandroidview.theme.AppTheme

@Immutable
interface PromoCodeState {

    val promoCode: SimplePrice

    val promoCodeName: String
}

@Composable
fun PromoCode(
    modifier: Modifier = Modifier,
    state: () -> PromoCodeState,
    editPromoCode: (String) -> Unit,
    applyPromoCode: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .animateContentSize()
            .padding(AppTheme.dimensions.medium),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
    ) {
        ThemedTextField(
            text = state()::promoCodeName,
            hint = stringResource(R.string.email),
            onChange = editPromoCode,
            action = applyPromoCode
        )
        if (state().promoCode.price > 0)
            Text(
                text = "Скидка ${state().promoCode.formattedPrice}",
                color = AppTheme.colors.primary,
                style = AppTheme.typography.tertiaryText
            )
    }
}