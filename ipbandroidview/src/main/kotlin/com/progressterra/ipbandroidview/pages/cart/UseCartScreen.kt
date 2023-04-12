package com.progressterra.ipbandroidview.pages.cart

import com.progressterra.ipbandroidview.features.proshkatopbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.widgets.proshkacartitems.UseCartItems
import com.progressterra.ipbandroidview.widgets.proshkacartsummary.UseCartSummary

interface UseCartScreen : UseCartItems, UseStateBox,
    UseTopBar, UseCartSummary