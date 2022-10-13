package com.progressterra.ipbandroidview.di

class DistinctFactory<K, V>(private val newInstance: (K) -> V) {
    private val _locker = Any()
    private val mRepo: HashMap<K, V> = HashMap()

    operator fun get(id: K): V {
        return mRepo[id] ?: run {
            synchronized(_locker) {
                mRepo[id] ?: run {
                    newInstance(id).also {
                        mRepo[id] = it
                    }
                }
            }
        }
    }
}