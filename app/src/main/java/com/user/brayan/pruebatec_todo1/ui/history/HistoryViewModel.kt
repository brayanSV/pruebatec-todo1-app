package com.user.brayan.pruebatec_todo1.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.user.brayan.pruebatec_todo1.model.Accounts
import com.user.brayan.pruebatec_todo1.model.HistoryAccounts
import com.user.brayan.pruebatec_todo1.repository.HistoryAccountsRepository
import com.user.brayan.pruebatec_todo1.repository.Resource
import com.user.brayan.pruebatec_todo1.utils.AbsentLiveData
import javax.inject.Inject

class HistoryViewModel @Inject constructor(repository: HistoryAccountsRepository): ViewModel() {
    private val _accountType: MutableLiveData<AccountsType> = MutableLiveData()
    val accountType: LiveData<AccountsType> get() = _accountType

    val history: LiveData<Resource<List<HistoryAccounts>>> = Transformations.switchMap(_accountType) { input ->
        input.ifExists { accountId, type, bearerToken ->
            repository.loadHistoryAccounts(accountId, type, bearerToken)
        }
    }

    fun setId(accountId: Int, type: String, bearerToken: String) {
        val update = AccountsType(accountId, type, bearerToken)

        if (_accountType.value == update) {
            return
        }

        _accountType.value = update
    }

    data class AccountsType(val accountId: Int, val type: String, val bearerToken: String) {
        fun<T> ifExists(f: (Int, String, String) -> LiveData<T>): LiveData<T> {
            return if (accountId <= 0 || type.isBlank() || bearerToken.isBlank()) {
                AbsentLiveData.create()
            } else {
                f(accountId, type, bearerToken)
            }
        }
    }
}