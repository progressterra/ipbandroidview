package com.progressterra.ipbandroidview.ui.personal_edit

import androidx.fragment.app.viewModels
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentPersonalEditLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment

class PersonalEditFragment :
    BaseBindingFragment<FragmentPersonalEditLibBinding, PersonalEditViewModel>(
        R.layout.fragment_personal_edit_lib
    ) {
    override val vm by viewModels<PersonalEditViewModel>()
}