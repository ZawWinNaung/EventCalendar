package zawwin.naung.eventcalendar.domain.usecase

import zawwin.naung.eventcalendar.data.MyEventRepo
import zawwin.naung.eventcalendar.domain.core.MyResult
import zawwin.naung.eventcalendar.domain.core.MyUseCase
import zawwin.naung.eventcalendar.domain.mapper.EventMapper
import zawwin.naung.eventcalendar.domain.model.MyEvent
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