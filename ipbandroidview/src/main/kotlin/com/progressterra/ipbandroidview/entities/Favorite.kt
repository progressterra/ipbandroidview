package com.progressterra.ipbandroidview.entities

interface Favorite<T : Favorite<T>> {

    val favorite: Boolean

    fun reverseFavorite(): T
}