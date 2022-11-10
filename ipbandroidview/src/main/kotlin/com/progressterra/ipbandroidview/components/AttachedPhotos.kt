package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.utils.niceClickable
import com.progressterra.ipbandroidview.model.CheckPicture
import com.progressterra.ipbandroidview.theme.AppTheme
import com.skydoves.landscapist.ImageOptions

@Composable
fun AttachedPhoto(
    modifier: Modifier = Modifier,
    pictures: List<CheckPicture>,
    onPhotoSelect: (picture: CheckPicture) -> Unit,
    onCamera: () -> Unit,
    enabled: Boolean
) {

    @Composable
    fun Item(picture: CheckPicture) {
        SimpleImage(
            modifier = Modifier
                .size(48.dp)
                .clip(AppTheme.shapes.small)
                .niceClickable(onClick = { onPhotoSelect(picture) }),
            url = picture.thumbnail,
            options = ImageOptions(contentScale = ContentScale.FillBounds),
            backgroundColor = AppTheme.colors.surfaces
        )
    }

    if (pictures.isEmpty()) {
        Row(
            modifier = modifier
                .clip(AppTheme.shapes.small)
                .fillMaxWidth()
                .height(TextFieldDefaults.MinHeight)
                .background(AppTheme.colors.background)
                .niceClickable(
                    onClick = onCamera,
                    enabled = enabled
                )
                .padding(horizontal = 12.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.add_photo),
                style = AppTheme.typography.text,
                color = if (enabled) AppTheme.colors.gray1 else AppTheme.colors.gray2
            )
            CameraIcon(enabled = enabled)
        }
    } else {
        LazyRow(modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            if (enabled) {
                item {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(AppTheme.shapes.small)
                            .background(AppTheme.colors.background)
                            .niceClickable(onClick = onCamera),
                        contentAlignment = Alignment.Center
                    ) {
                        CameraIcon(enabled = true)
                    }
                }
            }
            items(pictures) { item ->
                Item(picture = item)
            }
        }
    }
}