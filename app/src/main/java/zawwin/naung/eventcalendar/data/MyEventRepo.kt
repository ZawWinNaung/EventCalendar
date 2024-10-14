package zawwin.naung.eventcalendar.data

import zawwin.naung.eventcalendar.domain.entity.MyEventEntity

interface MyEventRepo {
    suspend fun getEventsByDate(date: Long): List<MyEventEntity>

    suspend fun insertEvent(event: MyEventEntity)
}