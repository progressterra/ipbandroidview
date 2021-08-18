package com.progressterra.core.sresult

interface ISResult<out T : Any> {

    val data: T?

    var isNeedHandle: Boolean

    var isHandled: Boolean

    fun isSuccess(): Boolean

    fun isLoading(): Boolean

    fun isError(): Boolean

    fun handle(onHandled: () -> Unit)
}