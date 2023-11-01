package com.progressterra.ipbandroidview.entities

import androidx.compose.runtime.Composable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
import com.progressterra.ipbandroidapi.api.iamhere.models.EnumTypeStatusConnect
import com.progressterra.ipbandroidapi.api.iamhere.models.RFInterestViewModel
import com.progressterra.ipbandroidapi.api.iamhere.models.RFTargetViewModel
import com.progressterra.ipbandroidapi.api.iamhere.models.RGClientDataViewModel
import com.progressterra.ipbandroidapi.api.iamhere.models.RGClientInterest
import com.progressterra.ipbandroidapi.api.iamhere.models.RGConnectViewModel
import com.progressterra.ipbandroidapi.api.iamhere.models.TypeSex
import com.progressterra.ipbandroidapi.api.messenger.models.RGDialogsViewModel
import com.progressterra.ipbandroidapi.api.messenger.models.RGMessagesViewModel
import com.progressterra.ipbandroidapi.api.payment.models.DHPaymentClientViewModel
import com.progressterra.ipbandroidapi.api.payment.models.TypeResultOperationBisinessArea
import com.progressterra.ipbandroidapi.api.paymentdata.models.RFPaymentDataForClientViewModel
import com.progressterra.ipbandroidapi.api.product.models.ProductView
import com.progressterra.ipbandroidapi.api.scrm.models.RGAddress
import com.progressterra.ipbandroidapi.api.suggestion.model.Suggestion
import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionExtendedInfo
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.bankcard.BankCardState
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.features.receipt.ReceiptState
import com.progressterra.ipbandroidview.features.withdrawaltransaction.WithdrawalTransactionState
import com.progressterra.ipbandroidview.shared.CreateId
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

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
        date = dateAdded?.parseToZDT()?.formatZdt("dd.MM.yyyy") ?: "")

fun ProductView.toGoodsItem() = GoodsItem(id = nomenclature?.idUnique!!,
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
    count = countInCart ?: 0)

@Composable
fun TypeStatusOrder.toString(stringResource: @Composable (Int) -> String) = when (this) {
    TypeStatusOrder.ORDER -> stringResource(R.string.order_confirmed)
    TypeStatusOrder.CONFIRM_FROM_STORE -> stringResource(R.string.order_confirm_from_store)
    TypeStatusOrder.CONFIRM_FROM_CALL_CENTER -> stringResource(R.string.order_confirm_from_call_center)
    TypeStatusOrder.SENT_TO_WAREHOUSE -> stringResource(R.string.order_sent_to_warehouse)
    TypeStatusOrder.SENT_DELIVERY_SERVICE -> stringResource(R.string.order_sent_delivery_service)
    TypeStatusOrder.ON_PICK_UP_POINT -> stringResource(R.string.order_on_pick_point)
    TypeStatusOrder.DELIVERED -> stringResource(R.string.order_delivered)
    TypeStatusOrder.CANCELED -> stringResource(R.string.order_canceled)
    TypeStatusOrder.ONE_CLICK -> stringResource(R.string.one_click)
    TypeStatusOrder.CART -> stringResource(R.string.cart)
}

fun SuggestionExtendedInfo.convertSuggestionToAddressUIModel(time: String) = AddressUI(
    defaultShipping = time,
    defaultBilling = time,
    fiasIDRegion = regionFiasId ?: "",
    fiasIDCity = cityFiasId ?: "",
    fiasIDArea = areaFiasId ?: "",
    fiasIDDistrict = cityDistrictFiasId ?: "",
    fiasIDStreet = streetFiasId ?: "",
    fiasIDHouse = houseFiasId ?: "",
    kladrHouse = houseKladrId ?: "",
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
    apartment = flat?.toIntOrNull() ?: 0,
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
    apartment = apartment ?: 0,
    floor = floor ?: 0,
    nameStreet = nameStreet ?: "",
    entrance = entrance?.toString() ?: "",
    defaultBilling = defaultBilling ?: "",
    defaultShipping = defaultShipping ?: "",
    fiasIDRegion = fiasIDRegion ?: "",
    fiasIDCity = fiasIDCity ?: "",
    fiasIDArea = fiasIDArea ?: "",
    fiasIDDistrict = fiasIDDistrict ?: "",
    fiasIDHouse = fiasIDHouse ?: "",
    fiasIDStreet = fiasIDStreet ?: "",
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
        name = characteristicType?.name ?: "",
        id = characteristicValue?.idUnique!!,
        entries = (characteristicValue?.valueAsJSON?.let {
            gson.fromJson<List<FieldData>?>(
                it, object : TypeToken<List<FieldData>>() {}.type
            )
        } ?: emptyList()).map {
            TextFieldState(
                id = createId(),
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
            ).unFormatByType(it.valueData ?: "")
        },
        photo = DocumentPhotoState(items = characteristicValue?.listImages?.map { img ->
            MultisizedImage(
                id = img.idUnique!!, local = false, toRemove = false, url = img.urlData!!
            )
        } ?: emptyList(), required = imageRequired ?: false),
        additionalValue = characteristicValue?.valueAsReference ?: "")

