package com.progressterra.ipbandroidview

import com.progressterra.ipbandroidview.shared.theme.ProjectType

object IpbAndroidViewSettings {

    var TEST_MODE = false

    var DEBUG = false

    var MAIN_SCREEN_CATEGORIES: List<String> = emptyList()

    val PROJECT_TYPE: ProjectType = ProjectType.REDI

    var PRIVACY_URL: String = ""

    var OFFER_URL: String = ""

    var BUTTON_ROUNDING: Int = 14
}
