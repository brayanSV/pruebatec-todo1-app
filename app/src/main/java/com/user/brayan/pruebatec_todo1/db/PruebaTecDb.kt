package com.user.brayan.pruebatec_todo1.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.user.brayan.pruebatec_todo1.model.Accounts
import com.user.brayan.pruebatec_todo1.model.HistoryAccounts
import com.user.brayan.pruebatec_todo1.model.InfoToken

@Database(
    entities = [
        Accounts::class,
        HistoryAccounts::class,
        InfoToken::class
    ],
    version = 2
)

abstract class PruebaTecDb: RoomDatabase() {
    abstract fun accountsDao(): AccountsDao
    abstract fun historyAccountsDao(): HistoryAccountsDao
    abstract fun infoTokenDao(): InfoTokenDao
}