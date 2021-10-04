package com.user.brayan.pruebatec_todo1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.user.brayan.pruebatec_todo1.model.HistoryAccounts

@Dao
abstract class HistoryAccountsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(history: List<HistoryAccounts>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertHistory(history: HistoryAccounts)

    @Query("select * from HistoryAccounts where accountID = :accountID")
    abstract fun load(accountID: Int): LiveData<List<HistoryAccounts>>
}