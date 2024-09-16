package com.example.eventcalendar.domain.model

sealed class MyEvent(
    val title: String,
    val description: String,
    val time: Long
) {
    class Reminder(
        val repeat: String?,
        title: String,
        description: String,
        time: Long,
    ) : MyEvent(title, description, time)

    class SimpleEvent(
        val repeat: String?,
        val endTime: Long,
        title: String,
        description: String,
        time: Long
    ) : MyEvent(title, description, time)
}