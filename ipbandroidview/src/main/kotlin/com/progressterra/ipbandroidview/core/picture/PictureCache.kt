package com.progressterra.ipbandroidview.core.picture

import android.content.Intent
import android.graphics.Bitmap
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

interface PictureCache {

    interface Activity : PictureCache {

        val intentChannel: ReceiveChannel<Intent>
        val thumbnailChannel: SendChannel<Bitmap?>
    }

    interface Client : PictureCache {

        val intentChannel: SendChannel<Intent>
        val thumbnailChannel: ReceiveChannel<Bitmap?>
    }

    class Base : Activity, Client {

        override val intentChannel = Channel<Intent>()
        override val thumbnailChannel = Channel<Bitmap?>()
    }
}