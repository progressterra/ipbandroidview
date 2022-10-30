package com.progressterra.ipbandroidview.ui.profiledetails

import com.progressterra.ipbandroidview.actions.Back

interface ProfileDetailsInteractor : Back {

    fun confirmChange()

    fun editEmail(email: String)

    fun editName(name: String)

    fun logout()

    class Empty : ProfileDetailsInteractor {

        override fun confirmChange() = Unit

        override fun editEmail(email: String) = Unit

        override fun editName(name: String) = Unit

        override fun back() = Unit

        override fun logout() = Unit
    }
}