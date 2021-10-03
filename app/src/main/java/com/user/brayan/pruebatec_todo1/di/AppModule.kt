package com.user.brayan.pruebatec_todo1.di

import android.app.Application
import androidx.room.Room
import com.user.brayan.pruebatec_todo1.api.ApplicationApi
import com.user.brayan.pruebatec_todo1.db.AccountsDao
import com.user.brayan.pruebatec_todo1.db.HistoryAccountsDao
import com.user.brayan.pruebatec_todo1.db.InfoTokenDao
import com.user.brayan.pruebatec_todo1.db.PruebaTecDb
import com.user.brayan.pruebatec_todo1.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideApplicationApi(): ApplicationApi {
        return Retrofit.Builder()
            .baseUrl("https://47dced59-f62d-48ad-9211-eb562f8a2c05.mock.pstmn.io")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ApplicationApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): PruebaTecDb {
        return Room.databaseBuilder(app, PruebaTecDb::class.java, "pruebatec.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: PruebaTecDb): AccountsDao {
        return db.accountsDao()
    }

    @Singleton
    @Provides
    fun provideRepoDao(db: PruebaTecDb): HistoryAccountsDao {
        return db.historyAccountsDao()
    }
}