package com.progressterra.ipbandroidview.ui.profiledetails

import com.progressterra.ipbandroidview.actions.Back

interface ProfileDetailsInteractor : Back {

    fun confirmChange()

    fun onEmail(email: String)

    fun onName(name: String)

    fun logout()

    class Empty : ProfileDetailsInteractor {

        override fun confirmChange() = Unit

        override fun onEmail(email: String) = Unit

        override fun onName(name: String) = Unit

        override fun back() = Unit

        override fun logout() = Unit
    }
}