package com.progressterra.ipbandroidview.entities

interface Suggestion<T : Any> {

    val name: String
    val data: T
}