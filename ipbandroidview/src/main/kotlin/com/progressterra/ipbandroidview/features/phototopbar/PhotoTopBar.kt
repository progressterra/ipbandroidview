package com.progressterra.ipbandroidview.features.phototopbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon

@Composable
fun PhotoTopBar(
    modifier: Modifier = Modifier,
    useComponent: UsePhotoTopBar
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(IpbTheme.colors.background.asBrush())
            .padding(horizontal = 16.dp)
    ) {
        IconButton(modifier = Modifier
            .size(30.dp)
            .align(Alignment.CenterStart),
            onClick = { useComponent.handle(PhotoTopBarEvent) }) {
            BrushedIcon(
                modifier = Modifier.size(30.dp),
                resId = R.drawable.ic_back, tint = IpbTheme.colors.iconPrimary.asBrush()
            )
        }
    }
}