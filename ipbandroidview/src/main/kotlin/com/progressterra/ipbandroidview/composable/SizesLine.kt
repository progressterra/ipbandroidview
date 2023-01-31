package com.progressterra.ipbandroidview.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.model.store.GoodsDetails
import com.progressterra.ipbandroidview.model.store.GoodsSize
import com.progressterra.ipbandroidview.model.store.SimplePrice
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun SizesLine(
    modifier: Modifier = Modifier,
    state: GoodsDetails,
    onSize: (GoodsSize) -> Unit,
    onTable: () -> Unit
) {
//
//    @Composable
//    fun Item(size: GoodsSize) {
//        Column(
//            modifier = Modifier
//                .clip(AppTheme.shapes.small)
//                .border(
//                    width = 1.dp,
//                    color = if (size == state.size) AppTheme.colors.primary else Color.Transparent,
//                    AppTheme.shapes.small
//                )
//                .niceClickable { onSize(size) }
//                .padding(
//                    vertical = AppTheme.dimensions.tiny, horizontal = AppTheme.dimensions.smany
//                ),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(2.dp)
//        ) {
//            Text(
//                text = size.primary,
//                color = if (size.available) AppTheme.colors.black else AppTheme.colors.gray3,
//                style = AppTheme.typography.title
//            )
//            size.secondary?.let {
//                Text(
//                    text = size.secondary,
//                    color = if (size.available) AppTheme.colors.gray2 else AppTheme.colors.gray3,
//                    style = AppTheme.typography.tertiaryText
//                )
//            }
//        }
//    }
//    Column(
//        modifier = modifier
//            .clip(AppTheme.shapes.medium)
//            .background(AppTheme.colors.surfaces)
//            .padding(AppTheme.dimensions.medium),
//        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            LazyRow(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
//                items(state.sizes) {
//                    Item(it)
//                }
//            }
//            IconButton(onClick = onTable) {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.spacedBy(2.dp)
//                ) {
//                    RulerIcon()
//                    Text(
//                        text = stringResource(id = R.string.table),
//                        color = AppTheme.colors.primary,
//                        style = AppTheme.typography.tertiaryText
//                    )
//                }
//            }
//        }
//        Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)) {
//            Text(
//                text = stringResource(id = R.string.size),
//                color = AppTheme.colors.gray2,
//                style = AppTheme.typography.tertiaryText
//            )
//            Text(
//                text = state.size.primary,
//                color = AppTheme.colors.gray1,
//                style = AppTheme.typography.tertiaryText
//            )
//        }
//    }
}

@Preview
@Composable
private fun SizesLinePreview() {
    AppTheme {
        val current = GoodsSize(
            primary = "M", secondary = "36", available = true
        )
        SizesLine(state = GoodsDetails(
            description = "",
            favorite = false,
            images = listOf(),
            inCartCounter = 0,
            name = "",
            parameters = listOf(),
            price = SimplePrice(0)
        ), onSize = {}, onTable = {})
    }
}