package com.progressterra.ipbandroidview.pages.debug



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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import com.skydoves.landscapist.ImageOptions


import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.onSizeChanged

import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.Constraints


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


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
fun ImageWidth(image: String) {



        Column(
            modifier =
            Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                image = image,
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





@Preview
@Composable
private fun StoreCardPreview() {
    Preview {
        ImageWidth(
            image = "https://ipb.website.yandexcloud.net/mediadata/08dc90ff-4ff9-4a44-8f91-4ffbf77bb8da_20240625220907524",
        )
    }
}
