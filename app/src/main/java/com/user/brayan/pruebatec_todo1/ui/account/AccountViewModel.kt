package com.user.brayan.pruebatec_todo1.ui.account

import android.accounts.Account
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.user.brayan.pruebatec_todo1.model.Accounts
import com.user.brayan.pruebatec_todo1.model.InfoToken
import com.user.brayan.pruebatec_todo1.model.LoginUser
import com.user.brayan.pruebatec_todo1.repository.AccountsRepository
import com.user.brayan.pruebatec_todo1.repository.Resource
import com.user.brayan.pruebatec_todo1.utils.AbsentLiveData
import javax.inject.Inject

class AccountViewModel @Inject constructor(repository: AccountsRepository): ViewModel() {
    val bearerToken: MutableLiveData<String> = MutableLiveData()

    val repositories: LiveData<Resource<List<Accounts>>> = Transformations
        .switchMap(bearerToken){ account ->
            repository.loadAccounts(account)
        }

    fun setBearerToken(token: String?) {
        if (token.isNullOrEmpty()) {
            return
        }

        if (bearerToken.value == token) {
            return
        }

        bearerToken.value = token
    }
}