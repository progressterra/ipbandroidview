package com.progressterra.ipbandroidview.entities

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.progressterra.ipbandroidapi.api.address.models.DataAddress
import com.progressterra.ipbandroidapi.api.address.models.RGAddress
import com.progressterra.ipbandroidapi.api.cart.models.DHSaleHeadAsOrderViewModel
import com.progressterra.ipbandroidapi.api.cart.models.TypeStatusOrder
import com.progressterra.ipbandroidapi.api.documents.models.CharacteristicData
import com.progressterra.ipbandroidapi.api.documents.models.FieldData
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidapi.api.documents.models.TypeValueCharacteristic
import com.progressterra.ipbandroidapi.api.product.models.ProductView
import com.progressterra.ipbandroidapi.api.suggestion.model.Suggestion
import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionExtendedInfo
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionUI
import com.progressterra.ipbandroidview.features.cartcard.CartCardState
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.features.ordercard.OrderCardState
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType
import com.progressterra.ipbandroidview.widgets.documents.DocumentsState

fun pricesSum(prices: List<SimplePrice>): SimplePrice {
    var sum = SimplePrice()
    prices.forEach { sum += it }
    return sum
}

fun Int.toSimplePrice(): SimplePrice = SimplePrice(this)

fun Double.toSimplePrice(): SimplePrice = SimplePrice(toInt())

//TODO check id
fun DHSaleHeadAsOrderViewModel.toOrder(manageResources: ManageResources) = Order(
    itemsIds = listDRSale?.map { it.idrfNomenclature!! } ?: emptyList(),
    price = pricesSum(listDRSale?.map { it.amountEndPrice!!.toSimplePrice() } ?: emptyList()),
    id = idUnique!!,
    number = number ?: "",
    status = statusOrder?.toString(manageResources) ?: ""
)

fun ProductView.toGoodsItem(): GoodsItem = GoodsItem(
    id = nomenclature?.idUnique!!,
    categoryId = nomenclature?.listCatalogCategory?.first()!!,
    name = nomenclature?.name ?: "",
    description = nomenclature?.commerseDescription ?: "",
    images = nomenclature?.listImages?.map { it.urlData!! } ?: emptyList(),
    imageUrl = nomenclature?.listImages?.first()?.urlData ?: "",
    price = inventoryData?.currentPrice?.toSimplePrice() ?: SimplePrice(),
    installment = Installment(
        months = installmentPlanValue?.countMonthPayment ?: 0,
        perMonth = installmentPlanValue?.amountPaymentInMonth?.toSimplePrice() ?: SimplePrice()
    ),
    properties = listProductCharacteristic?.associate {
        (it.characteristicType?.name ?: "") to (it.characteristicValue?.viewData ?: "")
    } ?: emptyMap(),
    count = countInCart ?: 0,
)

fun GoodsItem.toCartCardState(): CartCardState = CartCardState(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    installment = installment,
    counter = CounterState(
        id = id,
        count = count
    ),
    properties = properties
)

fun GoodsItem.toStoreCardState(): StoreCardState = StoreCardState(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    installment = installment,
    counter = CounterState(
        id = id,
        count = count
    )
)

fun GoodsItem.toOrderCardState(): OrderCardState = OrderCardState(
    id = id,
    name = name,
    price = price * count,
    imageUrl = imageUrl,
    installment = installment
)

