package com.progressterra.ipbandroidview.shared

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Id

@Immutable
interface AttachedMedia<out T> : Id where T : AttachedMedia<T>  {
    val local: Boolean
    val toRemove: Boolean

    fun markToRemove(): T
}