package com.progressterra.ipbandroidview.features.catalogcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.Preview
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage

@Composable
fun CatalogCard(
    modifier: Modifier = Modifier,
    state: CatalogCardState,
    useComponent: UseCatalogCard
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .niceClickable {
                useComponent.handle(
                    CatalogCardEvent.Open(state.id)
                )
            }, verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        SimpleImage(
            modifier = Modifier
                .size(98.dp)
                .clip(RoundedCornerShape(8.dp)),
            url = state.imageUrl,
            backgroundColor = IpbTheme.colors.background.asColor()
        )
        BrushedText(
            text = state.name,
            style = IpbTheme.typography.footnoteRegular,
            tint = IpbTheme.colors.surface2.asBrush(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun CatalogCardPreview() {
    Preview {
        CatalogCard(
            state = CatalogCardState(
                name = "Ноутбук Lenovo IdeaPad 3 15ADA05"
            ), useComponent = UseCatalogCard.Empty()
        )
    }
}