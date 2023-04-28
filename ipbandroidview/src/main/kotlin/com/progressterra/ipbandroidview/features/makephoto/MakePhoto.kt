package com.progressterra.ipbandroidview.features.makephoto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.button.PseudoButton
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun MakePhoto(
    modifier: Modifier = Modifier,
    title: String,
    state: MakePhotoState,
    useComponent: UseMakePhoto
) {

    @Composable
    fun Item(picture: MultisizedImage) {
        Box {
            SimpleImage(
                modifier = Modifier
                    .size(63.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .niceClickable { useComponent.handle(MakePhotoEvent.Select(picture.id)) },
                url = picture.thumbnail,
                backgroundColor = IpbTheme.colors.surface.asColor()
            )
            IconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(18.dp)
                    .padding(4.dp),
                onClick = { useComponent.handle(MakePhotoEvent.Remove(picture.id)) }
            ) {
                BrushedIcon(
                    resId = R.drawable.ic_cancel_small,
                    tint = IpbTheme.colors.iconTertiary.asBrush()
                )
            }
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        PseudoButton(
            modifier = Modifier.fillMaxWidth(),
            state = state.makePhoto,
            useComponent = useComponent,
            title = title,
            icId = R.drawable.ic_camera
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.items) { item ->
                Item(picture = item)
            }
        }
    }
}

@Preview
@Composable
private fun AttachedPhotosPreviewEnabled() {
    IpbTheme {
        MakePhoto(
            state = MakePhotoState(
                items = listOf(
                    MultisizedImage(
                        id = "", local = false, toRemove = false, thumbnail = "", fullSize = ""
                    ), MultisizedImage(
                        id = "", local = false, toRemove = false, thumbnail = "", fullSize = ""
                    ), MultisizedImage(
                        id = "", local = false, toRemove = false, thumbnail = "", fullSize = ""
                    )
                )
            ), useComponent = UseMakePhoto.Empty(),
            title = stringResource(R.string.passport_photo)
        )
    }
}
