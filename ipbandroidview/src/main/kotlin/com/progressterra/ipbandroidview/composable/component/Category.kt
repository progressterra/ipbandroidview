package com.progressterra.ipbandroidview.composable.component

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
import com.progressterra.ipbandroidview.composable.SimpleImage
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.CategoryWithSubcategories
import com.progressterra.ipbandroidview.model.MainCategory
import com.progressterra.ipbandroidview.theme.AppTheme

private val picHeight = 104.dp

@Composable
fun Category(
    modifier: Modifier = Modifier,
    state: MainCategory,
    openCategory: (CategoryWithSubcategories) -> Unit
) {
    Column(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .niceClickable { openCategory(state) },
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)
    ) {
        SimpleImage(
            modifier = Modifier
                .clip(AppTheme.shapes.small)
                .fillMaxWidth()
                .height(picHeight),
            url = state.image,
            backgroundColor = AppTheme.colors.surfaces
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = AppTheme.dimensions.small,
                    end = AppTheme.dimensions.small,
                    bottom = AppTheme.dimensions.small
                ),
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

@Preview
@Composable
private fun CategoryPreview() {
    AppTheme {
        Category(
            modifier = Modifier.width(300.dp),
            state = MainCategory(
                id = "", name = "", image = "", subCategories = listOf(), hasNext = false
            ),
            openCategory = {}
        )
    }
}