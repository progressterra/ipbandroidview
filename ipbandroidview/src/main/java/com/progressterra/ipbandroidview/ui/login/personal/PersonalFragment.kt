package com.progressterra.ipbandroidview.ui.login.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.progressterra.ipbandroidview.databinding.FragmentPersonalBinding
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener

internal class PersonalFragment : Fragment() {

    private var onLoginFlowFinishListener: OnLoginFlowFinishListener? = null

    private val viewModel: PersonalViewModel by viewModels {
        PersonalViewModelFactory(
            onLoginFlowFinishListener
        )
    }

    private lateinit var binding: FragmentPersonalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {
        fun newInstance(
            onLoginFlowFinishListener: OnLoginFlowFinishListener?
        ) = PersonalFragment().apply {
            this.onLoginFlowFinishListener = onLoginFlowFinishListener
        }
    }

}