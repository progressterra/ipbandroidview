package com.progressterra.ipbandroidview.integration

import android.content.Context
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.gsonpref.gson
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.google.firebase.FirebaseApp
import com.google.gson.Gson
import com.progressterra.ipbandroidapi.IpbAndroidApiSettings
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.shared.reflection.extractFromMap
import com.progressterra.ipbandroidview.shared.theme.IpbColors
import okhttp3.OkHttpClient
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
        IpbAndroidViewSettings.COLORS = config.extractFromMap(IpbColors())
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
        IpbAndroidApiSettings.IMH_URL = config["imhService"]!!.first()
        FirebaseApp.initializeApp(context)
        Fresco.initialize(
            context,
            OkHttpImagePipelineConfigFactory.newBuilder(context, OkHttpClient.Builder().build())
                .setDiskCacheEnabled(true).setDownsampleEnabled(true)
                .setResizeAndRotateEnabledForNetwork(true).build()
        )
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

        fun setupConfig(config: Map<String, List<String>>) : Builder {
            this.config = config
            return this
        }

        fun setupContext(context: Context) : Builder {
            this.context = context
            return this
        }

        fun setupDebug(debug: Boolean) : Builder {
            this.debug = debug
            return this
        }

        fun addModule(module: Module) : Builder {
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