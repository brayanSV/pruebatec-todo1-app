package com.user.brayan.pruebatec_todo1.ui.scan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ScanViewModel @Inject constructor(): ViewModel() {
    private val _bearerToken: MutableLiveData<String> = MutableLiveData()
    val bearerToken: LiveData<String> get() = _bearerToken

    fun setBearerToken(token: String?) {
        if (token.isNullOrEmpty()) {
            return
        }

        if (_bearerToken.value == token) {
            return
        }

        _bearerToken.value = token
    }
}