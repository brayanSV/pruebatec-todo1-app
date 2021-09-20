package com.user.brayan.pruebatec_todo1.utils

import androidx.lifecycle.LiveData

class AbsentLiveData <T: Any?> private constructor(): LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}