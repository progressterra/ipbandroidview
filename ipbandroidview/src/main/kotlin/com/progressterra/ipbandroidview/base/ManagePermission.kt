package com.progressterra.ipbandroidview.base

interface ManagePermission {

    fun requirePermission(permission: String)

    fun checkPermission(permission: String): Boolean
}