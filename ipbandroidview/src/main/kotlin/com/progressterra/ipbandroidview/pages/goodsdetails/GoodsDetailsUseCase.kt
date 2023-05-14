package com.progressterra.ipbandroidview.pages.goodsdetails

import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.GoodsParameters
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescriptionState
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.processes.store.FetchFavoriteIds
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ScreenState

interface GoodsDetailsUseCase {

    suspend operator fun invoke(id: String): Result<GoodsDetailsState>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val productRepository: ProductRepository,
        private val cartRepository: CartRepository,
        private val goodsDetailsMapper: GoodsDetailsMapper,
        private val fetchFavoriteIds: FetchFavoriteIds
    ) : AbstractUseCase(scrmRepository, provideLocation), GoodsDetailsUseCase {

        override suspend fun invoke(id: String): Result<GoodsDetailsState> = withToken { token ->
            val isFavorite = fetchFavoriteIds().getOrThrow().contains(id)
            val count = cartRepository.getGoodsQuantity(token, id).getOrThrow()
            val goods = productRepository.productByGoodsInventoryId(id)
                .getOrThrow()!!.first()
            GoodsDetailsState(
                description = GoodsDescriptionState(
                    name = "",
                    description = "",
                    company = ""
                ), gallery = ItemGalleryState(images = listOf()), name = ""
            )
        }

        data class ImageData(
            @SerializedName("datalist") val list: List<Item>
        ) {

            data class Item(
                @SerializedName("URL") val url: String
            )
        }
    }

    class Test : GoodsDetailsUseCase {

        override suspend fun invoke(id: String): Result<GoodsDetailsState> = Result.success(
            GoodsDetailsState(
                description = GoodsDescriptionState(
                    description = "Описание товара",
                    name = "Товар",
                    company = "IKEA",
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