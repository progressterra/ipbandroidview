package com.progressterra.ipbandroidview.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AttachedPhoto(
    modifier: Modifier = Modifier,
    readOnly: Boolean,
    photosIds: List<String>,
    onPhotoSelect: (id: String) -> Unit,
    onCamera: () -> Unit
) {

    @Composable
    fun Item(id: String, ordinal: String) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(AppTheme.dimensions.tinyRounding))
                .background(AppTheme.colors.background)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    onClick = {
                        Log.d("PHOTO", "photos $photosIds")
                        Log.d("PHOTO", "selected $id")
                        onPhotoSelect(id)
                    }
                ), contentAlignment = Alignment.Center) {
            Text(
                text = ordinal,
                color = AppTheme.colors.primary,
                style = AppTheme.typography.title
            )
        }

    }
    if (photosIds.isEmpty()) {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(AppTheme.dimensions.tinyRounding))
                .background(AppTheme.colors.background)
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    onClick = onCamera,
                    enabled = !readOnly
                )
                .padding(horizontal = 12.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.add_photo),
                style = AppTheme.typography.text,
                color = AppTheme.colors.gray1
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = stringResource(
                    id = R.string.camera
                ),
                tint = AppTheme.colors.primary
            )
        }
    } else {
        LazyRow(modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            if (!readOnly) {
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
            itemsIndexed(photosIds) { index, item ->
                Item(ordinal = index.toString(), id = item)
            }
        }
    }
}