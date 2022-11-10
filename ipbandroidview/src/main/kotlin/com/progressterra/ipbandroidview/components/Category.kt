package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.components.utils.niceClickable
import com.progressterra.ipbandroidview.model.component.Id
import com.progressterra.ipbandroidview.model.component.Image
import com.progressterra.ipbandroidview.model.component.Name
import com.progressterra.ipbandroidview.theme.AppTheme
import com.skydoves.landscapist.ImageOptions

interface CategoryState : Id, Image, Name

@Composable
fun Category(modifier: Modifier = Modifier, state: CategoryState, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .niceClickable(onClick),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        SimpleImage(
            modifier = Modifier
                .clip(AppTheme.shapes.small)
                .fillMaxWidth()
                .height(104.dp),
            url = state.image,
            options = ImageOptions(),
            backgroundColor = AppTheme.colors.surfaces
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = state.name,
                color = AppTheme.colors.black,
                style = AppTheme.typography.title
            )
        }
    }
}

private class CategoryStatePreview(
    override val id: String,
    override val image: String,
    override val name: String
) : CategoryState

@Preview
@Composable
private fun CategoryPreview() {
    AppTheme {
        Category(
            modifier = Modifier.width(300.dp),
            state = CategoryStatePreview(
                id = "", image = "", name = "Some category"
            ),
            onClick = {}
        )
    }
}