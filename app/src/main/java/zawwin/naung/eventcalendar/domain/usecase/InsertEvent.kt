package zawwin.naung.eventcalendar.domain.usecase

import zawwin.naung.eventcalendar.data.MyEventRepo
import zawwin.naung.eventcalendar.domain.core.MyResult
import zawwin.naung.eventcalendar.domain.core.MyUseCase
import zawwin.naung.eventcalendar.domain.entity.MyEventEntity
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