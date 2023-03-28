package com.progressterra.ipbandroidview.pages.proshkamain

sealed class ProshkaMainEvent {

    object OnBonuses : ProshkaMainEvent()

    class OnOffer(val id: String) : ProshkaMainEvent()

    class OnItem(val id: String) : ProshkaMainEvent()
}
