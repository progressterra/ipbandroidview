package com.progressterra.ipbandroidview.entities

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.progressterra.ipbandroidapi.api.address.models.RGAddress
import com.progressterra.ipbandroidapi.api.cart.models.DHSaleHeadAsOrderViewModel
import com.progressterra.ipbandroidapi.api.cart.models.DRSaleForCartAndOrder
import com.progressterra.ipbandroidapi.api.cart.models.TypeStatusOrder
import com.progressterra.ipbandroidapi.api.documents.models.CharacteristicData
import com.progressterra.ipbandroidapi.api.documents.models.FieldData
import com.progressterra.ipbandroidapi.api.documents.models.RFCharacteristicTypeViewModel
import com.progressterra.ipbandroidapi.api.documents.models.RFCharacteristicValueViewModel
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidapi.api.documents.models.TypeValueCharacteristic
import com.progressterra.ipbandroidapi.api.product.models.ProductView
import com.progressterra.ipbandroidapi.api.suggestion.model.Suggestion
import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionExtendedInfo
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionUI
import com.progressterra.ipbandroidview.features.cartcard.CartCardState
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.features.ordercard.OrderCardState
import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactState
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState
import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState
import com.progressterra.ipbandroidview.features.receipt.ReceiptState
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardState
import com.progressterra.ipbandroidview.pages.documentdetails.DocumentDetailsState
import com.progressterra.ipbandroidview.pages.wantthis.WantThisScreenState
import com.progressterra.ipbandroidview.shared.CreateId
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.print
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType
import com.progressterra.ipbandroidview.widgets.orderitems.OrderItemsState

fun pricesSum(prices: List<SimplePrice>): SimplePrice {
    var sum = SimplePrice()
    prices.forEach { sum += it }
    return sum
}

fun Int.toSimplePrice(): SimplePrice = SimplePrice(this)

fun Double.toSimplePrice(): SimplePrice = SimplePrice(toInt())

fun DHSaleHeadAsOrderViewModel.toOrder() =
    Order(itemsIds = listDRSale?.map { it.idrfNomenclature!! } ?: emptyList(),
        price = pricesSum(listDRSale?.map { it.amountEndPrice!!.toSimplePrice() } ?: emptyList()),
        id = idUnique!!,
        number = number ?: "",
        status = statusOrder ?: TypeStatusOrder.CANCELED,
        date = dateAdded?.parseToDate()?.print() ?: "")

fun ProductView.toGoodsItem(): GoodsItem = GoodsItem(
    id = nomenclature?.idUnique!!,
    categoryId = nomenclature?.listCatalogCategory?.first()!!,
    name = nomenclature?.name ?: "",
    description = nomenclature?.commerseDescription ?: "",
    images = nomenclature?.listImages?.map { it.urlData!! } ?: emptyList(),
    image = nomenclature?.listImages?.firstOrNull()?.urlData ?: "",
    oldPrice = inventoryData?.beginPrice?.toSimplePrice() ?: SimplePrice(),
    price = inventoryData?.currentPrice?.toSimplePrice() ?: SimplePrice(),
    installment = Installment(
        months = installmentPlanValue?.countMonthPayment ?: 0,
        perMonth = installmentPlanValue?.amountPaymentInMonth?.toSimplePrice() ?: SimplePrice()
    ),
    properties = listProductCharacteristic?.map {
        (it.characteristicType?.name ?: "") to (it.characteristicValue?.viewData ?: "")
    } ?: emptyList(),
    count = countInCart ?: 0
)

fun GoodsItem.toCartCardState(): CartCardState = CartCardState(
    id = id,
    name = name,
    oldPrice = oldPrice,
    price = price,
    image = image,
    installment = installment,
    counter = CounterState(
        id = id, count = count
    ),
    properties = properties
)

fun GoodsItem.toStoreCardState(): StoreCardState = StoreCardState(
    id = id,
    name = name,
    oldPrice = oldPrice,
    price = price,
    image = image,
    installment = installment,
    counter = CounterState(
        id = id, count = count
    )
)

