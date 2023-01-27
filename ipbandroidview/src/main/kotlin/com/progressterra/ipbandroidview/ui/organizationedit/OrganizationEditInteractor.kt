package com.progressterra.ipbandroidview.ui.organizationedit

interface OrganizationEditInteractor {

    fun onBack()

    fun editEmployeeName(name: String)

    fun editEmployeePhone(phone: String)

    fun editEmployeeEmail(email: String)

    fun saveEmployeeChanges()

    fun deleteEmployee()

    fun editEmployeeCanAudit(canAudit: Boolean)

    fun onEmployeeDetails()

    fun editOrganizationName(name: String)

    fun editOrganizationAddress(address: String)

    fun addOrganizationPhoto()

    fun saveOrganizationChanges()

    fun deleteOrganization()

    fun onOrganizationDetails()

    class Empty : OrganizationEditInteractor {

        override fun onBack() = Unit

        override fun editEmployeeName(name: String) = Unit

        override fun editEmployeePhone(phone: String) = Unit

        override fun editEmployeeEmail(email: String) = Unit

        override fun editEmployeeCanAudit(canAudit: Boolean) = Unit

        override fun saveEmployeeChanges() = Unit

        override fun deleteEmployee() = Unit

        override fun onEmployeeDetails() = Unit

        override fun editOrganizationName(name: String) = Unit

        override fun editOrganizationAddress(address: String) = Unit

        override fun addOrganizationPhoto() = Unit

        override fun saveOrganizationChanges() = Unit

        override fun deleteOrganization() = Unit

        override fun onOrganizationDetails() = Unit
    }
}