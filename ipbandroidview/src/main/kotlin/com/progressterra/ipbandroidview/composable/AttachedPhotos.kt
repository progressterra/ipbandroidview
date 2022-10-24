package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.Picture
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AttachedPhoto(
    modifier: Modifier = Modifier,
    pictures: List<Picture>,
    onPhotoSelect: (picture: Picture) -> Unit,
    onCamera: () -> Unit,
    enabled: Boolean
) {

    @Composable
    fun Item(picture: Picture) {
        AsyncImage(modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(AppTheme.dimensions.tinyRounding))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = { onPhotoSelect(picture) }

            ), model = picture.thumbnail, contentDescription = stringResource(
            id = R.string.thumbnail
        ), contentScale = ContentScale.FillBounds)
    }

    if (pictures.isEmpty()) {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(AppTheme.dimensions.tinyRounding))
                .background(AppTheme.colors.background)
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
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
            Icon(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = stringResource(
                    id = R.string.camera
                ),
                tint = if (enabled) AppTheme.colors.primary else AppTheme.colors.gray2
            )
        }
    } else {
        LazyRow(modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            if (enabled) {
                item {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(AppTheme.dimensions.tinyRounding))
                            .background(AppTheme.colors.background)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(),
                                onClick = onCamera
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = stringResource(
                                id = R.string.camera
                            ),
                            tint = AppTheme.colors.primary
                        )
                    }
                }
            }
            items(pictures) { item ->
                Item(picture = item)
            }
        }
    }
}