package com.progressterra.ipbandroidview.features.favoritebutton

sealed class FavoriteButtonEvent(val id: String) {

    class Click(id: String) : FavoriteButtonEvent(id)
}