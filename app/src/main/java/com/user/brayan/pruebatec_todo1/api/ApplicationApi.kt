package com.user.brayan.pruebatec_todo1.api

import androidx.lifecycle.LiveData
import com.user.brayan.pruebatec_todo1.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface  ApplicationApi {
    @POST("/Token")
    fun login(@Body loginUser: LoginUser): LiveData<ApiResponse<InfoToken>>

    @GET("/Account")
    fun accounts(@Header("Authorization") bearerToken: String): LiveData<ApiResponse<List<Accounts>>>

    @POST("/NewTransfer")
    fun newTransfer(@Header("Authorization") bearerToken: String, @Body history: HistoryAccounts): LiveData<ApiResponse<String>>

    @GET("/HistoryCurrentAccount")
    fun historyCurrentAccount(@Header("Authorization") bearerToken: String): LiveData<ApiResponse<List<HistoryAccounts>>>

    @GET("/HistorySavingsAccount")
    fun historySavingsAccount(@Header("Authorization") bearerToken: String): LiveData<ApiResponse<List<HistoryAccounts>>>
}