package com.progressterra.ipbandroidview.ui.set_personal_info

data class UserInfoModelUI(
    var name: String = "",
    var lastName: String = "",
    var middleName: String = "",
    var bankName: String = "",
    var account: String = "",
    var bic: String = "",
    var correspondentAccount: String = "",
    var inn: String = "",
    var cpp: String = "",
    var clientInn: String = ""
) {


    val nameIsValid
        get() = name.isNotBlank()

    val lastNameIsValid
        get() = lastName.isNotBlank()

    var middleNameIsValid = true

    val bankNameIsValid
        get() = bankName.isNotBlank()

    val accountIsValid
        get() = account.isNotBlank()

    val bicIsValid
        get() = bic.isNotBlank()

    val correspondentAccountIsValid
        get() = correspondentAccount.isNotBlank()

    val innIsValid
        get() = inn.isNotBlank()

    val cppIsValid
        get() = cpp.isNotBlank()

    val clientInnIsValid
        get() = clientInn.isNotBlank()

    val bankFieldsIsValid: Boolean =
        bankNameIsValid && accountIsValid
                && bicIsValid && correspondentAccountIsValid
                && innIsValid && cppIsValid && clientInnIsValid

    val baseInfoIsValid: Boolean = nameIsValid && lastNameIsValid
}
