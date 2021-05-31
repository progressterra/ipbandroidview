package com.progressterra.ipbandroidview.utils.extensions

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.progressterra.ipbandroidview.models.ui.PersonalInfo
import com.progressterra.ipbandroidview.utils.delegate.FragmentArgumentDelegate
import com.progressterra.ipbandroidview.utils.delegate.FragmentNullableArgumentDelegate
import kotlin.properties.ReadWriteProperty

fun <T> Bundle.put(key: String, value: T) {
    when (value) {
        is Boolean -> putBoolean(key, value)
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Short -> putShort(key, value)
        is Long -> putLong(key, value)
        is Byte -> putByte(key, value)
        is ByteArray -> putByteArray(key, value)
        is Char -> putChar(key, value)
        is CharArray -> putCharArray(key, value)
        is CharSequence -> putCharSequence(key, value)
        is Float -> putFloat(key, value)
        is Bundle -> putBundle(key, value)
        is Parcelable -> putParcelable(key, value)
        else -> throw IllegalStateException("Type of property $key is not supported")
    }
}

fun <T : Any> argument(): ReadWriteProperty<Fragment, T> =
    FragmentArgumentDelegate()

fun <T : Any> argumentNullable(): ReadWriteProperty<Fragment, T?> =
    FragmentNullableArgumentDelegate()

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}