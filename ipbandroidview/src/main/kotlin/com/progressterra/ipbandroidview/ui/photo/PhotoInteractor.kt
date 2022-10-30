package com.progressterra.ipbandroidview.ui.photo

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.actions.Remove

interface PhotoInteractor : Back, Remove {

    class Empty : PhotoInteractor {

        override fun back() = Unit

        override fun remove() = Unit
    }
}