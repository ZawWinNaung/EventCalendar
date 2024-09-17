package com.example.eventcalendar.data

import com.example.eventcalendar.domain.entity.MyEventEntity

interface MyEventRepo {
    suspend fun getEventsByDate(date: Long): List<MyEventEntity>

    suspend fun insertEvent(event: MyEventEntity)
}