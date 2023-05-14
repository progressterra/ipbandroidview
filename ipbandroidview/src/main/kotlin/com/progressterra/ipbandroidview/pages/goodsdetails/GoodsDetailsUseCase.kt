package com.progressterra.ipbandroidview.pages.goodsdetails

import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonState
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescriptionState
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryState
import com.progressterra.ipbandroidview.processes.store.FetchFavoriteIds

interface GoodsDetailsUseCase {

    suspend operator fun invoke(id: String): Result<GoodsDetailsState>

    class Base(
        private val productRepository: ProductRepository,
        private val fetchFavoriteIds: FetchFavoriteIds
    ) : GoodsDetailsUseCase {

        override suspend fun invoke(id: String): Result<GoodsDetailsState> = runCatching {
            val isFavorite = fetchFavoriteIds().getOrThrow().contains(id)
            val goods = productRepository.productByGoodsInventoryId(id)
                .getOrThrow()!!.first()
            GoodsDetailsState(
                description = GoodsDescriptionState(
                    name = goods.nomenclature?.name ?: "",
                    description = goods.nomenclature?.commerseDescription ?: "",
                    favoriteButton = FavoriteButtonState(
                        id = goods.nomenclature?.idUnique!!,
                        favorite = isFavorite
                    )
                ),
                gallery = ItemGalleryState(images = goods.nomenclature?.listImages?.map { it.urlData!! }
                    ?: emptyList()),
                name = goods.nomenclature?.name ?: ""
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