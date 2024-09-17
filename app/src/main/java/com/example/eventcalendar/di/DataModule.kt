package com.example.eventcalendar.di

import android.content.Context
import androidx.room.Room
import com.example.eventcalendar.data.MyEventDao
import com.example.eventcalendar.data.MyEventDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): MyEventDatabase {
        return Room.databaseBuilder(
            appContext,
            MyEventDatabase::class.java,
            "my_event_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(myEventDatabase: MyEventDatabase):MyEventDao{
        return myEventDatabase.myEventDao()
    }
}