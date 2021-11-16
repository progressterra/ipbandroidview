package com.progressterra.ipbandroidview.utils

sealed class SResult<out T : Any> : ISResult<T> {
    override val data: T? = null
    override var isNeedHandle: Boolean = false
    override var isHandled: Boolean = false

    data class Success<out T : Any>(override val data: T) : SResult<T>()

    object Completed : SResult<Nothing>()

    data class Toast(val message: Any) : SResult<Nothing>() {
        override var isNeedHandle: Boolean = true
    }

    data class Failed<out T : Any>(val message: Any? = null, override val data: T? = null) :
        SResult<T>() {
        override var isNeedHandle: Boolean = true
    }

    data class Loading<out T : Any>(override val data: T? = null) : SResult<T>()

    open class NavResult(val navDirections: Any) : SResult<Nothing>() {
        override var isNeedHandle: Boolean = true
    }

    object NavBackResult : NavResult(Any())

    override fun handle(onHandled: () -> Unit) {
        if (!isHandled) {
            isHandled = isNeedHandle
            onHandled.invoke()
        }
    }

    override fun isSuccess(): Boolean = !isLoading() && !isError()
    override fun isLoading(): Boolean = this is Loading
    override fun isError(): Boolean = this is Failed
    override fun isEmptySuccess(): Boolean =
        this.isSuccess() && (this.data as? List<*>)?.isEmpty() == true
}