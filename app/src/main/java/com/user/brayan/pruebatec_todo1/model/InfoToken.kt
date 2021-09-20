package com.user.brayan.pruebatec_todo1.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    primaryKeys = [
        "token"
    ]
)
data class InfoToken(
    @field:SerializedName("as_token")
    val token: String,
    @field:SerializedName("as_expires_in")
    val expires: String,
    @field:SerializedName("as_type_token")
    val type: String,
    @field:SerializedName("as_refresh_token")
    val refresh: String
)
