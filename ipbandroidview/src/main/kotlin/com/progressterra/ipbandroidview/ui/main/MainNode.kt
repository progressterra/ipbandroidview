package com.progressterra.ipbandroidview.ui.main

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidapi.Constants
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class MainNode(
    buildContext: BuildContext,
    private val onGoodsDetails: (String) -> Unit,
    private val onFilters: () -> Unit,
    private val onBonuses: () -> Unit,
    private val onSearch: (String, String) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: MainViewModel = getViewModel()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                is MainEffect.Toast -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT)
                    .show()
                is MainEffect.Bonuses -> onBonuses()
                is MainEffect.GoodsDetails -> onGoodsDetails(it.goodsId)
                is MainEffect.Search -> onSearch(Constants.EMPTY_ID, it.keyword)
            }
        }

        val state = viewModel.collectAsState()
        MainScreen(
            state = state::value,
            refresh = viewModel::refresh,
            favoriteSpecific = viewModel::favoriteSpecific,
            openDetails = viewModel::openDetails,
            keyword = viewModel::keyword,
            search = viewModel::search,
            clear = viewModel::clear,
            bonuses = viewModel::bonuses
        )
    }
}