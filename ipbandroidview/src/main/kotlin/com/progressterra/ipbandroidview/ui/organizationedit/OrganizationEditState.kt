package com.progressterra.ipbandroidview.ui.organizationedit

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.Employee
import com.progressterra.ipbandroidview.entities.Organization

@Immutable
data class OrganizationEditState(
    val audits: List<Document> = emptyList(),
    val employees: List<Employee> = emptyList(),
    val currentEmployee: Employee? = null,
    val organization: Organization = Organization(),
) {

    fun editEmployeeName(name: String): OrganizationEditState = copy(
        currentEmployee = currentEmployee!!.editName(name)
    )

    fun editEmployeePhone(phone: String): OrganizationEditState = copy(
        currentEmployee = currentEmployee!!.editPhone(phone)
    )

    fun editEmployeeEmail(email: String): OrganizationEditState = copy(
        currentEmployee = currentEmployee!!.editEmail(email)
    )

    fun editEmployeeCanAudit(canAudit: Boolean): OrganizationEditState = copy(
        currentEmployee = currentEmployee!!.editCanAudit(canAudit)
    )
}
