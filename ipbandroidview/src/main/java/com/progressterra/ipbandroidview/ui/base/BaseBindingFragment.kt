package com.progressterra.ipbandroidview.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.progressterra.ipbandroidview.BR
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.toToastResult

open class BaseBindingFragment<Binding : ViewDataBinding, out ViewModel : BaseBindingViewModel>(@LayoutRes private val layoutRes: Int) :
    Fragment() {


    private var _binding: Binding? = null
    private val binding: Binding
        get() = _binding!!
    protected open val vm by viewModels<BaseBindingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create binding
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.setVariable(BR.vm, vm)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.supportLiveData?.observeAndHandleSResult {
            handleSupportLiveData(it)
        }
        vm.toastLiveData.observeAndHandleSResult {
            it.defaultResultHandling()
        }
        vm.navLiveData.observeAndHandleSResult {
            it.defaultResultHandling()
        }

        onInitBinding(binding, savedInstanceState)
    }

    protected fun <T : Any> SResult<T>.defaultResultHandling() {
        when (this) {
            is SResult.Toast -> handleToastResult()
            is SResult.Failed -> handleFailedResult()
            is SResult.NavResult -> handleNavResult()
            else -> Unit
        }
    }

    protected open fun onToast(toastMessage: String) {

    }

    protected open fun <T : Any> handleSupportLiveData(result: SResult<T>) {
        result.defaultResultHandling()
    }

    private fun <T : Any> SResult.Failed<T>.handleFailedResult() {
        when (message) {
            is String -> message as String
            is Int -> getString(message as Int)
            is Throwable -> (message as Throwable).message
            else -> null
        }?.toToastResult()?.handleToastResult()
    }

    private fun SResult.Toast.handleToastResult() {
        when (message) {
            is String -> message as String
            is Int -> getString(message as Int)
            else -> null
        }?.let {
            onToast(it)
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun SResult.NavResult.handleNavResult() {
        when (navDirections) {
            is NavDirections -> findNavController().navigate(navDirections as NavDirections)
            is Int -> findNavController().navigate(navDirections as Int)
        }
    }

    protected fun <T : SResult<T>> LiveData<T>.observeAndHandleSResult(observer: (T) -> Unit) {
        this.observe(viewLifecycleOwner) {
            it?.handle {
                observer.invoke(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected open fun onInitBinding(binding: Binding, savedInstanceState: Bundle?) {
    }
}