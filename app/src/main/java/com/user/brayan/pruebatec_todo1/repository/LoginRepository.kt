package com.user.brayan.pruebatec_todo1.repository

import android.icu.text.IDNA
import androidx.lifecycle.LiveData
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.api.ApiResponse
import com.user.brayan.pruebatec_todo1.api.ApplicationApi
import com.user.brayan.pruebatec_todo1.model.Accounts
import com.user.brayan.pruebatec_todo1.model.InfoToken
import com.user.brayan.pruebatec_todo1.model.Login
import javax.inject.Singleton

@Singleton
class LoginRepository(
    private val appExecutors: AppExecutors,
    private val login: InfoToken,
    private val applicationApi: ApplicationApi
) {
    fun login(login: Login): LiveData<Resource<InfoToken>> {
        return object: NetworkBoundResource<InfoToken, List<InfoToken>>(appExecutors) {
            override fun loadFromDataBase(): LiveData<InfoToken> {
                TODO("Not yet implemented")
            }

            override fun createCall(): LiveData<ApiResponse<List<InfoToken>>> {
                TODO("Not yet implemented")
            }

            override fun saveCallResult(item: List<InfoToken>) {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: InfoToken?): Boolean {
                TODO("Not yet implemented")
            }

        }.asLiveData()
    }
}