package com.progressterra.ipbandroidview.ui.organizationedit

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class OrganizationEditViewModel : ViewModel(), OrganizationEditInteractor,
    ContainerHost<OrganizationEditState, OrganizationEditEffect> {

    override val container: Container<OrganizationEditState, OrganizationEditEffect> =
        container(OrganizationEditState())

    override fun onBack() = intent { postSideEffect(OrganizationEditEffect.Back) }

    override fun editEmployeeName(name: String) =
        blockingIntent { reduce { state.editEmployeeName(name) } }

    override fun editEmployeePhone(phone: String) =
        blockingIntent { reduce { state.editEmployeePhone(phone) } }

    override fun editEmployeeEmail(email: String) =
        blockingIntent { reduce { state.editEmployeeEmail(email) } }

    override fun editEmployeeCanAudit(canAudit: Boolean) =
        intent { reduce { state.editEmployeeCanAudit(canAudit) } }

    override fun saveEmployeeChanges() = Unit

    override fun deleteEmployee() = Unit

    override fun editOrganizationName(name: String) = blockingIntent {

    }

    override fun editOrganizationAddress(address: String) = blockingIntent {

    }

    override fun addOrganizationPhoto() = intent {

    }

    override fun saveOrganizationChanges() = Unit

    override fun deleteOrganization() = Unit

    override fun onEmployeeDetails() = intent {

    }

    override fun onOrganizationDetails() = intent {

    }
}