package com.example.eventcalendar.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Long.toTimeString(): String {
    val calendar = Calendar.getInstance()
    calendar.time.time = this
    val formatter = SimpleDateFormat("hh:mm a", Locale.US)
    return try {
        formatter.format(calendar.time)
    } catch (e: Throwable) {
        e.printStackTrace()
        ""
    }
}