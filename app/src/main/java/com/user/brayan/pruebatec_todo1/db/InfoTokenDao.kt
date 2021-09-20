package com.user.brayan.pruebatec_todo1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.user.brayan.pruebatec_todo1.model.Accounts
import com.user.brayan.pruebatec_todo1.model.HistoryAccounts
import com.user.brayan.pruebatec_todo1.model.InfoToken

@Dao
abstract class InfoTokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(history: List<InfoToken>)

    @Query("select * from infotoken")
    abstract fun load(): LiveData<List<InfoToken>>
}