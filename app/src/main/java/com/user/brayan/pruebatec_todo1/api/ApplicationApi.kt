package com.user.brayan.pruebatec_todo1.api

import androidx.lifecycle.LiveData
import com.user.brayan.pruebatec_todo1.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface  ApplicationApi {
    @POST("/Token")
    fun login(@Body loginUser: LoginUser): LiveData<ApiResponse<InfoToken>>

    @GET("/Account")
    fun accounts(): LiveData<ApiResponse<List<Accounts>>>

    @POST("/NewTransfer")
    fun newTransfer(@Body history: HistoryAccounts): LiveData<ApiResponse<String>>

    @GET("/HistoryCurrentAccount")
    fun historyCurrentAccount(): LiveData<ApiResponse<List<HistoryAccounts>>>

    @GET("/HistorySavingsAccount")
    fun historySavingsAccount(): LiveData<ApiResponse<List<HistoryAccounts>>>
}