fun TypeStatusOrder.toString(manageResources: ManageResources) = when (this) {
    TypeStatusOrder.ORDER -> manageResources.string(R.string.order_confirmed)
    TypeStatusOrder.CONFIRM_FROM_STORE -> manageResources.string(R.string.order_confirm_from_store)
    TypeStatusOrder.CONFIRM_FROM_CALL_CENTER -> manageResources.string(R.string.order_confirm_from_call_center)
    TypeStatusOrder.SENT_TO_WAREHOUSE -> manageResources.string(R.string.order_sent_to_warehouse)
    TypeStatusOrder.SENT_DELIVERY_SERVICE -> manageResources.string(R.string.order_sent_delivery_service)
    TypeStatusOrder.ON_PICK_UP_POINT -> manageResources.string(R.string.order_on_pick_point)
    TypeStatusOrder.DELIVERED -> manageResources.string(R.string.order_delivered)
    TypeStatusOrder.CANCELED -> manageResources.string(R.string.order_canceled)
    TypeStatusOrder.ONE_CLICK -> manageResources.string(R.string.one_click)
    TypeStatusOrder.CART -> manageResources.string(R.string.cart)
}


fun SuggestionExtendedInfo.convertSuggestionToAddressUIModel() =
    AddressUI(
        idUnique = "00000000-0000-0000-0000-000000000000",
        idClient = "00000000-0000-0000-0000-000000000000",
        dateAdded = "0001-01-01T00:00:00",
        dateVerification = "",
        idManagerVerification = "",
        dateDeactivation = "",
        defaultShipping = "",
        defaultBilling = "",
        fiasIDCountry = "",
        fiasIDRegion = regionFiasId ?: "",
        fiasIDCity = cityFiasId ?: "",
        fiasIDArea = areaFiasId ?: "",
        fiasIDDistrict = cityDistrictFiasId ?: "",
        fiasIDStreet = streetFiasId ?: "",
        fiasIDHouse = houseFiasId ?: "",
        kladrHouse = houseKladrId ?: "",
        kladrCountry = "00000000-0000-0000-0000-000000000000",
        kladrRegion = regionKladrId ?: "",
        kladrCity = cityKladrId ?: "",
        kladrArea = areaKladrId ?: "",
        kladrDistrict = cityDistrictKladrId ?: "",
        kladrStreet = streetKladrId ?: "",
        nameCountry = country ?: "",
        nameRegion = region ?: "",
        nameCity = city ?: "",
        nameStreet = street ?: "",
        nameArea = area ?: "",
        nameDistrict = cityDistrict ?: "",
        postalCode = postalCode ?: "",
        houseNUmber = house ?: "",
        building = block ?: "",
        apartment = flat?.toInt() ?: 0,
        entrance = entrance?.toInt() ?: 0,
        floor = floor?.toInt() ?: 0,
        latitude = geoLat?.toDouble() ?: 0.0,
        longitude = geoLon?.toDouble() ?: 0.0
    )

fun Suggestion.convertSuggestionsDtoToUIModels() = SuggestionUI(
    suggestionExtendedInfo = suggestionExtendedInfo ?: SuggestionExtendedInfo(),
    previewOfSuggestion = previewOfSuggestion ?: ""
)

fun AddressUI.convertAddressUiModelToDto() = RGAddress(
    idUnique = idUnique,
    nameCity = nameCity,
    postalCode = postalCode,
    building = building,
    apartment = apartment,
    floor = floor,
    nameStreet = nameStreet,
    entrance = entrance,
    idClient = idClient,
    dateAdded = dateAdded,
    dateVerification = dateVerification,
    idManagerVerification = idManagerVerification,
    dateDeactivation = dateDeactivation,
    defaultBilling = defaultBilling,
    defaultShipping = defaultShipping,
    fiasIDCountry = fiasIDCountry,
    fiasIDRegion = fiasIDRegion,
    fiasIDCity = fiasIDCity,
    fiasIDArea = fiasIDArea,
    fiasIDDistrict = fiasIDDistrict,
    fiasIDHouse = fiasIDHouse,
    fiasIDStreet = fiasIDStreet,
    kladrCountry = kladrCountry,
    kladrRegion = kladrRegion,
    kladrCity = kladrCity,
    kladrArea = kladrArea,
    kladrDistrict = kladrArea,
    kladrStreet = kladrStreet,
    kladrHouse = kladrHouse,
    nameCountry = nameCountry,
    nameRegion = nameRegion,
    nameArea = nameArea,
    nameDistrict = nameDistrict,
    houseNUmber = houseNUmber,
    latitude = latitude,
    longitude = longitude
)

