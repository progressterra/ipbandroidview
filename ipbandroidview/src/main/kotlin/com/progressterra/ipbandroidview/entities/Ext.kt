package com.progressterra.ipbandroidview.entities

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.progressterra.ipbandroidapi.api.address.models.RGAddress
import com.progressterra.ipbandroidapi.api.cart.models.DHSaleHeadAsOrderViewModel
import com.progressterra.ipbandroidapi.api.cart.models.DRSaleForCartAndOrder
import com.progressterra.ipbandroidapi.api.cart.models.TypeStatusOrder
import com.progressterra.ipbandroidapi.api.catalog.models.CatalogItem
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
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.features.receipt.ReceiptState
import com.progressterra.ipbandroidview.shared.CreateId
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.print
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType

fun List<SimplePrice>.sum(): SimplePrice {
    var sum = SimplePrice()
    forEach { sum += it }
    return sum
}

fun Double.toSimplePrice() = SimplePrice(toInt())

fun Boolean.toScreenState() = if (this) ScreenState.SUCCESS else ScreenState.ERROR

fun DHSaleHeadAsOrderViewModel.toOrder() =
    Order(itemsIds = listDRSale?.map { it.idrfNomenclature!! } ?: emptyList(),
        price = (listDRSale?.map { it.amountEndPrice!!.toSimplePrice() } ?: emptyList()).sum(),
        id = idUnique!!,
        number = number ?: "",
        status = statusOrder ?: TypeStatusOrder.CANCELED,
        date = dateAdded?.parseToDate()?.print() ?: "")

fun ProductView.toGoodsItem() = GoodsItem(
    id = nomenclature?.idUnique!!,
    categoryId = nomenclature?.listCatalogCategory?.firstOrNull() ?: "",
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
        photo = DocumentPhotoState(items = characteristicValue?.listImages?.map { img ->
            MultisizedImage(
                id = img.idUnique!!, local = false, toRemove = false, url = img.urlData!!
            )
        } ?: emptyList(),
            docName = characteristicType?.name ?: "",
            required = imageRequired ?: false),
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
        } ?: emptyList(),
            docName = characteristicType?.name ?: "",
            required = !listImages.isNullOrEmpty()),
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
        photo = DocumentPhotoState(required = true)
    )

fun DRSaleForCartAndOrder.toReceiptItems() = ReceiptState.Item(
    name = nameGoods ?: "",
    quantity = quantity ?: 0,
    price = SimplePrice(amountEndPrice?.toInt() ?: 0)
)

fun CatalogItem.toCatalogCardState(manageResources: ManageResources): CatalogCardState {
    val noData = manageResources.string(R.string.no_data)
    return CatalogCardState(
        id = itemCategory?.idUnique!!,
        name = itemCategory?.name ?: noData,
        image = itemCategory?.imageData?.urlData ?: "",
        children = listChildItems?.map { it.toCatalogCardState(manageResources) } ?: emptyList()
    )
}