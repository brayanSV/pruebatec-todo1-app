package com.user.brayan.pruebatec_todo1.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.user.brayan.pruebatec_todo1.model.Accounts
import com.user.brayan.pruebatec_todo1.model.HistoryAccounts

@Database(
    entities = [
        Accounts::class,
        HistoryAccounts::class
    ],
    version = 1
)

abstract class PruebaTecDb: RoomDatabase() {
    abstract fun accountsDao(): AccountsDao
    abstract fun historyAccountsDao(): HistoryAccountsDao
}