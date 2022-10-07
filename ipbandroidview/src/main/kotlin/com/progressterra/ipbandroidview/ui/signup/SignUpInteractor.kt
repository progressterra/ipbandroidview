package com.progressterra.ipbandroidview.ui.signup

import java.time.LocalDate

interface SignUpInteractor {


    fun onSkip()

    fun onNext()

    fun onBirthday(birthday: String, birthdayDate: LocalDate)

    fun onEmail(email: String)

    fun onName(name: String)

    fun openCalendar()

    fun closeCalendar()

    class Empty : SignUpInteractor {


        override fun onSkip() = Unit

        override fun onNext() = Unit

        override fun onBirthday(birthday: String, birthdayDate: LocalDate) = Unit

        override fun onEmail(email: String) = Unit

        override fun onName(name: String) = Unit

        override fun openCalendar() = Unit

        override fun closeCalendar() = Unit
    }
}