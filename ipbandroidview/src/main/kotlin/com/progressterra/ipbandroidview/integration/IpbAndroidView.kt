package com.progressterra.ipbandroidview.integration

import android.content.Context
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.gsonpref.gson
import com.google.firebase.FirebaseApp
import com.google.gson.Gson
import com.progressterra.ipbandroidapi.IpbAndroidApiSettings
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.shared.theme.ColorUnit
import com.progressterra.ipbandroidview.shared.theme.IpbColors
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

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
        IpbAndroidViewSettings.DEBUG = debug
        IpbAndroidViewSettings.BUTTON_ROUNDING = config["buttonRounding"]!!.first().toInt()
        IpbAndroidViewSettings.OFFER_URL = config["offerUrl"]!!.first()
        IpbAndroidViewSettings.PRIVACY_URL = config["privacyUrl"]!!.first()
        IpbAndroidViewSettings.MAIN_SCREEN_CATEGORIES = config["mainCategories"]!!
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
        IpbAndroidViewSettings.WORK_WATCH_ENABLED = config["workWatchEnabled"]!!.first().toBoolean()
        IpbAndroidViewSettings.WORK_WATCH_START_HOUR = config["workWatchStartHour"]!!.first().toInt()
        IpbAndroidViewSettings.WORK_WATCH_END_HOUR = config["workWatchEndHour"]!!.first().toInt()
        IpbAndroidViewSettings.WORK_WATCH_PERIOD = config["workWatchPeriod"]!!.first().toInt()
        IpbAndroidViewSettings.AVAILABLE_PROFILE_FIELDS = config["availableProfileFields"]!!
        IpbAndroidViewSettings.MANDATORY_PROFILE_FIELDS = config["mandatoryProfileFields"]!!
        IpbAndroidApiSettings.AUTH_URL = config["authUrl"]!!.first()
        IpbAndroidApiSettings.BALANCE_URL = config["balanceUrl"]!!.first()
        IpbAndroidApiSettings.CART_URL = config["cartUrl"]!!.first()
        IpbAndroidApiSettings.CATALOG_URL = config["catalogUrl"]!!.first()
        IpbAndroidApiSettings.DOCS_URL = config["docsUrl"]!!.first()
        IpbAndroidApiSettings.MESSENGER_URL = config["messengerUrl"]!!.first()
        IpbAndroidApiSettings.PAYMENT_DATA_URL = config["paymentDataUrl"]!!.first()
        IpbAndroidApiSettings.PAYMENT_URL = config["paymentUrl"]!!.first()
        IpbAndroidApiSettings.PRODUCT_URL = config["productUrl"]!!.first()
        IpbAndroidApiSettings.SCRM_URL = config["scrmUrl"]!!.first()
        IpbAndroidApiSettings.MEDIA_DATA_URL = config["mediaDataUrl"]!!.first()
        IpbAndroidApiSettings.IMH_URL = config["imhUrl"]!!.first()
        IpbAndroidApiSettings.WORK_WATCH_URL = config["workWatchUrl"]!!.first()
        MapKitFactory.setApiKey(config["yandexMapApiKey"]!!.first())
        MapKitFactory.initialize(context)
        FirebaseApp.initializeApp(context)
        startKoin {
            if (debug) androidLogger()
            androidContext(context)
            modules(koinModules + ipbModule)
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