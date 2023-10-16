package com.progressterra.ipbandroidview.integration

import android.content.Context

class IpbAndroidView {


    class Builder {

        private var config: Map<String, List<String>>? = null
        private var context: Context? = null

        fun setupConfig(config: Map<String, List<String>>) {
            this.config = config
        }

        fun setupContext(context: Context) {
            this.context = context
        }
    }
}