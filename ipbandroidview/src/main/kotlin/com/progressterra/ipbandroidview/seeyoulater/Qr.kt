//package com.progressterra.ipbandroidview.composable
//
//import android.graphics.Bitmap
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.unit.dp
//import com.progressterra.ipbandroidview.shared.theme.IpbTheme
//
//private val height = 130.dp
//
//private val qrSize = 114.dp
//
//@Composable
//fun Qr(
//    modifier: Modifier = Modifier, qr: Bitmap
//) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .height(height)
//            .clip(IpbTheme.shapes.medium)
//            .background(IpbTheme.colors.surfaces)
//            .padding(8.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        Image(
//            modifier = Modifier.size(qrSize),
//            bitmap = qr.asImageBitmap(),
//            contentDescription = null,
//            contentScale = ContentScale.FillBounds
//        )
//    }
//}