package com.progressterra.ipbandroidview.features.storecardwide

import androidx.compose.foundation.background

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Installment
import com.progressterra.ipbandroidview.entities.Price
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.Preview
import com.progressterra.ipbandroidview.shared.ui.Icon
import com.progressterra.ipbandroidview.shared.ui.Image
import com.progressterra.ipbandroidview.shared.ui.Text
import com.progressterra.ipbandroidview.shared.ui.counter.Counter
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


import androidx.compose.foundation.layout.*



@Composable
fun StoreCardWide(modifier: Modifier = Modifier, state: StoreCardWideState, useComponent: UseStoreCardWide) {
    val colorLeft = Color(0xFF53B8EB)
    val colorRight = Color(0xFF27D1AE)
    Column(
        modifier =
            modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)).niceClickable {
                useComponent.handle(StoreCardWideEvent.Open(state.id))
            },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            modifier =
                Modifier.fillMaxWidth(),//.clip(RoundedCornerShape(8.dp)),
            image = state.image
        )
    }
}

@Preview
@Composable
private fun StoreCardPreview() {
    Preview {
        StoreCardWide(
            state = StoreCardWideState(name = "Ноутбук Lenovo IdeaPad 3 15ADA05", price = Price(10)),
            useComponent = UseStoreCardWide.Empty()
        )
        StoreCardWide(
            state =
                StoreCardWideState(
                    name = "Ноутбук Lenovo IdeaPad 3 15ADA05",
                    price = Price(100),
                    counter = CounterState("1", 5),
                    installment = Installment(months = 4, perMonth = Price(500))
                ),
            useComponent = UseStoreCardWide.Empty()
        )
    }
}
