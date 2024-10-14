package zawwin.naung.eventcalendar.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import zawwin.naung.eventcalendar.domain.entity.MyEventEntity

@Dao
interface MyEventDao {

    @Query("SELECT * FROM my_event WHERE date = :date")
    suspend fun getEventsByDate(date: Long): List<MyEventEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertEvent(event:MyEventEntity)
}