package com.user.brayan.pruebatec_todo1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.user.brayan.pruebatec_todo1.model.Accounts

@Dao
abstract class AccountsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: List<Accounts>)

    @Query("select * from accounts")
    abstract fun load(): LiveData<List<Accounts>>
}