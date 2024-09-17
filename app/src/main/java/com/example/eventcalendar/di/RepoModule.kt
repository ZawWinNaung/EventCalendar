package com.example.eventcalendar.di

import com.example.eventcalendar.data.MyEventRepo
import com.example.eventcalendar.data.MyEventRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindMyEventRepo(myEventRepoImpl: MyEventRepoImpl): MyEventRepo
}