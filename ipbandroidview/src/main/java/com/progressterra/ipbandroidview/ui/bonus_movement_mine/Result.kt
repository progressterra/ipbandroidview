package com.progressterra.ipbandroidview.ui.bonus_movement_mine

class Result<T>(var status: Status, val data: T?, val error: String?) {

    companion object {
        fun <T> loading(): Result<T> {
            return Result(Status.LOADING, null, null)
        }

        fun <T> success(data: T?): Result<T> {
            return Result(Status.SUCCESS, data, null)
        }

        fun <T> error(error: String?): Result<T> {
            return Result(Status.ERROR, null, error)
        }
    }

    fun resultIsSuccess() = status == Status.SUCCESS
    fun resultIsError() = status == Status.ERROR
    fun resultIsLoading() = status == Status.LOADING

}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}