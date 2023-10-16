package com.progressterra.ipbandroidview.pages.datingmain

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.AnotherUser
import com.progressterra.ipbandroidview.entities.DatingTarget
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitch
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchState
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.yandex.mapkit.mapview.MapView
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun DatingMainScreen(
    modifier: Modifier = Modifier, state: DatingMainScreenState, useComponent: UseDatingMainScreen
) {

    @Composable
    fun User(
        user: AnotherUser
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.drawable.avatar_background),
                tint = IpbTheme.colors.primary.asColor(),
                contentDescription = null
            )
            SimpleImage(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                image = user.image,
                backgroundColor = IpbTheme.colors.background.asColor()
            )
        }
    }

    @Composable
    fun Orbits(
        modifier: Modifier
    ) {
        val rotationTime = 1000 * 30
        val tiers = rememberSaveable(state.users, state.chosenTier) {
            List(3) { if (it == state.chosenTier) state.users.size else Random.nextInt(1..5) }
        }
        val animationValues = rememberSaveable(tiers) {
            List(3) { tier ->
                val step = 360f / tiers[tier]
                List(tiers[tier]) {
                    Animatable(it * step)
                }
            }
        }
        LaunchedEffect(animationValues) {
            for (i in animationValues.indices) {
                if (state.chosenTier == i || state.chosenTier == null) {
                    val step = 360f / animationValues[i].size
                    animationValues[i].forEachIndexed { index, anim ->
                        launch {
                            anim.animateTo(
                                targetValue = index * step + 360f,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(
                                        durationMillis = rotationTime,
                                        easing = LinearEasing
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }
        BoxWithConstraints(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(1f), contentAlignment = Alignment.Center
        ) {
            val density = LocalDensity.current
            val baseR =
                constraints.maxWidth / 2f - with(density) { if (state.chosenTier == null) 9.dp.toPx() else 25.dp.toPx() }
            val diff = with(density) { baseR.toDp() } - 39.dp
            for (i in animationValues.indices) {
                val r = with(density) { ((diff * (3 - i) / 3) + 39.dp).toPx() }
                for (j in animationValues[i].indices) {
                    val x =
                        (r + cos(Math.toRadians(animationValues[i][j].value.toDouble())) * r).toFloat()
                    val y =
                        (r + sin(Math.toRadians(animationValues[i][j].value.toDouble())) * r).toFloat()
                    val intOffset = IntOffset(
                        x.roundToInt(), y.roundToInt()
                    )
                    val correctionOffset =
                        IntOffset((baseR - r).roundToInt(), (baseR - r).roundToInt())
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .offset { intOffset + correctionOffset }
                        .zIndex(1f)) {
                        if (state.chosenTier == i) {
                            User(user = state.users[j])
                        } else if (state.chosenTier == null) {
                                BrushedIcon(
                                    resId = when (i) {
                                        0 -> R.drawable.ic_tier_1
                                        1 -> R.drawable.ic_tier_2
                                        else -> R.drawable.ic_tier_3
                                    },
                                    tint = IpbTheme.colors.primary.asBrush()
                                )
                        }
                    }
                }
                val size = when (i) {
                    0 -> 8
                    1 -> 10
                    else -> 12
                }
                val color = IpbTheme.colors.primary.asColor()
                Canvas(modifier = Modifier.fillMaxSize()) {
                    if (state.chosenTier == i || state.chosenTier == null) drawCircle(
                        color = color,
                        style = Stroke(
                            width = with(density) { 2.dp.toPx() },
                            pathEffect = PathEffect.dashPathEffect(
                                floatArrayOf(with(density) { size.dp.toPx() },
                                    with(density) { size.dp.toPx() }), 0f
                            )
                        ),
                        radius = r,
                        center = center
                    )
                }
                Box(
                    modifier = Modifier
                        .size(78.dp)
                        .clip(CircleShape)
                        .background(Color.Magenta)
                )
            }
        }
    }

    @Composable
    fun Tabs(
        selected: Int, onSelect: (Int) -> Unit
    ) {
        val tabList = listOf(
            stringResource(id = R.string.remoteness), stringResource(id = R.string.map)
        )
        TabRow(modifier = Modifier
            .padding(top = 28.dp)
            .width(240.dp)
            .height(40.dp)
            .clip(CircleShape),
            selectedTabIndex = selected,
            backgroundColor = IpbTheme.colors.surface.asColor(),
            divider = { },
            indicator = { }) {
            tabList.forEachIndexed { index, text ->
                Tab(modifier = Modifier
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
                    })
            }
        }
    }

    ThemedLayout(modifier = modifier, topBar = {
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
                    resId = R.drawable.ic_back,
                    tint = IpbTheme.colors.iconPrimary.asBrush()
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
                modifier = Modifier.align(Alignment.CenterEnd),
                state = state.readyToMeet,
                useComponent = useComponent
            )
        }
    }) { _, _ ->
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
                    Row(modifier = Modifier
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
                        verticalAlignment = Alignment.CenterVertically) {
                        BrushedText(
                            text = stringResource(id = R.string.choose_dating_interest),
                            tint = IpbTheme.colors.textButton.asBrush(),
                            style = IpbTheme.typography.subHeadlineRegular
                        )
                        BrushedIcon(
                            modifier = Modifier.rotate(rotation.value),
                            resId = R.drawable.ic_imh_exposable,
                            tint = IpbTheme.colors.iconSecondary.asBrush()
                        )
                    }
                    Column(modifier = Modifier
                        .constrainAs(exposedPicker) {
                            width = Dimension.value(240.dp)
                            top.linkTo(picker.bottom, 8.dp)
                            start.linkTo(picker.start)
                            end.linkTo(picker.end)
                        }
                        .zIndex(1f)) {
                        AnimatedVisibility(visible = exposed) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                state.interestsTargets.forEach {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(CircleShape)
                                            .border(
                                                width = 1.dp,
                                                brush = IpbTheme.colors.primary.asBrush(),
                                                shape = CircleShape
                                            )
                                            .niceClickable { }
                                            .padding(vertical = 10.dp),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        BrushedText(
                                            text = it.name,
                                            tint = IpbTheme.colors.primary.asBrush(),
                                            style = IpbTheme.typography.subHeadlineRegular
                                        )
                                    }
                                }
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
                        BrushedIcon(
                            modifier = Modifier.size(24.dp),
                            resId = R.drawable.ic_filter_empty,
                            tint = IpbTheme.colors.iconPrimary.asBrush()
                        )
                    }
                    Orbits(modifier = Modifier.constrainAs(circle) {
                        width = Dimension.fillToConstraints
                        top.linkTo(picker.bottom, 28.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    })
                } else if (selectedIndex == 1) {
                    AndroidView(factory = {
                        MapView(it).apply {

                        }
                    })
                }
            }
        }
    }
}


@Preview
@Composable
private fun DatingMainScreenPreview() {
    DatingMainScreen(
        state = DatingMainScreenState(
            readyToMeet = BrushedSwitchState(
                id = "magnis", enabled = false, turned = false
            ),
            users = listOf(
                AnotherUser(), AnotherUser(), AnotherUser(), AnotherUser(), AnotherUser()
            ),
            avatar = "dignissim",
            interestsTargets = listOf(
                DatingTarget(name = "Sport"),
                DatingTarget(name = "Cars")
            ),
            chosenTier = null,
            chosenInterest = DatingTarget()
        ), useComponent = UseDatingMainScreen.Empty()
    )
}