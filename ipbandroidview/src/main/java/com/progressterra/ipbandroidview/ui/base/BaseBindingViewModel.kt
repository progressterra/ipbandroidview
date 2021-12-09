package com.progressterra.ipbandroidview.ui.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.navBackResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

open class BaseBindingViewModel : ViewModel(), ISResultContainer {
    override val resultLiveData: LiveData<*>? = null
    override val supportLiveData: MutableLiveData<SResult<*>>? = null
    override val toastLiveData = MutableLiveData<SResult.Toast>()
    override val navLiveData = MutableLiveData<SResult.NavResult>()

    private val namedJobs = mutableMapOf<String, Job>()

    /**
     *  Запускает безопасную корутину с именем, в случае если такая
     *  уже запущена - новая не будет запущена
     *  @param launchName - имя корутины
     *  @param dispatcher - поток, по дефолту IO
     */
    protected fun safeNamedLaunch(
        launchName: String,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        onCatch: ((e: Exception) -> Unit)? = null,
        launchBlock: suspend () -> Unit
    ) {
        val job = namedJobs[launchName]
        if (job?.isActive != true) {
            val newJob = viewModelScope.launch(dispatcher) {
                try {
                    launchBlock.invoke()
                } catch (e: Exception) {
                    Log.e(this@BaseBindingViewModel.javaClass.simpleName, "$e", e)
                    onCatch?.invoke(e)
                }
            }
            namedJobs[launchName] = newJob
        }
    }

    /**
     *  Запускает безопасную корутину с именем, в случае если такая
     *  уже запущена, отменит старую и начнет новую
     *  @param launchName - имя корутины
     *  @param dispatcher - поток, по дефолту IO
     */
    protected fun safeNamedOverrideLaunch(
        launchName: String,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        launchBlock: suspend () -> Unit
    ) {
        val job = namedJobs[launchName]
        if (job?.isActive == true) {
            job.cancel()
        }
        val newJob = viewModelScope.launch(dispatcher) {
            try {
                launchBlock.invoke()
            } catch (e: Exception) {
                Log.e(this@BaseBindingViewModel.javaClass.simpleName, "$e", e)
            }
        }
        namedJobs[launchName] = newJob
    }

    /**
     *  Безопасное выполнение корутины
     *  @param dispatcher - поток, по дефолту UI
     */
    protected fun safeLaunch(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        onCatch: ((e: Exception) -> Unit)? = null,
        launchBlock: suspend () -> Unit
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                launchBlock.invoke()
            } catch (e: Exception) {
                Log.e(this@BaseBindingViewModel.javaClass.simpleName, "$e", e)
                onCatch?.invoke(e)
            }
        }
    }

    /**
     *  Flow -> StateFlow
     */
    protected fun <T> Flow<T>.stateInLazy(initialValue: T): StateFlow<T> =
        stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            initialValue
        )


    /**
     *  FragmentLifecycle
     */
    open fun onStart() {

    }

    open fun onResume() {

    }

    open fun onPause() {

    }

    open fun onStop() {

    }

    fun navigateBack() {
        navLiveData.postValue(navBackResult())
    }
}