package com.progressterra.ipbandroidview.model

interface Favorite<T : Favorite<T>> {

    val favorite: Boolean

    fun reverseFavorite(): T
}