fun DataAddress.convertDtoToAddressUiModel(): List<AddressUI> = listAddAddress?.map {
    AddressUI(
        idUnique = it.idUnique ?: "",
        nameCity = it.nameCity ?: "",
        postalCode = it.postalCode ?: "",
        building = it.building ?: "",
        apartment = it.apartment ?: 0,
        floor = it.floor ?: 0,
        nameStreet = it.nameStreet ?: "",
        entrance = it.entrance ?: 0,
        idClient = it.idClient ?: "",
        dateAdded = it.dateAdded ?: "",
        dateVerification = it.dateVerification ?: "",
        idManagerVerification = it.idManagerVerification ?: "",
        dateDeactivation = it.dateDeactivation ?: "",
        defaultBilling = it.defaultBilling ?: "",
        defaultShipping = it.defaultShipping ?: "",
        fiasIDCountry = it.fiasIDCountry ?: "",
        fiasIDRegion = it.fiasIDRegion ?: "",
        fiasIDCity = it.fiasIDCity ?: "",
        fiasIDArea = it.fiasIDArea ?: "",
        fiasIDDistrict = it.fiasIDDistrict ?: "",
        fiasIDHouse = it.fiasIDHouse ?: "",
        fiasIDStreet = it.fiasIDStreet ?: "",
        kladrCountry = it.kladrCountry ?: "",
        kladrRegion = it.kladrRegion ?: "",
        kladrCity = it.kladrCity ?: "",
        kladrArea = it.kladrArea ?: "",
        kladrDistrict = it.kladrArea ?: "",
        kladrStreet = it.kladrStreet ?: "",
        kladrHouse = it.kladrHouse ?: "",
        nameCountry = it.nameCountry ?: "",
        nameRegion = it.nameRegion ?: "",
        nameArea = it.nameArea ?: "",
        nameDistrict = it.nameDistrict ?: "",
        houseNUmber = it.houseNUmber ?: "",
        latitude = it.latitude ?: 0.0,
        longitude = it.longitude ?: 0.0
    )
} ?: emptyList()

fun CharacteristicData.toDocument(gson: Gson) = Document(
    status = characteristicValue?.statusDoc ?: TypeStatusDoc.NOT_FILL,
    docName = characteristicType?.name ?: "",
    id = characteristicValue?.idUnique!!,
    entries = (characteristicType?.dataInJSON?.let {
        gson.fromJson<List<FieldData>?>(
            it,
            object : TypeToken<List<FieldData>>() {}.type
        )
    } ?: emptyList()).map {
        TextFieldState(
            id = it.order.toString(),
            text = it.valueData ?: "",
            placeholder = it.comment,
            label = it.name,
            type = when (it.typeValue) {
                TypeValueCharacteristic.AS_STRING -> TextInputType.DEFAULT
                TypeValueCharacteristic.AS_NUMBER -> TextInputType.NUMBER
                TypeValueCharacteristic.AS_DATE_TIME -> TextInputType.DEFAULT
                TypeValueCharacteristic.AS_REFERENCE -> TextInputType.DEFAULT
                TypeValueCharacteristic.AS_CUSTOM_AS_JSON -> TextInputType.DEFAULT
                null -> TextInputType.DEFAULT
            }
        )
    },
    photo = if (imageRequired!!) DocumentPhotoState(
        items = characteristicValue?.listImages?.map { img ->
            MultisizedImage(
                id = img.idUnique!!,
                local = false,
                toRemove = false,
                url = img.urlData!!
            )
        } ?: emptyList()) else null
)

fun Document.toDocumentsStateItem() = DocumentsState.Item(
    id = id,
    name = docName,
    status = status,
    entries = entries,
    photo = photo
)