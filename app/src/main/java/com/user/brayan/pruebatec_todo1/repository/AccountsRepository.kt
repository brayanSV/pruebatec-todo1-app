package com.user.brayan.pruebatec_todo1.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.api.ApiResponse
import com.user.brayan.pruebatec_todo1.api.ApplicationApi
import com.user.brayan.pruebatec_todo1.db.AccountsDao
import com.user.brayan.pruebatec_todo1.model.Accounts
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountsRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val accountsDao: AccountsDao,
    private val applicationApi: ApplicationApi
) {
    fun loadAccounts(bearerToken: String): LiveData<Resource<List<Accounts>>> = object: NetworkBoundResource<List<Accounts>, List<Accounts>>(appExecutors) {
        override fun loadFromDataBase(): LiveData<List<Accounts>> {
            return accountsDao.load()
        }

        override fun createCall(): LiveData<ApiResponse<List<Accounts>>> {
            return applicationApi.accounts(bearerToken)
        }

        override fun saveCallResult(item: List<Accounts>) {
            accountsDao.insert(item)
        }

        override fun shouldFetch(data: List<Accounts>?): Boolean {
            return data == null || data.isEmpty()
        }
    }.asLiveData()

}