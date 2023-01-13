package com.progressterra.ipbandroidview.ui.signup

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserBirthdayUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserEmailUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserNameUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserPhoneUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.NeedAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.UpdatePersonalInfoUseCase
import com.progressterra.ipbandroidview.ext.isEmail
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate

@OptIn(OrbitExperimental::class)
class SignUpViewModel(
    private val updatePersonalInfoUseCase: UpdatePersonalInfoUseCase,
    private val fetchUserEmailUseCase: FetchUserEmailUseCase,
    private val fetchUserBirthdayUseCase: FetchUserBirthdayUseCase,
    private val fetchUserNameUseCase: FetchUserNameUseCase,
    private val fetchUserPhoneUseCase: FetchUserPhoneUseCase,
    private val needAddressUseCase: NeedAddressUseCase
) : ViewModel(), ContainerHost<SignUpState, SignUpEffect>, SignUpInteractor {

    override val container: Container<SignUpState, SignUpEffect> =
        container(SignUpState())

    init {
        refresh()
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        var wasError = false
        fetchUserNameUseCase().onSuccess { reduce { state.copy(name = it) } }
            .onFailure {
                wasError = true
                it.printStackTrace()
            }
        fetchUserEmailUseCase().onSuccess { reduce { state.copy(email = it) } }
            .onFailure {
                wasError = true
                it.printStackTrace()
            }
        fetchUserBirthdayUseCase().onSuccess { reduce { state.copy(birthday = it) } }
            .onFailure {
                wasError = true
                it.printStackTrace()
            }
        fetchUserPhoneUseCase().onSuccess { reduce { state.copy(phoneNumber = it) } }
            .onFailure {
                wasError = true
                it.printStackTrace()
            }
        if (wasError)
            reduce { state.copy(screenState = ScreenState.ERROR) }
        else
            reduce { state.copy(screenState = ScreenState.SUCCESS) }
    }

    override fun onSkip() = intent { postSideEffect(SignUpEffect.Skip) }

    override fun onNext() = intent {
        if (state.isDataValid) {
            reduce { state.copy(screenState = ScreenState.LOADING) }
            updatePersonalInfoUseCase(state.name, state.email, state.birthday).onSuccess {
                needAddressUseCase().onSuccess {
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

    override fun editBirthday(date: LocalDate) {
        intent { reduce { state.copy(birthday = date) } }
        checkDataValidity()
    }

    override fun editEmail(email: String) {
        blockingIntent { reduce { state.copy(email = email) } }
        checkDataValidity()
    }

    override fun editName(name: String) {
        blockingIntent { reduce { state.copy(name = name) } }
        checkDataValidity()
    }

    override fun openCalendar() = intent { reduce { state.copy(showCalendar = true) } }

    override fun closeCalendar() = intent { reduce { state.copy(showCalendar = false) } }

    private fun checkDataValidity() = intent {
        reduce { state.copy(isDataValid = state.name.isNotBlank() && state.email.isEmail()) }
    }
}