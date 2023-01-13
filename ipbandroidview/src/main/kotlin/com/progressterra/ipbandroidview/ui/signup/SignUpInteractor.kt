package com.progressterra.ipbandroidview.ui.signup

import java.time.LocalDate

interface SignUpInteractor {

    fun onSkip()

    fun onNext()

    fun refresh()

    fun editBirthday(date: LocalDate)

    fun editEmail(email: String)

    fun editName(name: String)

    fun openCalendar()

    fun closeCalendar()

    class Empty : SignUpInteractor {

        override fun onSkip() = Unit

        override fun onNext() = Unit

        override fun refresh() = Unit

        override fun editBirthday(date: LocalDate) = Unit

        override fun editEmail(email: String) = Unit

        override fun editName(name: String) = Unit

        override fun openCalendar() = Unit

        override fun closeCalendar() = Unit
    }
}