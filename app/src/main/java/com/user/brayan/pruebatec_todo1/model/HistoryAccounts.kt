package com.user.brayan.pruebatec_todo1.model

/*import androidx.room.Entity
import androidx.room.Index*/
import com.google.gson.annotations.SerializedName

/*@Entity(
    indices = [
        Index("id")
    ]
)*/

data class HistoryAccounts(
    val id: Int,
    @field:SerializedName("date")
    val date: String,
    @field:SerializedName("descripcion")
    val descripcion: String,
    @field:SerializedName("reference")
    val reference: String,
    @field:SerializedName("amount")
    val amount: String,
    @field:SerializedName("accountID")
    val accountID: Int
)
