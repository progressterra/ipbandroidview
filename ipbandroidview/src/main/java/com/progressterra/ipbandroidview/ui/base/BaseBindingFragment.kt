package com.progressterra.ipbandroidview.ui.base

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.progressterra.ipbandroidview.BR
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.toToastResult

/**
 * Базовый фрагмент с dataBinding. Сам создает dataBinding, привязывает vm к фрагменту и передает
 * lifecycleOwner в фрагмент
 */
open class BaseBindingFragment<Binding : ViewDataBinding, out ViewModel : BaseBindingViewModel>(@LayoutRes private val layoutRes: Int) :
    Fragment() {

    private var _binding: Binding? = null
    protected val binding: Binding
        get() = _binding!!
    protected open val vm by viewModels<BaseBindingViewModel>()

    /**
     *  "раздувание" разметки и установка вм в разметку
     */
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

    /**
     *  Подписывается на базовые лайвдаты в вм и вызывает onInitBinding.
     *  Настройку фрагмента следует производить в onInitBinding
     */
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

    /**
     *  Дефолтная обработка SResult
     *  Обрабатываемые типы: Toast, Failed, NavResult
     */
    protected fun <T : Any> SResult<T>.defaultResultHandling() {
        when (this) {
            is SResult.Toast -> handleToastResult()
            is SResult.Failed -> handleFailedResult()
            is SResult.NavResult -> handleNavResult()
            else -> Unit
        }
    }

    /**
     *  После вывода сообщения на экран, вызовется данный метод
     */
    protected open fun onToast(toastMessage: String) {

    }

    /**
     * Обработка лайвдаты с SResult
     */
    protected open fun <T : Any> handleSupportLiveData(result: SResult<T>) {
        result.defaultResultHandling()
    }

    /**
     *  Выводит тост в случае если в сообщение в Failed не пустое
     */
    private fun <T : Any> SResult.Failed<T>.handleFailedResult() {
        when (message) {
            is String -> message
            is Int -> getString(message)
            is Throwable -> message.message
            else -> null
        }?.toToastResult()?.handleToastResult()
    }

    /**
     *  Определяет тип данных в Toast и выводит тост на экран с вызовом onToast()
     */
    private fun SResult.Toast.handleToastResult() {
        when (message) {
            is String -> message
            is Int -> getString(message)
            else -> null
        }?.let {
            onToast(it)
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Навигация возможно как через action, так и через id в графе
     */
    private fun SResult.NavResult.handleNavResult() {
        when (navDirections) {
            is NavDirections -> findNavController().navigate(navDirections)
            is Int -> findNavController().navigate(navDirections)
        }
    }

    /**
     *  Подписывается на изменения SResult и "открывает" даненые (аналог Event)
     */
    protected fun <T : SResult<T>> LiveData<T>.observeAndHandleSResult(observer: (T) -> Unit) {
        this.observe(viewLifecycleOwner) {
            it?.handle {
                observer.invoke(it)
            }
        }
    }

    /**
     *  Обнуляет _binding что бы не было утечек
     */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /**
     *  Вызовется в onViewCreated()
     */
    protected open fun onInitBinding(binding: Binding, savedInstanceState: Bundle?) {
    }

    /**
     *  @param permission - разрешение для которого необходимо проверить доступность
     */
    protected fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    open fun setupHeader(
        title: Int? = null,
        backVisible: Boolean = true
    ) {
        try {
            val back = binding.root.findViewById<ImageView>(R.id.iv_back_header)

            if (backVisible) {
                back.visibility = View.VISIBLE
                back.setOnClickListener {
                    findNavController().popBackStack()
                }
            } else {
                back.visibility = View.GONE
            }

            title?.let {
                binding.root.findViewById<TextView>(R.id.tv_title_header).text =
                    resources.getString(title)
            }
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "$e")
        }
    }

    open fun setupHeader(
        title: String? = null,
        backVisible: Boolean = true
    ) {
        try {
            val back = binding.root.findViewById<ImageView>(R.id.iv_back_header)

            if (backVisible) {
                back.visibility = View.VISIBLE
                back.setOnClickListener {
                    findNavController().popBackStack()
                }
            } else {
                back.visibility = View.GONE
            }

            title?.let {
                binding.root.findViewById<TextView>(R.id.tv_title_header).text = title
            }
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "$e")
        }
    }
}