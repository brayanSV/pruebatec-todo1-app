package com.user.brayan.pruebatec_todo1.ui.account

import android.accounts.Account
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.user.brayan.pruebatec_todo1.model.Accounts
import com.user.brayan.pruebatec_todo1.model.LoginUser
import com.user.brayan.pruebatec_todo1.repository.AccountsRepository
import com.user.brayan.pruebatec_todo1.repository.Resource
import com.user.brayan.pruebatec_todo1.utils.AbsentLiveData
import javax.inject.Inject

class AccountViewModel @Inject constructor(repository: AccountsRepository): ViewModel() {
    val bearerToken: MutableLiveData<String> = MutableLiveData()
    val repositories: LiveData<Resource<List<Accounts>>> = repository.loadAccounts()

    fun setBearerToken(token: String?) {
        if (bearerToken.value == token) {
            return
        }

        bearerToken.value = token
    }

    fun valBearerTokenIsNull(): Boolean {
        return  bearerToken.value.isNullOrBlank() || bearerToken.value.isNullOrEmpty()
    }
}