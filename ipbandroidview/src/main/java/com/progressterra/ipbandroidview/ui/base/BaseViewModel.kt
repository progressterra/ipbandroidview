package com.progressterra.ipbandroidview.ui.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.ToastBundle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

@Deprecated("Dep", ReplaceWith("BaseBindingViewModel"))
open class BaseViewModel : ViewModel() {
    protected val _action = MutableLiveData<Event<NavDirections>>()
    val action: LiveData<Event<NavDirections>> = _action

    protected val _toastBundle = MutableLiveData<Event<ToastBundle>>()
    val toastBundle: LiveData<Event<ToastBundle>> = _toastBundle

    protected val _screenState = MutableLiveData(ScreenState.DEFAULT)
    val screenState: LiveData<ScreenState> = _screenState

    protected fun showToast(id: Int) {
        _toastBundle.postValue(Event(ToastBundle(id = id)))
    }

    protected fun showToast(vararg args: String) {
        _toastBundle.postValue(Event(ToastBundle(id = null, *args)))
    }

    protected fun showToast(id: Int, vararg args: String) {
        _toastBundle.postValue(Event(ToastBundle(id, *args)))
    }

    protected fun navigate(action: NavDirections) {
        _action.postValue(Event(action))
    }

    protected inline fun <T> tryWithState(
        state: MutableLiveData<ScreenState> = _screenState,
        tryBlock: () -> T
    ): T? {
        return try {
            state.postValue(ScreenState.LOADING)
            val data = tryBlock.invoke()
            state.postValue(ScreenState.DEFAULT)
            data
        } catch (e: CancellationException) {
            Log.e("tryWithState", "${e.message}")
            null
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, e.message, e)
            state.postValue(ScreenState.ERROR)
            null
        }
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
                onCatch?.invoke(e)
                Log.e(javaClass.simpleName, "$e")
            }
        }
    }

    protected fun safeLaunchWithState(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        state: MutableLiveData<ScreenState> = _screenState,
        launchBlock: suspend () -> Unit
    ) {
        viewModelScope.launch(dispatcher) {
            tryWithState(state) {
                launchBlock.invoke()
            }
        }
    }
}