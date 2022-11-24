package com.progressterra.ipbandroidview.ui.signup

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.FetchUserBirthdayUseCase
import com.progressterra.ipbandroidview.domain.usecase.FetchUserEmailUseCase
import com.progressterra.ipbandroidview.domain.usecase.FetchUserNameUseCase
import com.progressterra.ipbandroidview.domain.usecase.FetchUserPhoneUseCase
import com.progressterra.ipbandroidview.domain.usecase.NeedAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.UpdatePersonalInfoUseCase
import com.progressterra.ipbandroidview.ext.isEmail
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate

class SignUpViewModel(
    private val updatePersonalInfoUseCase: UpdatePersonalInfoUseCase,
    private val fetchUserEmailUseCase: FetchUserEmailUseCase,
    private val fetchUserBirthdayUseCase: FetchUserBirthdayUseCase,
    private val fetchUserNameUseCase: FetchUserNameUseCase,
    private val fetchUserPhoneUseCase: FetchUserPhoneUseCase,
    private val needAddressUseCase: NeedAddressUseCase
) : ViewModel(), ContainerHost<SignUpState, SignUpEffect> {

    override val container: Container<SignUpState, SignUpEffect> =
        container(SignUpState())

    init {
        refresh()
    }

    fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        var wasError = false
        fetchUserNameUseCase.fetch().onSuccess { reduce { state.copy(name = it) } }
            .onFailure {
                wasError = true
                it.printStackTrace()
            }
        fetchUserEmailUseCase.fetch().onSuccess { reduce { state.copy(email = it) } }
            .onFailure {
                wasError = true
                it.printStackTrace()
            }
        fetchUserBirthdayUseCase.fetch().onSuccess { reduce { state.copy(birthday = it) } }
            .onFailure {
                wasError = true
                it.printStackTrace()
            }
        fetchUserPhoneUseCase.fetch().onSuccess { reduce { state.copy(phoneNumber = it) } }
            .onFailure {
                wasError = true
                it.printStackTrace()
            }
        if (wasError)
            reduce { state.copy(screenState = ScreenState.ERROR) }
        else
            reduce { state.copy(screenState = ScreenState.SUCCESS) }
    }

    fun skip() = intent { postSideEffect(SignUpEffect.Skip) }

    fun next() = intent {
        if (state.isDataValid) {
            reduce { state.copy(screenState = ScreenState.LOADING) }
            updatePersonalInfoUseCase.update(state.name, state.email, state.birthday).onSuccess {
                needAddressUseCase.needAddress().onSuccess {
                    if (it)
                        postSideEffect(SignUpEffect.NeedAddress)
                    else
                        postSideEffect(SignUpEffect.Skip)
                }.onFailure {
                    SignUpEffect.Toast(R.string.try_again)
                }
            }
            reduce { state.copy(screenState = ScreenState.SUCCESS) }
        } else postSideEffect(SignUpEffect.Toast(R.string.invalid_data))
    }

    fun editBirthday(birthday: LocalDate) {
        intent { reduce { state.copy(birthday = birthday) } }
        checkDataValidity()
    }

    fun editEmail(email: String) {
        intent { reduce { state.copy(email = email) } }
        checkDataValidity()
    }

    fun editName(name: String) {
        intent { reduce { state.copy(name = name) } }
        checkDataValidity()
    }

    fun openCalendar() = intent { reduce { state.copy(showCalendar = true) } }

    fun closeCalendar() = intent { reduce { state.copy(showCalendar = false) } }

    private fun checkDataValidity() = intent {
        reduce { state.copy(isDataValid = state.name.isNotBlank() && state.email.isEmail()) }
    }
}