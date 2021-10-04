package com.user.brayan.pruebatec_todo1.ui.viewcode

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject


class ViewCodeViewModel @Inject constructor(): ViewModel() {
    private val _QRCode: MutableLiveData<String> = MutableLiveData()
    val QRCode: LiveData<String> get() = _QRCode

    fun setPath(path: String) {
        if (_QRCode.value == path) {
            return
        }

        _QRCode.value = path
    }
}