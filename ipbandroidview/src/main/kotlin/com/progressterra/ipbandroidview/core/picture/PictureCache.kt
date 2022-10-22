package com.progressterra.ipbandroidview.core.picture

interface PictureCache {

    interface Activity : PictureCache {

        fun sendResult()
    }
}