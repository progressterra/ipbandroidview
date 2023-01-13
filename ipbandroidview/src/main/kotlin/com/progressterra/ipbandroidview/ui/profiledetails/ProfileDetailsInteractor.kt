package com.progressterra.ipbandroidview.ui.profiledetails

interface ProfileDetailsInteractor {

    fun confirmChange()

    fun editEmail(email: String)

    fun editName(name: String)

    fun onBack()

    fun logout()

    class Empty : ProfileDetailsInteractor {

        override fun confirmChange() = Unit

        override fun editEmail(email: String) = Unit

        override fun editName(name: String) = Unit

        override fun onBack() = Unit

        override fun logout() = Unit
    }
}