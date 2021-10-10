package com.user.brayan.pruebatec_todo1.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.api.ApiResponse
import com.user.brayan.pruebatec_todo1.api.ApplicationApi
import com.user.brayan.pruebatec_todo1.db.HistoryAccountsDao
import com.user.brayan.pruebatec_todo1.model.HistoryAccounts
import com.user.brayan.pruebatec_todo1.model.QRCodes
import com.user.brayan.pruebatec_todo1.utils.AbsentLiveData
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryAccountsRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val historyAccountsDao: HistoryAccountsDao,
    private val applicationApi: ApplicationApi
) {
    fun loadHistoryAccounts(accountID: Int, accountType: String, bearerToken: String): LiveData<Resource<List<HistoryAccounts>>> {
        return object: NetworkBoundResource<List<HistoryAccounts>, List<HistoryAccounts>>(appExecutors) {
            override fun loadFromDataBase(): LiveData<List<HistoryAccounts>> {
                return historyAccountsDao.load(accountID)
            }

            override fun createCall(): LiveData<ApiResponse<List<HistoryAccounts>>> {
                return if (accountType.contains("Ahorro")) {
                    applicationApi.historySavingsAccount(bearerToken)
                } else {
                    applicationApi.historyCurrentAccount(bearerToken)
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

    fun payBill(transfer: HistoryAccounts, bearerToken: String): LiveData<Resource<String>> {
        return object: NetworkBoundResource<String, String>(appExecutors) {
            override fun loadFromDataBase(): LiveData<String> {
                return AbsentLiveData.create()
            }

            override fun shouldFetch(data: String?): Boolean {
                return true
            }

            override fun saveCallResult(item: String) {
                historyAccountsDao.insertHistory(transfer)
            }

            override fun createCall(): LiveData<ApiResponse<String>> {
                return applicationApi.newTransfer(bearerToken, transfer)
            }

        }.asLiveData()
    }
}