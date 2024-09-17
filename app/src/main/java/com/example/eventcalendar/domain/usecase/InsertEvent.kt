package com.example.eventcalendar.domain.usecase

import com.example.eventcalendar.data.MyEventRepo
import com.example.eventcalendar.domain.core.MyResult
import com.example.eventcalendar.domain.core.MyUseCase
import com.example.eventcalendar.domain.entity.MyEventEntity
import javax.inject.Inject

class InsertEvent @Inject constructor(
    private val repo: MyEventRepo
) : MyUseCase<MyEventEntity, Unit>() {
    override suspend fun provide(input: MyEventEntity): MyResult<Unit> {
        return try {
            repo.insertEvent(input)
            MyResult.Success(Unit)
        } catch (e: Throwable) {
            MyResult.Error(e)
        }
    }
}