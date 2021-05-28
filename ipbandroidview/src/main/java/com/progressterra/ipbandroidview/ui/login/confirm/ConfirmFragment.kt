package com.progressterra.ipbandroidview.ui.login.confirm

import android.os.Bundle
import android.util.EventLog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.progressterra.ipbandroidview.databinding.FragmentConfirmBinding
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged
import com.progressterra.ipbandroidview.utils.extensions.argument

internal class ConfirmFragment : Fragment() {

    private var selectedCountry by argument<String>()
    private var phoneNumber by argument<String>()


    private val viewModel: ConfirmViewModel by viewModels {
        ConfirmViewModelFactory(selectedCountry, phoneNumber)
    }


    private lateinit var binding: FragmentConfirmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editText.afterTextChanged(viewModel::checkIt)

        viewModel.fragment.observe(viewLifecycleOwner, this::onFragment)
    }

    private fun onFragment(event: Event<Fragment>) {
        val fragment = event.contentIfNotHandled
        activity?.supportFragmentManager?.commit {
            if (fragment != null) {
                replace(((view as ViewGroup).parent as View).id, fragment)
            }
        }
    }


    companion object {
        fun newInstance(
            selectedCountry: String,
            phoneNumber: String
        ): ConfirmFragment =
            ConfirmFragment().apply {
                this.selectedCountry = selectedCountry
                this.phoneNumber = phoneNumber
            }
    }
}


