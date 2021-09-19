package com.user.brayan.pruebatec_todo1.repository

import androidx.lifecycle.LiveData
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.api.ApiResponse
import com.user.brayan.pruebatec_todo1.api.ApplicationApi
import com.user.brayan.pruebatec_todo1.db.AccountsDao
import com.user.brayan.pruebatec_todo1.model.Accounts
import javax.inject.Singleton

@Singleton
class AccountsRepository(
    private val appExecutors: AppExecutors,
    private val accountsDao: AccountsDao,
    private val applicationApi: ApplicationApi
) {
    fun loadAccounts(token: String): LiveData<Resource<List<Accounts>>> {
        return object: NetworkBoundResource<List<Accounts>, List<Accounts>>(appExecutors) {
            override fun loadFromDb(): LiveData<List<Accounts>> {
                return accountsDao.load()
            }

            override fun createCall(): LiveData<ApiResponse<List<Accounts>>> {
                return applicationApi.accounts(token)
            }

            override fun saveCallResult(item: List<Accounts>) {
                accountsDao.insert(item)
            }

            override fun shouldFetch(data: List<Accounts>?): Boolean {
                return data == null || data.isEmpty()
            }
        }.asLiveData()
    }
}