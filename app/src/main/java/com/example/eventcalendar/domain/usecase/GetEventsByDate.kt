package com.example.eventcalendar.domain.usecase

import com.example.eventcalendar.data.MyEventRepo
import com.example.eventcalendar.domain.core.MyResult
import com.example.eventcalendar.domain.core.MyUseCase
import com.example.eventcalendar.domain.mapper.EventMapper
import com.example.eventcalendar.domain.model.MyEvent
import javax.inject.Inject

class GetEventsByDate @Inject constructor(
    private val repo: MyEventRepo
) : MyUseCase<Long, List<MyEvent>>() {
    override suspend fun provide(input: Long): MyResult<List<MyEvent>> {
        return try {
            val eventsByDate = repo.getEventsByDate(input)
            val eventList = EventMapper.map(eventsByDate)
            MyResult.Success(eventList)
        } catch (e: Throwable) {
            MyResult.Error(e)
        }
    }
}