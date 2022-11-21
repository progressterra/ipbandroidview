package com.progressterra.ipbandroidview.ui.main

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.ui.search.SearchEffect
import com.progressterra.ipbandroidview.ui.search.SearchViewModel
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class MainNode(
    buildContext: BuildContext,
    private val onGoodsDetails: (String) -> Unit,
    private val onFilters: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: MainViewModel = getViewModel()
        val searchViewModel: SearchViewModel = getViewModel()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                is MainEffect.Toast -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        searchViewModel.collectSideEffect {
            when (it) {
                is SearchEffect.GoodsDetails -> onGoodsDetails(it.goodsId)
                is SearchEffect.Back -> Unit
                is SearchEffect.Filters -> onFilters()
            }
        }
        val state = viewModel.collectAsState()
        val searchState = searchViewModel.collectAsState()
        MainScreen(
            mainState = state::value,
            searchState = searchState::value,
            refresh = viewModel::refresh,
            back = searchViewModel::back,
            filters = searchViewModel::filters,
            favoriteSpecific = searchViewModel::favoriteSpecific,
            searchRefresh = searchViewModel::refresh,
            openDetails = searchViewModel::openDetails,
            keyword = searchViewModel::keyword,
            search = searchViewModel::search,
            clear = searchViewModel::clear
        )
    }
}