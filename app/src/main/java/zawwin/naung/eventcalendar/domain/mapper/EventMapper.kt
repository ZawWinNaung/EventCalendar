package zawwin.naung.eventcalendar.domain.mapper

import zawwin.naung.eventcalendar.domain.entity.MyEventEntity
import zawwin.naung.eventcalendar.domain.model.MyEvent

object EventMapper {
    val map: (List<MyEventEntity>) -> (List<MyEvent>) = { eventList ->
        val myEventList: MutableList<MyEvent> = mutableListOf()
        eventList.forEach {
            if (it.endTime == null && it.endDate == null) {
                myEventList.add(
                    MyEvent.Reminder(
                        repeat = "",
                        title = it.title,
                        description = it.description,
                        time = it.time,
                        date = it.date
                    )
                )
            } else {
                myEventList.add(
                    MyEvent.SimpleEvent(
                        repeat = "",
                        title = it.title,
                        description = it.description,
                        time = it.time,
                        endTime = it.endTime!!,
                        date = it.date
                    )
                )
            }
        }
        myEventList
    }
}