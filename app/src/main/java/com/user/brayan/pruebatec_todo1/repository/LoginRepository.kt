package com.user.brayan.pruebatec_todo1.repository

import androidx.lifecycle.LiveData
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.api.ApplicationApi
import com.user.brayan.pruebatec_todo1.model.InfoToken
import com.user.brayan.pruebatec_todo1.model.Login
import javax.inject.Singleton

@Singleton
class LoginRepository(
    private val appExecutors: AppExecutors,
    private val login: InfoToken,
    private val applicationApi: ApplicationApi
) {
    /*fun login(login: Login): LiveData<Resource<InfoToken>> {

    }*/
}