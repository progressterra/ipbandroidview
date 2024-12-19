package com.progressterra.ipbandroidview.shared

import androidx.compose.ui.graphics.Color
import com.progressterra.ipbandroidview.shared.theme.IpbColors

/** Storage for configuration settings from config.properties file */
internal object IpbAndroidViewSettings {

    var ACCESS_TOKEN_FOR_UNAUTHORIZED_USER: String = ""

    var AVAILABLE_PROFILE_FIELDS: List<String> = emptyList()

    var MANDATORY_PROFILE_FIELDS: List<String> = emptyList()

    var COLORS: IpbColors = IpbColors()

    var DEBUG = false

    var MAIN_SCREEN_CATEGORIES: List<String> = emptyList()

    var PRIVACY_URL: String = ""

    var OFFER_URL: String = ""

    var BUTTON_ROUNDING: Int = 14

    var WORK_WATCH_ENABLED = true

    var SHOW_PROFILE_DETAILS_BACK_BUTTON = true

    var YOU_KASSA_SHOP_ID = ""

    var YOU_KASSA_CLIENT_APPLICATION_KEY = ""

    var CATALOG_SEARCH = true

    var CATALOG_COLUMNS = 2

    var CATALOG_STORE_COLUMNS = 2

    var PROFILE_BUTTONS: List<String> = emptyList()

    var PROFILE_BUTTONS_BORDER: Boolean = false

    var WANT_THIS_DOC_TYPE_ID = "08db716a-e5d2-422b-8b07-20068718307b"

    var DEFAULT_ID = "00000000-0000-0000-0000-000000000000"

    var DOCS_CHAT_ID = "adb4cf1a-6918-4f7b-8d5c-1c566e5bbdc8"

    var WANT_THIS_CHAT_ID = "776c597d-d326-42cd-9393-206c19f8ca0d"

    var ORDERS_CHAT_ID = "56600c57-b9f7-4d11-a0f9-8c0103c4ed7f"

    var MAIN_CHAT_ID = "7f7e8460-5608-48d2-b2b4-1e5769e8d9e7"

    var BANK_CARDS_TYPE_ID = "08db9c4b-e7c1-4b65-836b-7512ea92b180"

    var IDRFSPECIFICATION_FOR_BUY = "08dca16f-9e0c-41da-8d6e-615cf940160c"//Идентификатор специкации именно товара, который можно купить.

    var IDRFSPECIFICATION_FOR_TEACH = ""//Идентификатор специкации товара как учебного материала

    var NAME_TYPECHARECTERISTIC_MANUFACTURE = "Производитель"//Идентификатор типа характеристики производителеля_


    var GRADIENT_COLOR_LEFT = Color(0xFF53B8EB)
    val GRADIENT_COLOR_RIGHT = Color(0xFF27D1AE)
}
