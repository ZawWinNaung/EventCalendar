package com.example.eventcalendar.data

import com.example.eventcalendar.domain.entity.MyEventEntity
import javax.inject.Inject

class MyEventRepoImpl @Inject constructor(
    private val dao: MyEventDao
) : MyEventRepo {
    override suspend fun getEventsByDate(date: Long): List<MyEventEntity> {
        return dao.getEventsByDate(date)
    }

    override suspend fun insertEvent(event: MyEventEntity) {
        dao.insertEvent(event)
    }
}