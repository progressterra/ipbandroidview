package com.progressterra.ipbandroidview.widgets.edituser

import com.progressterra.ipbandroidview.features.makephoto.UseMakePhoto
import com.progressterra.ipbandroidview.features.suggestions.UseSuggestions
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseEditUser : UseTextField, UseMakePhoto, UseSuggestions<String>