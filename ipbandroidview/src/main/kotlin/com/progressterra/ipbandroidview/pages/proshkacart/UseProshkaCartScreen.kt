package com.progressterra.ipbandroidview.pages.proshkacart

import com.progressterra.ipbandroidview.features.proshkatopbar.UseProshkaTopBar
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.widgets.proshkacartitems.UseProshkaCartItems
import com.progressterra.ipbandroidview.widgets.proshkacartsummary.UseProshkaCartSummary

interface UseProshkaCartScreen : UseProshkaCartItems, UseStateBox,
    UseProshkaTopBar, UseProshkaCartSummary