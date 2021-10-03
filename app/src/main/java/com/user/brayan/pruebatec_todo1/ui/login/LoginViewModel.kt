package com.user.brayan.pruebatec_todo1.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.user.brayan.pruebatec_todo1.model.Accounts
import com.user.brayan.pruebatec_todo1.model.InfoToken
import com.user.brayan.pruebatec_todo1.model.LoginUser
import com.user.brayan.pruebatec_todo1.repository.LoginRepository
import com.user.brayan.pruebatec_todo1.repository.Resource
import com.user.brayan.pruebatec_todo1.ui.history.HistoryViewModel
import com.user.brayan.pruebatec_todo1.utils.AbsentLiveData
import javax.inject.Inject

class LoginViewModel @Inject constructor(repository: LoginRepository): ViewModel() {
    private val authToken: MutableLiveData<String> = MutableLiveData("dXNlcllvdXR1YmU6cGFzd29yZFlvdXR1YmU=")
    private val _loginUser: MutableLiveData<LoginUser> = MutableLiveData()
    private val loginUser: LiveData<LoginUser> get() = _loginUser

    val result: LiveData<Resource<InfoToken>> = Transformations
        .switchMap(loginUser){ infoUser ->
            repository.loadLogin(infoUser)
        }

    fun fieldsIsEmpty(user: String, passw: String): Boolean {
        return user.isBlank() || passw.isBlank()
    }

    fun setUser(user: String, password: String) {
        val update = LoginUser(user, password)

        if (_loginUser.value == update) {
            return
        }

        _loginUser.value = update
    }

    fun retry() {
        val user = _loginUser.value?.user
        val password = _loginUser.value?.password

        if (user != null && password != null) {
            _loginUser.value = LoginUser(user, password)
        }
    }
}