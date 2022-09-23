package com.progressterra.ipbandroidview.signup

interface SignUpInteractor {

    fun onBack()

    fun onSkip()

    fun onNext()

    fun onBirthday(birthday: String)

    fun onEmail(email: String)

    fun onName(name: String)

    class Empty : SignUpInteractor {

        override fun onBack() = Unit

        override fun onSkip() = Unit

        override fun onNext() = Unit

        override fun onBirthday(birthday: String) = Unit

        override fun onEmail(email: String) = Unit

        override fun onName(name: String) = Unit
    }
}