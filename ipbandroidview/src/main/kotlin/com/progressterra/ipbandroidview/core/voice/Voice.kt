package com.progressterra.ipbandroidview.core.voice

import com.progressterra.ipbandroidview.core.AttachedMedia

data class Voice(
    override val local: Boolean,
    override val toRemove: Boolean,
    val id: String
) : AttachedMedia<Voice> {

    override fun markToRemove(): Voice = this.copy(toRemove = true)
}
