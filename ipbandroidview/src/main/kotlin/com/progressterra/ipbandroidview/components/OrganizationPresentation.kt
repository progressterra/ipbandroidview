package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme
import com.skydoves.landscapist.ImageOptions

@Composable
fun OrganizationPresentation(
    modifier: Modifier = Modifier,
    name: String,
    address: String,
    imageUrl: String,
    onMapClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.roundings.mediumRounding))
            .background(AppTheme.colors.surfaces)
            .padding(12.dp)
    ) {
        SimpleImage(
            modifier = Modifier
                .height(188.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(AppTheme.roundings.smallRounding)),
            url = imageUrl,
            options = ImageOptions(contentScale = ContentScale.FillBounds),
            backgroundColor = AppTheme.colors.surfaces
        )
        Spacer(modifier = Modifier.size(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = name, color = AppTheme.colors.black, style = AppTheme.typography.title)
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = address,
                    color = AppTheme.colors.gray2,
                    style = AppTheme.typography.tertiaryText
                )
            }
            Column(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(),
                        onClick = onMapClick
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_map),
                    contentDescription = stringResource(
                        id = R.string.map
                    ),
                    tint = AppTheme.colors.primary
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = stringResource(id = R.string.map),
                    color = AppTheme.colors.primary,
                    style = AppTheme.typography.tertiaryText
                )
            }
        }
    }
}

@Preview
@Composable
private fun OrganizationPresentationPreview() {
    AppTheme {
        OrganizationPresentation(name = "Кофемания",
            address = "Ленина 13 б",
            imageUrl = "",
            onMapClick = {})
    }
}