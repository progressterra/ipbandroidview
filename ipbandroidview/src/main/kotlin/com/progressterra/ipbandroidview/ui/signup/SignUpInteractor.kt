package com.progressterra.ipbandroidview.ui.signup

import com.progressterra.ipbandroidview.actions.Next
import com.progressterra.ipbandroidview.actions.Skip
import java.time.LocalDate

interface SignUpInteractor : Skip, Next {

    fun editBirthday(birthday: String, birthdayDate: LocalDate)

    fun editEmail(email: String)

    fun editName(name: String)

    fun openCalendar()

    fun closeCalendar()

    class Empty : SignUpInteractor {

        override fun skip() = Unit

        override fun next() = Unit

        override fun editBirthday(birthday: String, birthdayDate: LocalDate) = Unit

        override fun editEmail(email: String) = Unit

        override fun editName(name: String) = Unit

        override fun openCalendar() = Unit

        override fun closeCalendar() = Unit
    }
}