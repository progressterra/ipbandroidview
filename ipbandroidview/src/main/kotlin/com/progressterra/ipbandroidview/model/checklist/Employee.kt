package com.progressterra.ipbandroidview.model.checklist

data class Employee(
    val name: String,
    val phone: String,
    val email: String,
    val canAudit: Boolean
) {

    fun editName(name: String) = copy(name = name)

    fun editPhone(phone: String) = copy(phone = phone)

    fun editEmail(email: String) = copy(email = email)

    fun editCanAudit(canAudit: Boolean) = copy(canAudit = canAudit)

}
