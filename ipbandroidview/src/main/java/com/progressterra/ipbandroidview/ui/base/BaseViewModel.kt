package com.progressterra.ipbandroidview.ui.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.ToastBundle
import java.util.concurrent.CancellationException

open class BaseViewModel : ViewModel() {
    protected val _action = MutableLiveData<Event<NavDirections>>()
    val action: LiveData<Event<NavDirections>> = _action

    protected val _toastBundle = MutableLiveData<Event<ToastBundle>>()
    val toastBundle: LiveData<Event<ToastBundle>> = _toastBundle

    protected val _screenState = MutableLiveData(ScreenState.DEFAULT)
    val screenState: LiveData<ScreenState> = _screenState

    internal fun showToast(id: Int) {
        _toastBundle.postValue(Event(ToastBundle(id = id)))
    }

    internal fun showToast(vararg args: String) {
        _toastBundle.postValue(Event(ToastBundle(id = null, *args)))
    }

    internal fun showToast(id: Int, vararg args: String) {
        _toastBundle.postValue(Event(ToastBundle(id, *args)))
    }

    internal fun navigate(action: NavDirections) {
        _action.postValue(Event(action))
    }

    internal inline fun <T> tryWithState(tryBlock: () -> T): T? {
        return try {
            _screenState.postValue(ScreenState.LOADING)
            val data = tryBlock.invoke()
            _screenState.postValue(ScreenState.DEFAULT)
            data
        } catch (e: CancellationException) {
            Log.e("tryWithState", "${e.message}")
            null
        } catch (e: Exception) {
            _screenState.postValue(ScreenState.ERROR)
            null
        }
    }
}