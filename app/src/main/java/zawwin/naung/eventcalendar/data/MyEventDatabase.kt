package zawwin.naung.eventcalendar.data

import androidx.room.Database
import androidx.room.RoomDatabase
import zawwin.naung.eventcalendar.domain.entity.MyEventEntity

@Database(entities = [MyEventEntity::class], version = 1)
abstract class MyEventDatabase : RoomDatabase() {

    abstract fun myEventDao(): MyEventDao

}