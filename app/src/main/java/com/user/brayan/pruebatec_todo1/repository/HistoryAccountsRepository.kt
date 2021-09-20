package com.user.brayan.pruebatec_todo1.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.api.ApiResponse
import com.user.brayan.pruebatec_todo1.api.ApplicationApi
import com.user.brayan.pruebatec_todo1.db.HistoryAccountsDao
import com.user.brayan.pruebatec_todo1.model.HistoryAccounts
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryAccountsRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val historyAccountsDao: HistoryAccountsDao,
    private val applicationApi: ApplicationApi
) {
    fun loadHistoryAccounts(accountID: Int, accountType: String): LiveData<Resource<List<HistoryAccounts>>> {
        return object: NetworkBoundResource<List<HistoryAccounts>, List<HistoryAccounts>>(appExecutors) {
            override fun loadFromDataBase(): LiveData<List<HistoryAccounts>> {
                return historyAccountsDao.load(accountID)
            }

            override fun createCall(): LiveData<ApiResponse<List<HistoryAccounts>>> {
                return if (accountType.contains("Ahorro")) {
                    applicationApi.historySavingsAccount()
                } else {
                    applicationApi.historyCurrentAccount()
                }
            }

            override fun saveCallResult(item: List<HistoryAccounts>) {
                historyAccountsDao.insert(item)
            }

            override fun shouldFetch(data: List<HistoryAccounts>?): Boolean {
                return data == null || data.isEmpty()
            }

        }.asLiveData()
    }
}