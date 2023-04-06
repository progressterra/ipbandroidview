package com.progressterra.ipbandroidview.shared

interface DoubleMapper<T1, T2, R> {

    fun map(data1: T1, data2: T2): R
}