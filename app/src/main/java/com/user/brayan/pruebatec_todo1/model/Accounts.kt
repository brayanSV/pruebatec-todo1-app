package com.user.brayan.pruebatec_todo1.model

import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(
    indices = [
        Index("id")
    ],
    primaryKeys = [
        "number"
    ]
)

data class Accounts(
    val id: Int,
    @field:SerializedName("account")
    val accountType: String,
    @field:SerializedName("number")
    val number: String,
    @field:SerializedName("balance")
    val balance: String
)
