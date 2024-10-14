package zawwin.naung.eventcalendar.di

import android.content.Context
import androidx.room.Room
import zawwin.naung.eventcalendar.data.MyEventDao
import zawwin.naung.eventcalendar.data.MyEventDatabase
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