fun RFCharacteristicValueViewModel.toDocument(gson: Gson, createId: CreateId) =
    Document(status = statusDoc ?: TypeStatusDoc.NOT_FILL,
        name = characteristicType?.name ?: "",
        id = idUnique!!,
        entries = (valueAsJSON?.let {
            gson.fromJson<List<FieldData>?>(
                it, object : TypeToken<List<FieldData>>() {}.type
            )
        } ?: emptyList()).map {
            TextFieldState(
                id = createId(),
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
            ).unFormatByType(it.valueData ?: "")
        },
        photo = DocumentPhotoState(items = listImages?.map { img ->
            MultisizedImage(
                id = img.idUnique!!, local = false, toRemove = false, url = img.urlData!!
            )
        } ?: emptyList(), required = !listImages.isNullOrEmpty()),
        additionalValue = valueAsReference ?: "")

fun RFCharacteristicTypeViewModel.toDocument(gson: Gson, createId: CreateId) =
    Document(name = name ?: "", entries = (dataInJSON?.let {
        gson.fromJson<List<FieldData>?>(
            it, object : TypeToken<List<FieldData>>() {}.type
        )
    } ?: emptyList()).map {
        TextFieldState(
            id = createId(),
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
        ).unFormatByType(it.valueData ?: "")
    }, photo = DocumentPhotoState(required = false)
    )

fun DRSaleForCartAndOrder.toReceiptItems() = ReceiptState.Item(
    name = nameGoods ?: "",
    quantity = quantity ?: 0,
    price = SimplePrice(amountEndPrice?.toInt() ?: 0)
)

fun CatalogItem.toCatalogCardState(stringResource: (Int) -> String): CatalogCardState {
    val noData = stringResource(R.string.no_data)
    return CatalogCardState(id = itemCategory?.idUnique!!,
        name = itemCategory?.name ?: noData,
        image = itemCategory?.imageData?.urlData ?: "",
        children = listChildItems?.map { it.toCatalogCardState(stringResource) } ?: emptyList())
}

fun RGMessagesViewModel.toMessage() = Message(
    id = idUnique!!,
    user = isOwnMessage ?: false,
    content = contentText ?: "",
    date = dateAdded?.parseToZDT()?.formatZdt("dd.MM HH:mm") ?: ""
)

fun RGDialogsViewModel.toDatingChat() = DatingChat(
    id = idUnique!!, user = DatingUser(), previewMessage = "", lastTime = ""
)

