package com.progressterra.ipbandroidview.pages.goodsdetails

import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.GoodsFilter
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.features.buygoods.BuyGoodsState
import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonState
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescriptionState
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryState
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.processes.store.FetchFavoriteIds
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.widgets.galleries.GalleriesState
import kotlinx.coroutines.flow.emptyFlow

interface GoodsDetailsUseCase {

    suspend operator fun invoke(id: String): Result<GoodsDetailsState>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val productRepository: ProductRepository,
        private val fetchFavoriteIds: FetchFavoriteIds,
        private val goodsUseCase: GoodsUseCase,
        private val manageResources: ManageResources
    ) : GoodsDetailsUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(id: String): Result<GoodsDetailsState> = withToken { token ->
            val isFavorite = fetchFavoriteIds().getOrThrow().contains(id)
            val goods =
                productRepository.productByNomenclatureId(token, id).getOrThrow()!!.toGoodsItem()
            val recommended =
                if (goods.categoryId.isNotEmpty()) goodsUseCase(GoodsFilter(categoryId = goods.categoryId)).getOrThrow() else emptyFlow()
            GoodsDetailsState(
                description = GoodsDescriptionState(
                    name = goods.name,
                    description = goods.description,
                    favoriteButton = FavoriteButtonState(
                        id = goods.id, favorite = isFavorite
                    ),
                    properties = goods.properties
                ),
                gallery = ItemGalleryState(images = goods.images),
                name = goods.name,
                buyGoods = BuyGoodsState(
                    oldPrice = goods.oldPrice,
                    price = goods.price,
                    installment = goods.installment
                ),
                similarGoods = GalleriesState(
                    items = recommended,
                    title = manageResources.string(R.string.similar_goods)
                )
            )
        }
    }

    class Test : GoodsDetailsUseCase {

        override suspend fun invoke(id: String): Result<GoodsDetailsState> = Result.success(
            GoodsDetailsState(
                description = GoodsDescriptionState(
                    description = "Описание товара",
                    name = "Товар",
                ), gallery = ItemGalleryState(
                    images = listOf(
                        "https://www.ikea.com/ru/ru/images/products/leifarne-krug-iz-serebra__0712009_PE729202_S5.JPG?f=xxs",
                        "https://www.ikea.com/ru/ru/images/products/leifarne-krug-iz-serebra__0712009_PE729202_S5.JPG?f=xxs",
                        "https://www.ikea.com/ru/ru/images/products/leifarne-krug-iz-serebra__0712009_PE729202_S5.JPG?f=xxs",
                        "https://www.ikea.com/ru/ru/images/products/leifarne-krug-iz-serebra__0712009_PE729202_S5.JPG?f=xxs",
                        "https://www.ikea.com/ru/ru/images/products/leifarne-krug-iz-serebra__0712009_PE729202_S5.JPG?f=xxs",
                        "https://www.ikea.com/ru/ru/images/products/leifarne-krug-iz-serebra__0712009_PE729202_S5.JPG?f=xxs"
                    )
                )
            )
        )
    }
}