package com.progressterra.ipbandroidview.pages.datingmain

import DatingMainScreenEvent
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
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

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
        val tier1 = if (state.chosenTier == 1) state.users.size else 5
        val tier2 = if (state.chosenTier == 2) state.users.size else 3
        val tier3 = if (state.chosenTier == 3) state.users.size else 2
        val animateTier1 = rememberSaveable(tier1) {
            val step = 360f / tier1
            List(tier1) { index -> Animatable(index * step) }
        }
        val animateTier2 = rememberSaveable(tier2) {
            val step = 360f / tier2
            List(tier2) { index -> Animatable(index * step) }
        }
        val animateTier3 = rememberSaveable(tier3) {
            val step = 360f / tier3
            List(tier3) { index -> Animatable(index * step) }
        }
        LaunchedEffect(animateTier1, animateTier2, animateTier3) {
            if (state.chosenTier == 1 || state.chosenTier == 0) animateTier1.forEachIndexed { index, anim ->
                val step = 360f / tier1
                val initial = index * step
                launch {
                    anim.animateTo(
                        targetValue = initial + 360f, animationSpec = infiniteRepeatable(
                            animation = tween(durationMillis = rotationTime, easing = LinearEasing)
                        )
                    )
                }
            }
            if (state.chosenTier == 2 || state.chosenTier == 0) animateTier2.forEachIndexed { index, anim ->
                val step = 360f / tier2
                val initial = index * step
                launch {
                    anim.animateTo(
                        targetValue = initial + 360f, animationSpec = infiniteRepeatable(
                            animation = tween(durationMillis = rotationTime, easing = LinearEasing)
                        )
                    )
                }
            }
            if (state.chosenTier == 3 || state.chosenTier == 0) animateTier3.forEachIndexed { index, anim ->
                val step = 360f / tier3
                val initial = index * step
                launch {
                    anim.animateTo(
                        targetValue = initial + 360f, animationSpec = infiniteRepeatable(
                            animation = tween(durationMillis = rotationTime, easing = LinearEasing)
                        )
                    )
                }
            }
        }
        BoxWithConstraints(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(1f), contentAlignment = Alignment.Center
        ) {
            val density = LocalDensity.current
            val r =
                constraints.maxWidth / 2f - with(density) { if (state.chosenTier == 0) 9.dp.toPx() else 25.dp.toPx() }
            val s = with(density) { r.toDp() } - 39.dp
            val r2 = with(density) { ((s * 2 / 3) + 39.dp).toPx() }
            val r3 = with(density) { ((s / 3) + 39.dp).toPx() }
            if (state.chosenTier == 1 || state.chosenTier == 0) for (i in 0 until tier1) {
                val x = (r + cos(Math.toRadians(animateTier1[i].value.toDouble())) * r).toFloat()
                val y = (r + sin(Math.toRadians(animateTier1[i].value.toDouble())) * r).toFloat()
                val intOffset = IntOffset(
                    x.roundToInt(), y.roundToInt()
                )
                Box(modifier = Modifier
                    .fillMaxSize()
                    .offset { intOffset }
                    .zIndex(1f)) {
                    if (state.chosenTier == 1) {
                        User(user = state.users[i])
                    } else {
                        BrushedIcon(
                            resId = R.drawable.ic_tier_1,
                            tint = IpbTheme.colors.primary.asBrush()
                        )
                    }
                }
            }
            if (state.chosenTier == 2 || state.chosenTier == 0) for (i in 0 until tier2) {
                val step = 360f / tier2
                val x =
                    (r2 + cos(Math.toRadians(step / 2 + animateTier2[i].value.toDouble())) * r2).toFloat()
                val y =
                    (r2 + sin(Math.toRadians(step / 2 + animateTier2[i].value.toDouble())) * r2).toFloat()
                val intOffset = IntOffset(
                    x.roundToInt(), y.roundToInt()
                )
                val correctionOffset = IntOffset((r - r2).roundToInt(), (r - r2).roundToInt())
                Box(modifier = Modifier
                    .fillMaxSize()
                    .offset { intOffset + correctionOffset }
                    .zIndex(1f)) {

                    if (state.chosenTier == 2) {
                        User(user = state.users[i])
                    } else {
                        BrushedIcon(
                            resId = R.drawable.ic_tier_2,
                            tint = IpbTheme.colors.primary.asBrush()
                        )
                    }
                }
            }
            if (state.chosenTier == 3 || state.chosenTier == 0) for (i in 0 until tier3) {
                val x = (r3 + cos(Math.toRadians(animateTier3[i].value.toDouble())) * r3).toFloat()
                val y = (r3 + sin(Math.toRadians(animateTier3[i].value.toDouble())) * r3).toFloat()
                val intOffset = IntOffset(
                    x.roundToInt(), y.roundToInt()
                )
                val correctionOffset = IntOffset((r - r3).roundToInt(), (r - r3).roundToInt())
                Box(modifier = Modifier
                    .fillMaxSize()
                    .offset { intOffset + correctionOffset }
                    .zIndex(1f)) {
                    if (state.chosenTier == 3) {
                        User(user = state.users[i])
                    } else {
                        BrushedIcon(
                            resId = R.drawable.ic_tier_3,
                            tint = IpbTheme.colors.primary.asBrush()
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .size(78.dp)
                    .clip(CircleShape)
                    .background(Color.Magenta)
            )
            val tierEffect1 = Stroke(
                width = with(density) { 2.dp.toPx() },
                pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(with(density) { 8.dp.toPx() },
                        with(density) { 8.dp.toPx() }), 0f
                )
            )
            val tierEffect2 = Stroke(
                width = with(density) { 2.dp.toPx() },
                pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(with(density) { 10.dp.toPx() },
                        with(density) { 10.dp.toPx() }), 0f
                )
            )
            val tierEffect3 = Stroke(
                width = with(density) { 2.dp.toPx() },
                pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(with(density) { 12.dp.toPx() },
                        with(density) { 12.dp.toPx() }), 0f
                )
            )
            val color = IpbTheme.colors.primary.asColor()
            Canvas(modifier = Modifier.fillMaxSize()) {
                if (state.chosenTier == 1 || state.chosenTier == 0) drawCircle(
                    color = color,
                    style = tierEffect1,
                    radius = r,
                    center = center
                )
                if (state.chosenTier == 2 || state.chosenTier == 0) drawCircle(
                    color = color,
                    style = tierEffect2,
                    radius = r2,
                    center = center
                )
                if (state.chosenTier == 3 || state.chosenTier == 0) drawCircle(
                    color = color,
                    style = tierEffect3,
                    radius = r3,
                    center = center
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
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
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
            interestsTargets = listOf(DatingTarget(name = "Sport"), DatingTarget(name = "Cars")),
            chosenTier = 1,
            chosenInterest = DatingTarget()
        ), useComponent = UseDatingMainScreen.Empty()
    )
}