fun String.parseToZDT(): ZonedDateTime? {
    var result: ZonedDateTime? = null
    runCatching {
        result = ZonedDateTime.parse(this, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }
    if (result == null) {
        runCatching {
            val semiResult = LocalDateTime.parse(this, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            result = semiResult.atZone(ZoneId.systemDefault())
        }
    }
    return result
}

fun ZonedDateTime.formatZdt(pattern: String): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return withZoneSameInstant(ZoneId.systemDefault()).format(formatter)
}

fun ZonedDateTime.formatZdtIso(): String {
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    return format(formatter)
}

@Composable
fun TypeStatusDoc.toString(stringResource: @Composable (Int) -> String) = when (this) {
    TypeStatusDoc.NOT_FILL -> stringResource(R.string.document_not_fill)
    TypeStatusDoc.WAIT_IMAGE -> stringResource(R.string.document_wait_image)
    TypeStatusDoc.WAIT_REVIEW -> stringResource(R.string.document_wait_review)
    TypeStatusDoc.REJECTED -> stringResource(R.string.document_rejected)
    TypeStatusDoc.CONFIRMED -> stringResource(R.string.document_confirmed)
}

@Composable
fun TypeStatusDoc.toColor() = when (this) {
    TypeStatusDoc.NOT_FILL -> IpbTheme.colors.textTertiary.asBrush()
    TypeStatusDoc.WAIT_IMAGE -> IpbTheme.colors.textTertiary.asBrush()
    TypeStatusDoc.WAIT_REVIEW -> IpbTheme.colors.textTertiary.asBrush()
    TypeStatusDoc.REJECTED -> IpbTheme.colors.textPrimary2.asBrush()
    TypeStatusDoc.CONFIRMED -> IpbTheme.colors.onBackground.asBrush()
}


fun TypeStatusDoc.toCanBeEditted() = when (this) {
    TypeStatusDoc.NOT_FILL -> true
    TypeStatusDoc.WAIT_IMAGE -> true
    TypeStatusDoc.WAIT_REVIEW -> false
    TypeStatusDoc.REJECTED -> true
    TypeStatusDoc.CONFIRMED -> false
}

fun RFPaymentDataForClientViewModel.toBankCardState() = BankCardState(
    id = idUnique!!, document = Document(
        name = "${paymentSystemName ?: ""} ${preiview ?: ""}", status = TypeStatusDoc.CONFIRMED
    ), isMainCard = false, isSelected = false
)

fun DHPaymentClientViewModel.toWithdrawalTransactionState() = WithdrawalTransactionState(
    id = idUnique!!,
    sum = amount?.toSimplePrice() ?: SimplePrice(),
    date = dateAdded?.parseToZDT()?.formatZdt("dd.MM.yyyy") ?: "",
    destination = previewPaymentMethod ?: "",
    status = status ?: TypeResultOperationBisinessArea.IN_PROGRESS
)

fun RGClientInterest.toInterest() = Interest(
    id = idrfInterest!!, name = ""
)

@Composable
fun TypeResultOperationBisinessArea.toColor() = when (this) {
    TypeResultOperationBisinessArea.IN_PROGRESS -> IpbTheme.colors.textTertiary.asBrush()
    TypeResultOperationBisinessArea.SUCCESS -> IpbTheme.colors.onBackground.asBrush()
    TypeResultOperationBisinessArea.WITH_ERROR -> IpbTheme.colors.textPrimary2.asBrush()
}

@Composable
fun TypeResultOperationBisinessArea.toString(stringResource: @Composable (Int) -> String) =
    when (this) {
        TypeResultOperationBisinessArea.IN_PROGRESS -> stringResource(R.string.transaction_in_progress)
        TypeResultOperationBisinessArea.SUCCESS -> stringResource(R.string.transaction_success)
        TypeResultOperationBisinessArea.WITH_ERROR -> stringResource(R.string.transaction_error)
    }

fun RFInterestViewModel.toInterest() = Interest(
    id = idUnique!!, name = name ?: "", picked = false
)

fun RGClientDataViewModel.toDatingUser(own: Boolean = false) = DatingUser(id = idUnique!!,
    name = this.nickName ?: "",
    description = descriptionAboutMe ?: "",
    avatar = avatarMediaData?.urlData ?: "",
    hideAvatar = false,
    locationPoint = LocationPoint(
        id = idrfPlace ?: "",
        latitude = latitudeReal ?: 0.0,
        longitude = longitudeReal ?: 0.0
    ),
    interests = listInterests?.map { it.toInterest() } ?: emptyList(),
    distance = 0,
    target = target?.toDatingTarget() ?: DatingTarget(),
    age = "",
    occupation = Interest(),
    connection = DatingConnection.CAN_CONNECT,
    sex = sex?.toSex() ?: Sex.MALE,
    own = own)

fun TypeSex.toSex() = when (this) {
    TypeSex.MALE -> Sex.MALE
    TypeSex.FEMALE -> Sex.FEMALE
}

fun com.progressterra.ipbandroidapi.api.scrm.models.TypeSex.toSex() = when (this) {
    com.progressterra.ipbandroidapi.api.scrm.models.TypeSex.MALE -> Sex.MALE
    com.progressterra.ipbandroidapi.api.scrm.models.TypeSex.FEMALE -> Sex.FEMALE
}


fun RFTargetViewModel.toDatingTarget() = DatingTarget(
    id = idUnique!!, name = name ?: ""
)

fun RGConnectViewModel.toConnection(target: Boolean): Connection {
    val user = if (target) targetClientData!! else initiatorClientData!!
    return Connection(
        id = idUnique!!,
        user = user.toDatingUser(),
        type = statusConnect ?: EnumTypeStatusConnect.WAIT
    )
}