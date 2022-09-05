package com.eblee.pokeguide.di

import android.app.Application
import androidx.room.Room
import com.eblee.pokeguide.data.local.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModules = module {

    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    single { provideDatabase(androidApplication()) }
}
