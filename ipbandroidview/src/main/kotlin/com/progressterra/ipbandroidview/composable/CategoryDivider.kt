package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun CategoryDivider(
    modifier: Modifier = Modifier, gap: Dp = 8.dp, minDivWidth: Dp = 32.dp, category: String = ""
) {
    ConstraintLayout(modifier = modifier) {
        val (text, line, spacer) = createRefs()
        Text(
            modifier = Modifier.constrainAs(text) {
                width = Dimension.preferredWrapContent
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            textAlign = TextAlign.Start,
            text = category,
            style = AppTheme.typography.actionBarLabels,
            color = AppTheme.colors.gray2
        )
        if (category.isNotBlank()) {
            Spacer(modifier = Modifier.size(gap).constrainAs(spacer) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            })
        }
        Box(
            modifier = Modifier
                .background(AppTheme.colors.gray2)
                .constrainAs(line) {
                    height = Dimension.value(1.dp)
                    width = Dimension.fillToConstraints.atLeast(minDivWidth)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )
        createHorizontalChain(text, spacer, line, chainStyle = ChainStyle.SpreadInside)

    }
}

@Preview
@Composable
private fun CategoryDivider() {
    AppTheme {
        Surface(color = AppTheme.colors.surfaces) {
            CategoryDivider(
                modifier = Modifier.fillMaxWidth(),
                category = "Some category"
            )
        }
    }
}

@Preview
@Composable
private fun CategoryDividerEmpty() {
    AppTheme {
        Surface(color = AppTheme.colors.surfaces) {
            CategoryDivider(
                category = ""
            )
        }
    }
}

@Preview
@Composable
private fun CategoryDividerLong() {
    AppTheme {
        Surface(color = AppTheme.colors.surfaces) {
            CategoryDivider(
                category = "Some very long text some very long text some very long text some very long text some very long text some very long text some very long text some very long text HAPPY END"
            )
        }
    }
}