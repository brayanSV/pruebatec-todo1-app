package com.user.brayan.pruebatec_todo1.model

/*import androidx.room.Entity
import androidx.room.Index*/
import com.google.gson.annotations.SerializedName

/*@Entity(
    primaryKeys = [
        "acountID"
    ]
)*/

data class Accounts(
    @field:SerializedName("id")
    val acountID: Int,
    @field:SerializedName("account")
    val accountType: String,
    @field:SerializedName("number")
    val number: String,
    @field:SerializedName("balance")
    val balance: String
)
