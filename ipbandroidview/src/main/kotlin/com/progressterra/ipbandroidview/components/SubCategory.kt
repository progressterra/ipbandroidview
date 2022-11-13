package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.progressterra.ipbandroidview.model.component.Name
import com.progressterra.ipbandroidview.theme.AppTheme

interface SubCategoryState : Id, Name

@Composable
fun SubCategory(modifier: Modifier = Modifier, state: () -> SubCategoryState, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .niceClickable(onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state().name,
            color = AppTheme.colors.black,
            style = AppTheme.typography.text
        )
        ForwardIcon()
    }
}

private class SubCategoryStatePreview(
    override val id: String,
    override val name: String
) : SubCategoryState

@Preview
@Composable
private fun SubCategoryPreview() {
    AppTheme {
        SubCategory(
            modifier = Modifier.width(300.dp),
            state = {
                SubCategoryStatePreview(
                    id = "", name = "Some category"
                )
            }, onClick = {}
        )
    }
}