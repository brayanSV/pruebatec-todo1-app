package com.user.brayan.pruebatec_todo1.api

import androidx.lifecycle.LiveData
import com.user.brayan.pruebatec_todo1.model.Accounts
import com.user.brayan.pruebatec_todo1.model.HistoryAccounts
import com.user.brayan.pruebatec_todo1.model.InfoToken
import com.user.brayan.pruebatec_todo1.model.Login
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface   ApplicationApi {
    /*@POST("/Token")
    fun login(@Body login: Login): LiveData<ApiResponse<InfoToken>>

    @GET("/Account")
    fun accounts(@Header("Authorization") authToken: String): LiveData<ApiResponse<List<Accounts>>>

    @GET("/HistoryCurrentAccount")
    fun historyCurrentAccount(@Header("Authorization") bearer_token: String): LiveData<ApiResponse<List<HistoryAccounts>>>

    @GET("/HistorySavingsAccount")
    fun historySavingsAccount(@Header("Authorization") bearer_token: String): LiveData<ApiResponse<List<HistoryAccounts>>>*/

    @POST("/Token")
    fun login(@Body login: Login): LiveData<ApiResponse<InfoToken>>

    @GET("/Account")
    fun accounts(): LiveData<ApiResponse<List<Accounts>>>

    @GET("/HistoryCurrentAccount")
    fun historyCurrentAccount(): LiveData<ApiResponse<List<HistoryAccounts>>>

    @GET("/HistorySavingsAccount")
    fun historySavingsAccount(): LiveData<ApiResponse<List<HistoryAccounts>>>
}