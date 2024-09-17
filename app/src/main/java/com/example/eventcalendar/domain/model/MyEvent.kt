package com.example.eventcalendar.domain.model

sealed class MyEvent(
    val title: String,
    val description: String,
    val time: Long,
    val date: Long
) {
    class Reminder(
        val repeat: String?,
        title: String,
        description: String,
        time: Long,
        date: Long
    ) : MyEvent(title, description, time, date)

    class SimpleEvent(
        val repeat: String?,
        val endTime: Long,
        title: String,
        description: String,
        time: Long,
        date: Long
    ) : MyEvent(title, description, time, date)
}