fun GoodsItem.toOrderCardState(): OrderCardState = OrderCardState(
    id = id,
    name = name,
    oldPrice = oldPrice * count,
    price = price * count,
    image = image,
    installment = installment,
    properties = properties
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


fun SuggestionExtendedInfo.convertSuggestionToAddressUIModel(time: String) = AddressUI(
    idUnique = "00000000-0000-0000-0000-000000000000",
    idClient = "00000000-0000-0000-0000-000000000000",
    dateAdded = "0001-01-01T00:00:00",
    dateVerification = "",
    idManagerVerification = "",
    dateDeactivation = "",
    defaultShipping = time,
    defaultBilling = time,
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
    apartment = flat ?: "",
    entrance = entrance ?: "",
    floor = floor?.toInt() ?: 0,
    latitude = geoLat?.toDouble() ?: 0.0,
    longitude = geoLon?.toDouble() ?: 0.0
)

fun TypeStatusOrder.canBeTracker(): Boolean {
    val trackable = listOf(
        TypeStatusOrder.CONFIRM_FROM_STORE,
        TypeStatusOrder.CONFIRM_FROM_CALL_CENTER,
        TypeStatusOrder.SENT_TO_WAREHOUSE,
        TypeStatusOrder.SENT_DELIVERY_SERVICE,
        TypeStatusOrder.ON_PICK_UP_POINT,
        TypeStatusOrder.DELIVERED
    )
    return trackable.contains(this)
}

fun Suggestion.convertSuggestionsDtoToUIModels() = SuggestionUI(
    suggestionExtendedInfo = suggestionExtendedInfo ?: SuggestionExtendedInfo(),
    previewOfSuggestion = previewOfSuggestion ?: ""
)

fun AddressUI.convertAddressUiModelToDto() = RGAddress(
    idUnique = idUnique,
    nameCity = nameCity,
    postalCode = postalCode,
    building = building,
    apartment = apartment.toIntOrNull() ?: 0,
    floor = floor,
    nameStreet = nameStreet,
    entrance = entrance.toIntOrNull() ?: 0,
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

fun RGAddress.toAddressUiModel() = AddressUI(
    idUnique = idUnique ?: "",
    nameCity = nameCity ?: "",
    postalCode = postalCode ?: "",
    building = building ?: "",
    apartment = apartment?.toString() ?: "",
    floor = floor ?: 0,
    nameStreet = nameStreet ?: "",
    entrance = entrance?.toString() ?: "",
    idClient = idClient ?: "",
    dateAdded = dateAdded ?: "",
    dateVerification = dateVerification ?: "",
    idManagerVerification = idManagerVerification ?: "",
    dateDeactivation = dateDeactivation ?: "",
    defaultBilling = defaultBilling ?: "",
    defaultShipping = defaultShipping ?: "",
    fiasIDCountry = fiasIDCountry ?: "",
    fiasIDRegion = fiasIDRegion ?: "",
    fiasIDCity = fiasIDCity ?: "",
    fiasIDArea = fiasIDArea ?: "",
    fiasIDDistrict = fiasIDDistrict ?: "",
    fiasIDHouse = fiasIDHouse ?: "",
    fiasIDStreet = fiasIDStreet ?: "",
    kladrCountry = kladrCountry ?: "",
    kladrRegion = kladrRegion ?: "",
    kladrCity = kladrCity ?: "",
    kladrArea = kladrArea ?: "",
    kladrDistrict = kladrArea ?: "",
    kladrStreet = kladrStreet ?: "",
    kladrHouse = kladrHouse ?: "",
    nameCountry = nameCountry ?: "",
    nameRegion = nameRegion ?: "",
    nameArea = nameArea ?: "",
    nameDistrict = nameDistrict ?: "",
    houseNUmber = houseNUmber ?: "",
    latitude = latitude ?: 0.0,
    longitude = longitude ?: 0.0
)

fun CharacteristicData.toDocument(gson: Gson, createId: CreateId) =
    Document(status = characteristicValue?.statusDoc ?: TypeStatusDoc.NOT_FILL,
        docName = characteristicType?.name ?: "",
        id = characteristicValue?.idUnique!!,
        entries = (characteristicValue?.valueAsJSON?.let {
            gson.fromJson<List<FieldData>?>(
                it, object : TypeToken<List<FieldData>>() {}.type
            )
        } ?: emptyList()).map {
            TextFieldState(
                id = createId(),
                text = it.valueData ?: "",
                placeholder = it.comment,
                label = it.name,
                type = when (it.typeValue) {
                    TypeValueCharacteristic.AS_STRING -> TextInputType.DEFAULT
                    TypeValueCharacteristic.AS_NUMBER -> TextInputType.NUMBER
                    TypeValueCharacteristic.AS_DATE_TIME -> TextInputType.DATE
                    TypeValueCharacteristic.AS_REFERENCE -> TextInputType.DEFAULT
                    TypeValueCharacteristic.AS_CUSTOM_AS_JSON -> TextInputType.DEFAULT
                    null -> TextInputType.DEFAULT
                }
            )
        },
        photo = if (imageRequired!!) DocumentPhotoState(items = characteristicValue?.listImages?.map { img ->
            MultisizedImage(
                id = img.idUnique!!, local = false, toRemove = false, url = img.urlData!!
            )
        } ?: emptyList()) else null,
        additionalValue = characteristicValue?.valueAsReference ?: "")

fun RFCharacteristicValueViewModel.toDocument(gson: Gson, createId: CreateId) =
    Document(status = statusDoc ?: TypeStatusDoc.NOT_FILL,
        docName = characteristicType?.name ?: "",
        id = idUnique!!,
        entries = (valueAsJSON?.let {
            gson.fromJson<List<FieldData>?>(
                it, object : TypeToken<List<FieldData>>() {}.type
            )
        } ?: emptyList()).map {
            TextFieldState(
                id = createId(),
                text = it.valueData ?: "",
                placeholder = it.comment,
                label = it.name,
                type = when (it.typeValue) {
                    TypeValueCharacteristic.AS_STRING -> TextInputType.DEFAULT
                    TypeValueCharacteristic.AS_NUMBER -> TextInputType.NUMBER
                    TypeValueCharacteristic.AS_DATE_TIME -> TextInputType.DATE
                    TypeValueCharacteristic.AS_REFERENCE -> TextInputType.DEFAULT
                    TypeValueCharacteristic.AS_CUSTOM_AS_JSON -> TextInputType.DEFAULT
                    null -> TextInputType.DEFAULT
                }
            )
        },
        photo = DocumentPhotoState(items = listImages?.map { img ->
            MultisizedImage(
                id = img.idUnique!!, local = false, toRemove = false, url = img.urlData!!
            )
        } ?: emptyList()),
        additionalValue = valueAsReference ?: "")

fun RFCharacteristicTypeViewModel.toDocument(gson: Gson, createId: CreateId) =
    Document(
        docName = name ?: "",
        entries = (dataInJSON?.let {
            gson.fromJson<List<FieldData>?>(
                it, object : TypeToken<List<FieldData>>() {}.type
            )
        } ?: emptyList()).map {
            TextFieldState(
                id = createId(),
                text = it.valueData ?: "",
                placeholder = it.comment,
                label = it.name,
                type = when (it.typeValue) {
                    TypeValueCharacteristic.AS_STRING -> TextInputType.DEFAULT
                    TypeValueCharacteristic.AS_NUMBER -> TextInputType.NUMBER
                    TypeValueCharacteristic.AS_DATE_TIME -> TextInputType.DATE
                    TypeValueCharacteristic.AS_REFERENCE -> TextInputType.DEFAULT
                    TypeValueCharacteristic.AS_CUSTOM_AS_JSON -> TextInputType.DEFAULT
                    null -> TextInputType.DEFAULT
                }
            )
        },
        photo = DocumentPhotoState()
    )

fun Document.fromTemplateToReal(real: Document) = real.copy(
    entries = entries,
    photo = photo
)

fun TextFieldState.toFieldData(id: String) = FieldData(
    idrfCharacteristicType = id,
    name = label,
    comment = placeholder,
    order = 0,
    typeValue = type.toTypeValueCharacteristic(),
    valueData = formatByType()
)

fun Document.toDocumentDetailsState(): DocumentDetailsState {
    val canBeEdit = when (status) {
        TypeStatusDoc.NOT_FILL -> true
        TypeStatusDoc.WAIT_IMAGE -> true
        TypeStatusDoc.WAIT_REVIEW -> false
        TypeStatusDoc.REJECTED -> true
        TypeStatusDoc.CONFIRMED -> false
    }
    return DocumentDetailsState(
        id = id,
        docName = docName,
        canBeEdit = canBeEdit,
        entries = entries.map { it.copy(enabled = canBeEdit) },
        photo = photo?.copy(enabled = canBeEdit),
        apply = ButtonState(
            id = "apply", enabled = false
        )
    )
}

fun DocumentDetailsState.toDocument() = Document(
    id = id,
    docName = docName,
    entries = entries,
    photo = photo
)

fun WantThisScreenState.toDocument() = Document(
    id = id,
    entries = entries,
    photo = photo
)

fun Document.toWantThisScreenState() = WantThisScreenState(
    id = id,
    entries = entries,
    photo = photo
)

fun OrderDetailsState.toOrderTrackingState() = OrderTrackingState(
    status = status,
    number = number
)

fun Order.toOrderDetailsState(goods: List<OrderCardState>) =
    OrderDetailsState(
        id = id,
        number = number,
        goods = OrderItemsState(goods),
        status = status,
        count = itemsIds.size,
        totalPrice = price,
        date = date
    )

fun Order.toOrderCompactState() =
    OrderCompactState(
        id = id,
        number = number,
        status = status,
        count = itemsIds.size,
        totalPrice = price,
        date = date
    )

fun Order.toOrderTrackingState() = OrderTrackingState(
    status = status,
    number = number
)

fun Document.toWantThisCardState() = WantThisCardState(
    id = id,
    image = photo?.items?.firstOrNull()?.url ?: "",
    status = status,
    name = entries.firstOrNull { it.label == "Наименование" }?.text ?: ""
)

fun GoodsItem.toWantThisCardState() = WantThisCardState(
    id = id,
    image = image,
    price = price,
    oldPrice = oldPrice,
    installment = installment,
    counter = CounterState(
        id = id, count = count
    ),
    status = TypeStatusDoc.CONFIRMED,
    name = name
)

fun DRSaleForCartAndOrder.toReceiptItems() = ReceiptState.Item(
    name = nameGoods ?: "",
    quantity = quantity ?: 0,
    price = SimplePrice(amountEndPrice?.toInt() ?: 0)
)