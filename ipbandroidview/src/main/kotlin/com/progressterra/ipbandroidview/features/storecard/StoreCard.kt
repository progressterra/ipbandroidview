package com.progressterra.ipbandroidview.features.storecard

import androidx.compose.foundation.background
import androidx.compose.foundation.border

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale

import com.skydoves.landscapist.ImageOptions


import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.runtime.*

import androidx.compose.ui.platform.LocalConfiguration

import androidx.compose.ui.res.painterResource


import androidx.compose.foundation.layout.padding
import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings


//@Composable
//fun GradientText(
//    text: String,
//    modifier: Modifier = Modifier
//) {
//    Box(
//        modifier = modifier
//            .background(
//                brush = Brush.horizontalGradient(
//                    colors = listOf(colorLeft, colorRight)
//                ),
//                shape = RoundedCornerShape(4.dp)
//            )
//            .padding(horizontal = 16.dp, vertical = 8.dp)
//    ) {
//        Text(
//            text = text,
//            style = textStyle,
//            color = textColor
//        )
//    }
//}

//@Composable
//fun FullWidthImage(image: String) {
//    BoxWithConstraints(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        val screenWidth = maxWidth
//
//        Image(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(screenWidth / 2) // Adjust this ratio based on your image aspect ratio
//                .aspectRatio(2f), // Adjust aspect ratio according to your need
//            image = image,
//            options = ImageOptions(contentScale = ContentScale.Crop)
//        )
//    }
//}


@Composable
fun FullWidthImage(image: String) {
    var aspectRatio by remember { mutableStateOf(1f) }
    var imageHeight by remember { mutableStateOf(0.dp) }

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val screenWidth = maxWidth

        Image(
            modifier = Modifier
                .fillMaxWidth(),
//                .height(imageHeight)
//                .onGloballyPositioned { layoutCoordinates ->
//                    val widthPx = layoutCoordinates.size.width.toFloat()
//                    val heightPx = layoutCoordinates.size.height.toFloat()
//                    aspectRatio = heightPx / widthPx
//                    imageHeight = screenWidth * aspectRatio
//                },
            image = image,
            options = ImageOptions(contentScale = ContentScale.FillWidth)
        )
    }
}

@Composable
fun getScreenWidth(): Int {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp
}

@Composable
fun StoreCard(modifier: Modifier = Modifier, state: StoreCardState, useComponent: UseStoreCard, isBanner: Boolean = false) {
//    val colorLeft = Color(0xFF53B8EB)
//    val colorRight = Color(0xFF27D1AE)

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp * 0.9f



    if (isBanner && state.imageBanner != "")
    {
        Column(
            modifier =
            Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)).niceClickable {
                useComponent.handle(StoreCardEvent.Open(state.id))},

            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                modifier = Modifier
                    .width(screenWidthDp)
                    .align(Alignment.CenterHorizontally),
                image = state.imageBanner,
                options = ImageOptions(contentScale = ContentScale.Fit),
                previewPlaceholder = painterResource(id = R.drawable.big_banner)
            )

        }
//        Image(
//            modifier =
//            Modifier
//                .fillMaxWidth(),
//            image = state.imageBanner,
//            options = ImageOptions(contentScale = ContentScale.Fit)
//        )

        //FullWidthImage(state.imageBanner)
    }

    else{

        Column(
            modifier =
            modifier.width(207.dp).clip(RoundedCornerShape(8.dp))
                .border(
                    width = 1.dp, // задайте необходимую толщину
                    color = IpbTheme.colors.tertiary.asColor(), // задайте необходимый цвет
                    shape = RoundedCornerShape(8.dp) // форма границы совпадает с формой клипа
                )
                .niceClickable {
                useComponent.handle(StoreCardEvent.Open(state.id))
            },
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Image(
                modifier =
                Modifier
                    .size(width = 157.dp, height = 157.dp)
                    .align(Alignment.CenterHorizontally),//.clip(RoundedCornerShape(8.dp)),
                image = state.image
            )
            Text(
                modifier = Modifier
                    .background(Brush.horizontalGradient(listOf(IpbAndroidViewSettings.GRADIENT_COLOR_LEFT, IpbAndroidViewSettings.GRADIENT_COLOR_RIGHT)))
                    .fillMaxWidth()
                    .padding(7.dp),
                text = "Кэшбэк " + state.price.toString() + " баллов",
                style = IpbTheme.typography.subHeadlineRegular,
                tint = IpbTheme.colors.textPrimary2.asBrush(),
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp),

                text = state.name,
                style = IpbTheme.typography.caption,
                tint = IpbTheme.colors.textPrimary.asBrush(),
                maxLines = 1
            )



            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 7.dp),
                text = state.properties.firstOrNull { it.first ==  IpbAndroidViewSettings.NAME_TYPECHARECTERISTIC_MANUFACTURE}?.second ?: "",
                style = IpbTheme.typography.caption3,
                tint = IpbTheme.colors.textPrimary.asBrush(),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(7.dp))

        }
    }


}

@Preview
@Composable
private fun StoreCardPreview() {
    Preview {
        StoreCard(
            state = StoreCardState(image = "https://ipb.website.yandexcloud.net/mediadata/08dc90ff-4ff9-4a44-8f91-4ffbf77bb8da_20240625220907524", name = "Ноутбук Lenovo IdeaPad 3 15ADA05", price = Price(10)),
            useComponent = UseStoreCard.Empty()
        )
        StoreCard(
            state =
                StoreCardState(
                    image = "https://ipb.website.yandexcloud.net/mediadata/08dc90ff-4ff9-4a44-8f91-4ffbf77bb8da_20240625220907524",
                    name = "Ноутбук Lenovo IdeaPad 3 15ADA05",
                    price = Price(100),
                    counter = CounterState("1", 5),
                    installment = Installment(months = 4, perMonth = Price(500))
                ),
            useComponent = UseStoreCard.Empty()
        )
    }
}
