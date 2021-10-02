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
import javax.inject.Inject

class LoginViewModel @Inject constructor(repository: LoginRepository): ViewModel() {
    val loginUser = MutableLiveData<LoginUser>()

    val result: LiveData<Resource<List<InfoToken>>> = Transformations
        .switchMap(loginUser){ infoUser ->
            //repository.loadLogin(infoUser)
        }

    fun fieldsIsEmpty(user: String, passw: String): Boolean {
        return user.isBlank() || passw.isBlank()
    }
}