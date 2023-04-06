package com.progressterra.ipbandroidview.shared

interface TripleMapper<T1, T2, T3, R> {

    fun map(data1: T1, data2: T2, data3: T3): R
}