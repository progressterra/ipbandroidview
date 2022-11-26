package com.progressterra.ipbandroidview.ui.order

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.model.OrderGoods
import com.progressterra.ipbandroidview.model.OrderResult
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class OrderNode(
    buildContext: BuildContext,
    private val goods: List<OrderGoods>,
    private val onBack: () -> Unit,
    private val onCity: () -> Unit,
    private val onGoodsDetails: (String) -> Unit,
    private val onChoosePickUpPoint: () -> Unit,
    private val onNext: (OrderResult) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: OrderViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is OrderEffect.Back -> onBack()
                is OrderEffect.City -> onCity()
                is OrderEffect.GoodsDetails -> onGoodsDetails(it.goodsId)
                is OrderEffect.PickUp -> onChoosePickUpPoint()
                is OrderEffect.Next -> onNext(it.orderResult)
            }
        }
        var alreadyLaunched by rememberSaveable(goods) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setCart(goods)
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.collectAsState()
        OrderScreen(
            state = state::value,
            back = viewModel::back,
            goodsDetails = viewModel::goodsDetails,
            changeAddress = viewModel::changeAddress,
            selectPickUpPoint = viewModel::selectPickUpPoint,
            selectDeliveryMethod = viewModel::selectDeliveryMethod,
            selectPayment = viewModel::selectPayment,
            editApartment = viewModel::editApartment,
            editComment = viewModel::editComment,
            editEntryway = viewModel::editEntryway,
            changeUseBonuses = viewModel::changeUseBonuses,
            editPromoCode = viewModel::editPromoCode,
            applyPromoCode = viewModel::applyPromoCode,
            changeReceiveReceipt = viewModel::changeReceiveReceipt,
            editEmail = viewModel::editEmail,
            payment = viewModel::payment,
            openUrl = viewModel::openUrl,
            refresh = viewModel::refresh
        )
    }
}