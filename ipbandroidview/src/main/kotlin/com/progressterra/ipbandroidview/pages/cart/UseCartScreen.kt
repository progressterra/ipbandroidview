package com.progressterra.ipbandroidview.pages.cart

import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.widgets.cartitems.UseCartItems
import com.progressterra.ipbandroidview.widgets.cartsummary.UseCartSummary

interface UseCartScreen : UseCartItems, UseStateBox,
    UseTopBar, UseCartSummary