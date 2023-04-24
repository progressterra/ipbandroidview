package com.progressterra.ipbandroidview.widgets.deliverypicker

interface DeliveryPickerValidUseCase {

    suspend operator fun invoke(state: DeliveryPickerState): Result<Unit>

    class Base : DeliveryPickerValidUseCase {

        override suspend fun invoke(state: DeliveryPickerState): Result<Unit> = runCatching {
            if (state.city.text.isEmpty() || state.home.text.isEmpty() || state.apartment.text.isEmpty() || state.entrance.text.isEmpty()) {
                throw Exception("Invalid")
            }
        }
    }
}