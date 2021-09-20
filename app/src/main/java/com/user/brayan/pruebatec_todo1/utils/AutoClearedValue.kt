package com.user.brayan.pruebatec_todo1.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import java.lang.IllegalStateException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AutoClearedValue<T: Any> (val fragment: Fragment): ReadWriteProperty<Fragment, T> {
    private var _values: T? = null

    init {
        fragment.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                _values = null
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T = _values?: throw IllegalStateException("No llamar cuando no esta disponible")

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        _values = value
    }
}

fun <T: Any> Fragment.autoCleared() = AutoClearedValue<T>(this)