package com.progressterra.ipbandroidview.features.catalogcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.Preview
import com.progressterra.ipbandroidview.shared.ui.Image
import com.progressterra.ipbandroidview.shared.ui.Text
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable
import com.skydoves.landscapist.ImageOptions

@Composable
fun CatalogCard(
    modifier: Modifier = Modifier,
    state: CatalogCardState,
    useComponent: UseCatalogCard
) {
    Row(
        modifier =
        modifier
            .fillMaxWidth() // Растягиваем компонент на всю ширину
            .clip(RoundedCornerShape(8.dp))
            .niceClickable {
                useComponent.handle(CatalogCardEvent(state))
            }
            .padding(8.dp), // Добавляем отступы
        horizontalArrangement = Arrangement.spacedBy(8.dp), // Пробел между изображением и текстом
        verticalAlignment = Alignment.CenterVertically // Центровка по вертикали
    ) {
        Image(
            modifier = Modifier
                .size(108.dp) // Размер изображения
                .clip(RoundedCornerShape(8.dp)),
            image = state.image,
            options = ImageOptions(contentScale = ContentScale.FillBounds)
        )
        Box(
            modifier = Modifier
                .weight(1f) // Текст занимает оставшееся пространство
                .clip(RoundedCornerShape(8.dp)) // Закругленные края

        ) {
            Text(
                text = state.name,
                style = IpbTheme.typography.title,
                tint = IpbTheme.colors.surface2.asBrush(),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(8.dp) // Внутренние отступы текста
                    .align(Alignment.CenterStart) // Выравнивание текста внутри Box
            )
        }
    }
}

@Preview
@Composable
private fun CatalogCardPreview() {
    Preview {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            CatalogCard(
                state = CatalogCardState(name = "Ноутбук Lenovo IdeaPad 3 15ADA05"),
                useComponent = UseCatalogCard.Empty()
            )
            CatalogCard(
                state = CatalogCardState(name = "Лэп"),
                useComponent = UseCatalogCard.Empty()
            )
        }
    }
}
