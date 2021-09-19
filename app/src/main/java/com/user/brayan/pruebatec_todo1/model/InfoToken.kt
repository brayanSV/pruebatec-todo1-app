package com.user.brayan.pruebatec_todo1.model

import com.google.gson.annotations.SerializedName

data class InfoToken(
    @field:SerializedName("as_token")
    val token: String,
    @field:SerializedName("as_expires_in")
    val expires: String,
    @field:SerializedName("as_type_token")
    val type_token: String,
    @field:SerializedName("as_refresh_token")
    val refresh_token: String
)
