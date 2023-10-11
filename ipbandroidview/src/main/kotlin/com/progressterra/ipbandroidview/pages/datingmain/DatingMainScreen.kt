package com.progressterra.ipbandroidview.pages.datingmain

import DatingMainScreenEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitch
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun DatingMainScreen(
    modifier: Modifier = Modifier,
    state: DatingMainScreenState,
    useComponent: UseDatingMainScreen
) {

    @Composable
    fun Tabs(
        selected: Int,
        onSelect: (Int) -> Unit
    ) {
        val tabList = listOf(
            stringResource(id = R.string.remoteness),
            stringResource(id = R.string.map)
        )
        TabRow(modifier = Modifier
            .padding(top = 28.dp)
            .width(240.dp)
            .height(40.dp)
            .clip(CircleShape), selectedTabIndex = selected,
            backgroundColor = IpbTheme.colors.surface.asColor(),
            divider = { },
            indicator = { }
        ) {
            tabList.forEachIndexed { index, text ->
                Tab(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(if (selected == index) IpbTheme.colors.secondary.asBrush() else IpbTheme.colors.surface.asBrush()),
                    selected = selected == index,
                    onClick = { onSelect(index) },
                    text = {
                        BrushedText(
                            text = text,
                            tint = IpbTheme.colors.textPrimary.asBrush(),
                            style = IpbTheme.typography.footnoteRegular
                        )
                    }
                )
            }
        }
    }

    ThemedLayout(
        modifier = modifier,
        topBar = {
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
                    onClick = { useComponent.handle(DatingMainScreenEvent.OnBack) }) {
                    BrushedIcon(
                        modifier = Modifier.size(30.dp),
                        resId = R.drawable.ic_back, tint = IpbTheme.colors.iconPrimary.asBrush()
                    )
                }
                BrushedText(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 40.dp),
                    text = stringResource(id = R.string.ready_to_meet),
                    maxLines = 1,
                    style = IpbTheme.typography.title,
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                )
                BrushedSwitch(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    state = state.readyToMeet,
                    useComponent = useComponent
                )
            }
        }
    ) { _, _ ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var selectedIndex by remember { mutableIntStateOf(0) }
            Tabs(selected = selectedIndex, onSelect = { selectedIndex = it })
            ConstraintLayout(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (selectedIndex == 0) {
                    val (call, picker, exposedPicker, filter, circle) = createRefs()
                    BrushedText(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .constrainAs(call) {
                                start.linkTo(parent.start, 16.dp)
                                end.linkTo(parent.end, 16.dp)
                                top.linkTo(parent.top)
                            },
                        text = stringResource(id = R.string.find_mate),
                        tint = IpbTheme.colors.textPrimary.asBrush(),
                        style = IpbTheme.typography.title2,
                        textAlign = TextAlign.Center
                    )
                    var exposed by remember { mutableStateOf(false) }
                    var currentRotation by remember { mutableFloatStateOf(180f) }
                    val rotation = remember { Animatable(currentRotation) }
                    LaunchedEffect(exposed) {
                        rotation.animateTo(
                            targetValue = if (exposed) {
                                currentRotation - 180f
                            } else {
                                currentRotation + 180f
                            }
                        ) {
                            currentRotation = value
                        }
                    }
                    Row(
                        modifier = Modifier
                            .constrainAs(picker) {
                                width = Dimension.value(240.dp)
                                top.linkTo(call.bottom, 28.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .clip(CircleShape)
                            .background(IpbTheme.colors.primary.asBrush())
                            .niceClickable { exposed = !exposed }
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BrushedText(
                            text = stringResource(id = R.string.choose_dating_interest),
                            tint = IpbTheme.colors.textButton.asBrush(),
                            style = IpbTheme.typography.subHeadlineRegular
                        )
                        Icon(
                            modifier = Modifier.rotate(rotation.value),
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_imh_exposable),
                            contentDescription = null
                        )
                    }
                    Column(modifier = Modifier.constrainAs(exposedPicker) {
                        width = Dimension.value(240.dp)
                        top.linkTo(picker.bottom, 8.dp)
                        start.linkTo(picker.start)
                        end.linkTo(picker.end)
                    }) {
                        AnimatedVisibility(visible = exposed) {
                            Column(
                            ) {
                                BrushedText(
                                    text = stringResource(id = R.string.choose_dating_interest),
                                    tint = IpbTheme.colors.textButton.asBrush(),
                                    style = IpbTheme.typography.subHeadlineRegular
                                )
                                BrushedText(
                                    text = stringResource(id = R.string.choose_dating_interest),
                                    tint = IpbTheme.colors.textButton.asBrush(),
                                    style = IpbTheme.typography.subHeadlineRegular
                                )
                                BrushedText(
                                    text = stringResource(id = R.string.choose_dating_interest),
                                    tint = IpbTheme.colors.textButton.asBrush(),
                                    style = IpbTheme.typography.subHeadlineRegular
                                )
                            }
                        }
                    }
                    IconButton(modifier = Modifier.constrainAs(filter) {
                        width = Dimension.value(24.dp)
                        height = Dimension.value(24.dp)
                        end.linkTo(parent.end, 16.dp)
                        top.linkTo(picker.top)
                        bottom.linkTo(picker.bottom)
                    }, onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_filter_empty),
                            contentDescription = null
                        )
                    }
                } else if (selectedIndex == 1) {

                }
            }
        }
    }
}

@Preview
@Composable
private fun DatingMainScreenPreview() {
    DatingMainScreen(state = DatingMainScreenState(), useComponent = UseDatingMainScreen.Empty())
}