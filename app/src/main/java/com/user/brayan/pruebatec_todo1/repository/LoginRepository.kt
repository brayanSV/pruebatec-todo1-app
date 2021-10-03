package com.user.brayan.pruebatec_todo1.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.api.ApiResponse
import com.user.brayan.pruebatec_todo1.api.ApiSuccessResponse
import com.user.brayan.pruebatec_todo1.api.ApplicationApi
import com.user.brayan.pruebatec_todo1.db.InfoTokenDao
import com.user.brayan.pruebatec_todo1.model.HistoryAccounts
import com.user.brayan.pruebatec_todo1.model.InfoToken
import com.user.brayan.pruebatec_todo1.model.LoginUser
import com.user.brayan.pruebatec_todo1.utils.AbsentLiveData
import retrofit2.http.Header
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val applicationApi: ApplicationApi
) {
    var isNull: Boolean = true

    fun loadLogin(loginUser: LoginUser): LiveData<Resource<InfoToken>> {
        return object: NetworkBoundResource<InfoToken, InfoToken>(appExecutors) {
            val mutable_token_bearing: MutableLiveData<InfoToken> = MutableLiveData()
            val bearerToken: LiveData<InfoToken> get() = mutable_token_bearing

            override fun loadFromDataBase(): LiveData<InfoToken> {
                return if (isNull) {
                    AbsentLiveData.create()
                } else {
                    bearerToken
                }
            }

            override fun createCall(): LiveData<ApiResponse<InfoToken>> {
                return applicationApi.login(loginUser)
            }

            public override fun saveCallResult(item: InfoToken) {
                mutable_token_bearing.postValue(item)
                isNull = false
            }

            override fun shouldFetch(data: InfoToken?): Boolean {
                return true
            }
        }.asLiveData()
    }
}