package com.progressterra.ipbandroidview.core

interface Mapper<T, R> {

    fun map(data: T): R
}