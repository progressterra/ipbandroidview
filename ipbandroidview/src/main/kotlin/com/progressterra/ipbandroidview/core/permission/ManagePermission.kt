package com.progressterra.ipbandroidview.core.permission

interface ManagePermission {

    fun requirePermission(permission: String)

    fun checkPermission(permission: String): Boolean
}