package com.progressterra.ipbandroidview.entities

import com.progressterra.ipbandroidview.shared.AttachedMedia

data class Voice(
    override val id: String,
    override val local: Boolean,
    override val toRemove: Boolean
) : AttachedMedia<Voice> {

    override fun markToRemove(): Voice = this.copy(toRemove = true)
}
