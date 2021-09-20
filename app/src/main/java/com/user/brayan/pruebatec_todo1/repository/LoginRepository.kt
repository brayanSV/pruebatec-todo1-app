package com.user.brayan.pruebatec_todo1.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.api.ApiResponse
import com.user.brayan.pruebatec_todo1.api.ApplicationApi
import com.user.brayan.pruebatec_todo1.db.InfoTokenDao
import com.user.brayan.pruebatec_todo1.model.HistoryAccounts
import com.user.brayan.pruebatec_todo1.model.InfoToken
import com.user.brayan.pruebatec_todo1.model.LoginUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val loginDao: InfoTokenDao,
    private val applicationApi: ApplicationApi
) {
    fun loadLogin(loginUser: LoginUser): LiveData<Resource<List<InfoToken>>> {
        return object: NetworkBoundResource<List<InfoToken>, List<InfoToken>>(appExecutors) {
            override fun loadFromDataBase(): LiveData<List<InfoToken>> {
                return loginDao.load()
            }

            override fun createCall(): LiveData<ApiResponse<List<InfoToken>>> {
                return applicationApi.login(loginUser)
            }

            override fun saveCallResult(item: List<InfoToken>) {
                loginDao.insert(item)
            }

            override fun shouldFetch(data: List<InfoToken>?): Boolean {
                return data == null || data.isEmpty()
            }

        }.asLiveData()
    }
}