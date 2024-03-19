package com.progressterra.ipbandroidview.integration

import android.content.Context
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.gsonpref.gson
import com.google.firebase.FirebaseApp
import com.google.gson.Gson
import com.progressterra.ipbandroidapi.IpbAndroidApiSettings
import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.shared.theme.ColorUnit
import com.progressterra.ipbandroidview.shared.theme.IpbColors
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import timber.log.Timber

@Suppress("unused")
class IpbAndroidView private constructor(
    private val config: Map<String, List<String>>,
    private val context: Context,
    private val debug: Boolean,
    private val koinModules: List<Module>
) {

    init {
        if (!Kotpref.isInitialized) Kotpref.init(context)
        Kotpref.gson = Gson()
        IpbAndroidApiSettings.ACCESS_KEY = config["accessKey"]!!.first()
        IpbAndroidApiSettings.DEBUG = debug
        IpbAndroidViewSettings.ACCESS_TOKEN_FOR_UNAUTHORIZED_USER =
            config["accessTokenForUnauthorizedUser"]!!.first()
        IpbAndroidViewSettings.DEBUG = debug
        IpbAndroidViewSettings.BUTTON_ROUNDING = config["buttonRounding"]!!.first().toInt()
        IpbAndroidViewSettings.OFFER_URL = config["offerUrl"]!!.first()
        IpbAndroidViewSettings.PRIVACY_URL = config["privacyUrl"]!!.first()
        IpbAndroidViewSettings.MAIN_SCREEN_CATEGORIES = config["mainCategories"]!!
        IpbAndroidViewSettings.SHOW_PROFILE_DETAILS_BACK_BUTTON =
            config["showProfileDetailsBackButton"]!!.first().toBoolean()
        IpbAndroidViewSettings.COLORS = IpbColors(
            //Main
            primary = ColorUnit(config["primary"]!!),
            secondary = ColorUnit(config["secondary"]!!),
            secondary2 = ColorUnit(config["secondary2"]!!),
            tertiary = ColorUnit(config["tertiary"]!!),
            background = ColorUnit(config["background"]!!),
            onBackground = ColorUnit(config["onBackground"]!!),
            surface = ColorUnit(config["surface"]!!),
            surface2 = ColorUnit(config["surface2"]!!),
            onSurface = ColorUnit(config["onSurface"]!!),
            onSurface2 = ColorUnit(config["onSurface2"]!!),
            primaryPressed = ColorUnit(config["primaryPressed"]!!),
            primaryDisabled = ColorUnit(config["primaryDisabled"]!!),
            secondaryPressed = ColorUnit(config["secondaryPressed"]!!),
            //Status
            error = ColorUnit(config["error"]!!),
            success = ColorUnit(config["success"]!!),
            info = ColorUnit(config["info"]!!),
            warning = ColorUnit(config["warning"]!!),
            //Text
            textPrimary = ColorUnit(config["textPrimary"]!!),
            textPrimary2 = ColorUnit(config["textPrimary2"]!!),
            textSecondary = ColorUnit(config["textSecondary"]!!),
            textTertiary = ColorUnit(config["textTertiary"]!!),
            textTertiary2 = ColorUnit(config["textTertiary2"]!!),
            textTertiary3 = ColorUnit(config["textTertiary3"]!!),
            textTertiary4 = ColorUnit(config["textTertiary4"]!!),
            textButton = ColorUnit(config["textButton"]!!),
            textDisabled = ColorUnit(config["textDisabled"]!!),
            textPressed = ColorUnit(config["textPressed"]!!),
            //Icon
            iconPrimary = ColorUnit(config["iconPrimary"]!!),
            iconPrimary2 = ColorUnit(config["iconPrimary2"]!!),
            iconSecondary = ColorUnit(config["iconSecondary"]!!),
            iconSecondary2 = ColorUnit(config["iconSecondary2"]!!),
            iconTertiary = ColorUnit(config["iconTertiary"]!!),
            iconTertiary2 = ColorUnit(config["iconTertiary2"]!!),
            iconTertiary3 = ColorUnit(config["iconTertiary3"]!!),
            iconTertiary4 = ColorUnit(config["iconTertiary4"]!!),
            iconPressed = ColorUnit(config["iconPressed"]!!),
            iconDisabled = ColorUnit(config["iconDisabled"]!!)
        )
        IpbAndroidViewSettings.YOU_KASSA_SHOP_ID = config["youKassaShopId"]!!.first()
        IpbAndroidViewSettings.YOU_KASSA_CLIENT_APPLICATION_KEY =
            config["youKassaClientApplicationKey"]!!.first()
        IpbAndroidViewSettings.WORK_WATCH_ENABLED = config["workWatchEnabled"]!!.first().toBoolean()
        IpbAndroidViewSettings.AVAILABLE_PROFILE_FIELDS = config["availableProfileFields"]!!
        IpbAndroidViewSettings.MANDATORY_PROFILE_FIELDS = config["mandatoryProfileFields"]!!
        IpbAndroidViewSettings.CATALOG_SEARCH = config["catalogSearch"]!!.first().toBoolean()
        IpbAndroidViewSettings.CATALOG_COLUMNS = config["catalogColumns"]!!.first().toInt()
        IpbAndroidViewSettings.CATALOG_STORE_COLUMNS = config["catalogStoreColumns"]!!.first().toInt()
        IpbAndroidViewSettings.PROFILE_BUTTONS = config["profileButtons"]!!
        IpbAndroidViewSettings.PROFILE_BUTTONS_BORDER =
            config["profileButtonsBorder"]!!.first().toBoolean()
        IpbAndroidApiSettings.CHECKLIST_URL = config["checklistUrl"]!!
        IpbAndroidApiSettings.SUGGESTION_URL = config["suggestionUrl"]!!
        IpbAndroidApiSettings.AUTH_URL = config["authUrl"]!!
        IpbAndroidApiSettings.BALANCE_URL = config["balanceUrl"]!!
        IpbAndroidApiSettings.CART_URL = config["cartUrl"]!!
        IpbAndroidApiSettings.CATALOG_URL = config["catalogUrl"]!!
        IpbAndroidApiSettings.DOCS_URL = config["docsUrl"]!!
        IpbAndroidApiSettings.MESSENGER_URL = config["messengerUrl"]!!
        IpbAndroidApiSettings.PAYMENT_DATA_URL = config["paymentDataUrl"]!!
        IpbAndroidApiSettings.PAYMENT_URL = config["paymentUrl"]!!
        IpbAndroidApiSettings.PRODUCT_URL = config["productUrl"]!!
        IpbAndroidApiSettings.SCRM_URL = config["scrmUrl"]!!
        IpbAndroidApiSettings.MEDIA_DATA_URL = config["mediaDataUrl"]!!
        IpbAndroidApiSettings.IMH_URL = config["imhUrl"]!!
        IpbAndroidApiSettings.WORK_WATCH_URL = config["workWatchUrl"]!!
        IpbAndroidApiSettings.APPLICATION_URL = config["applicationUrl"]!!
        MapKitFactory.setApiKey(config["yandexMapApiKey"]!!.first())
        MapKitFactory.initialize(context)
        FirebaseApp.initializeApp(context)
        startKoin {
            if (debug) androidLogger()
            androidContext(context)
            modules(koinModules + ipbModule)
        }
        if (debug) {
            Timber.plant(Timber.DebugTree())
        }
    }

    class Builder {

        private var config: Map<String, List<String>>? = null
        private var context: Context? = null
        private var debug: Boolean = false
        private val koinModules = mutableListOf<Module>()

        fun setupConfig(config: Map<String, List<String>>): Builder {
            this.config = config
            return this
        }

        fun setupContext(context: Context): Builder {
            this.context = context
            return this
        }

        fun setupDebug(debug: Boolean): Builder {
            this.debug = debug
            return this
        }

        fun addModule(module: Module): Builder {
            koinModules.add(module)
            return this
        }

        fun build() {
            IpbAndroidView(
                config = config!!,
                context = context!!,
                debug = debug,
                koinModules = koinModules
            )
        }
    }
}