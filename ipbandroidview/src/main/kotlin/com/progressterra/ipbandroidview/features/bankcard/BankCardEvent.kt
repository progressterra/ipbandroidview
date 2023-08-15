package com.progressterra.ipbandroidview.features.bankcard

sealed class BankCardEvent(val state: BankCardState) {

    class Delete(state: BankCardState) : BankCardEvent(state)

    class Click(state: BankCardState) : BankCardEvent(state)
}