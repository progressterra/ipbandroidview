package com.progressterra.ipbandroidview.core

interface ManagePermission {

    fun requirePermission(permission: String)

    fun checkPermission(permission: String): Boolean
}