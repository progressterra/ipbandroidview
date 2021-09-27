package com.progressterra.ipbandroidview.ui.remove_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.progressterra.ipbandroidview.databinding.FragmentRemoveAccountLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged
import com.progressterra.ipbandroidview.utils.extensions.applyIfNotDefault
import com.progressterra.ipbandroidview.utils.extensions.hideKeyboard
import com.progressterra.ipbandroidview.utils.extensions.showKeyboard

class RemoveAccountFragment : BaseFragment() {

    private val args by navArgs<RemoveAccountFragmentArgs>()

    override val vm: RemoveAccountViewModel by viewModels {
        RemoveAccountViewModelFactory(args.confirmCodeSettings)
    }

    private lateinit var binding: FragmentRemoveAccountLibBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRemoveAccountLibBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editText.afterTextChanged(vm::checkIt)

        binding.editText.requestFocus()
        showKeyboard(requireContext(), binding.editText)

        binding.vm = vm
        binding.lifecycleOwner = this
        setupViewModel()
        setupCodeBlockParameters()

        applySettings()


        vm.popBackStack.observe(viewLifecycleOwner) { event ->
            event.contentIfNotHandled?.let {
                if (it) {
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun applySettings() {
        binding.apply {
            args.confirmCodeSettings.logoImageResId.applyIfNotDefault(confirmLogoIv)
            args.confirmCodeSettings.footerImageResId.applyIfNotDefault(confirmBannerIv)
        }
    }

    private fun setupViewModel() {
        vm.apply {
            action.observe(viewLifecycleOwner, this@RemoveAccountFragment::onAction)
            toastBundle.observe(viewLifecycleOwner, this@RemoveAccountFragment::showToast)
            clearConfirmCode.observe(viewLifecycleOwner) {
                cleanBlockCode()
            }
            setFragmentResult.observe(viewLifecycleOwner) {
                val bundle = it.contentIfNotHandled
                bundle?.let {
                    setFragmentResult(RemoveAccountKeys.REMOVE_BUNDLE, bundle)
                }
            }
        }
    }

    private fun cleanBlockCode() {
        binding.editText.text.clear()
    }

    private fun setupCodeBlockParameters() {
        binding.editText.afterTextChanged {
            setDigitItemParameters(it.getOrNull(0), binding.digit1)
            setDigitItemParameters(it.getOrNull(1), binding.digit2)
            setDigitItemParameters(it.getOrNull(2), binding.digit3)
            setDigitItemParameters(it.getOrNull(3), binding.digit4)
        }
    }

    private fun setDigitItemParameters(inputString: Char?, textView: TextView) {
        if (inputString == null) {
            textView.text = ""
            textView.isEnabled = false

        } else {
            textView.text = inputString.toString()
            textView.isEnabled = true
        }
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard(requireContext(), binding.editText)
    }
}