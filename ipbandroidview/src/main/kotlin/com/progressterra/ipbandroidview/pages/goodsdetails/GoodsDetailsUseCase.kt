package com.progressterra.ipbandroidview.pages.goodsdetails

import com.progressterra.ipbandroidapi.api.ipbdelivery.IPBDeliveryRepository
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.buygoods.BuyGoodsState
import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonState
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescriptionState
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.processes.store.FetchFavoriteIds
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryMethodMapper
import com.progressterra.ipbandroidview.widgets.galleries.FetchGalleriesUseCase
import com.progressterra.ipbandroidview.widgets.galleries.GalleriesState

interface GoodsDetailsUseCase {

    suspend operator fun invoke(id: String): Result<GoodsDetailsState>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val productRepository: ProductRepository,
        private val fetchFavoriteIds: FetchFavoriteIds,
        private val fetchGalleriesUseCase: FetchGalleriesUseCase,
        private val deliveryRepository: IPBDeliveryRepository,
        private val deliveryMapper: DeliveryMethodMapper
    ) : GoodsDetailsUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(id: String): Result<GoodsDetailsState> = withToken { token ->
            val isFavorite = fetchFavoriteIds().getOrThrow().contains(id)
            val goods =
                productRepository.productByNomenclatureId(token, id).getOrThrow()!!
            val recommended =
                fetchGalleriesUseCase(goods.nomenclature?.listCatalogCategory?.first()!!).getOrThrow()
            val deliveryList =
                deliveryRepository.getDeliveryList(token).getOrThrow()
                    ?.map { deliveryMapper.map(it) }
                    ?: emptyList()
            GoodsDetailsState(description = GoodsDescriptionState(
                name = goods.nomenclature?.name ?: "",
                description = goods.nomenclature?.commerseDescription ?: "",
                favoriteButton = FavoriteButtonState(
                    id = goods.nomenclature?.idUnique!!, favorite = isFavorite
                ),
                properties = goods.listProductCharacteristic?.associate {
                    (it.characteristicType?.name ?: "") to (it.characteristicValue?.viewData ?: "")
                } ?: emptyMap(),
                availableDeliveries = deliveryList
            ),
                gallery = ItemGalleryState(images = goods.nomenclature?.listImages?.mapNotNull { it.urlData }
                    ?: emptyList()),
                name = goods.nomenclature?.name ?: "",
                buyGoods = BuyGoodsState(
                    price = SimplePrice(goods.inventoryData?.currentPrice ?: 0.0),
                    loan = "Рассрочка: ${goods.installmentPlanValue?.countMonthPayment} платежей по ${
                        SimplePrice(
                            goods.installmentPlanValue?.amountPaymentInMonth ?: 0.0
                        )
                    }"
                ), similarGoods = GalleriesState(items = recommended))
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