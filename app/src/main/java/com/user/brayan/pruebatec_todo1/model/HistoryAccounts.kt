package com.user.brayan.pruebatec_todo1.model

import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(
    primaryKeys = [
        "historyId"
    ]
)
data class HistoryAccounts(
    @field:SerializedName("id")
    val historyId: Int,
    @field:SerializedName("date")
    val date: String,
    @field:SerializedName("description")
    val descripcion: String,
    @field:SerializedName("reference")
    val reference: String,
    @field:SerializedName("amount")
    val amount: String,
    @field:SerializedName("accountID")
    val accountID: Int
)
