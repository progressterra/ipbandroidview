package com.progressterra.ipbandroidview.pages.signin

import com.progressterra.ipbandroidview.features.authorskip.UseAuthOrSkip
import com.progressterra.ipbandroidview.features.proshkatopbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.linktext.UseLinkText
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseSignIn : UseAuthOrSkip, UseTextField, UseTopBar, UseLinkText